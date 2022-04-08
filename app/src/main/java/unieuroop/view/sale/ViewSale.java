package unieuroop.view.sale;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;
import unieuroop.controller.client.ControllerClientImpl;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.sale.ControllerSaleImpl;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.shop.ControllerShopImpl;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Client;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierImpl;
import unieuroop.view.client.ViewChoseClient;
import unieuroop.view.loader.Loader;
import unieuroop.view.menu.ViewMainMenu;

public final class ViewSale implements Initializable {
    @FXML
    private Stage primaryStage;
    @FXML
    private ScrollPane scrollPane;;
    @FXML
    private ListView<String> listSelectedProducts;
    @FXML
    private ListView<Pane> listLabel;
    @FXML
    private Button btnCompleteSale;
    @FXML
    private Button btnAddToSale;
    @FXML
    private Button btnQuit;
    @FXML
    private ComboBox<Department> comboDepartments;
    private final ControllerClientImpl controllerClient;
    private final ControllerDepartmentImpl controllerDepartment;
    private final ControllerSaleImpl controllerSale;

    private final ViewMainMenu viewMenu;
    private Department input;
    private final Stage stage;


    public ViewSale(final ViewMainMenu viewMainMenu, final ControllerClientImpl controllerClient,
        final ControllerDepartmentImpl controllerDepartment, final ControllerSaleImpl controllerSale, final Stage primaryStage) {
        this.viewMenu = viewMainMenu;
        this.controllerClient = controllerClient;
        this.controllerDepartment = controllerDepartment;
        this.controllerSale = controllerSale;
        this.stage = primaryStage;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.comboDepartments.getItems().addAll(this.controllerDepartment.getDepartments());
    }

    @FXML
    public void comboSelectDepartment(final ActionEvent event) {
        if (this.controllerSale.isReserved()) {
            this.viewMenu.disableButtons(true);
        }
        this.input = this.comboDepartments.getValue();
        this.listLabel.getItems().clear();
        this.addLabels(this.input.getAllProducts().keySet(), this.input);
    }

    @FXML
    public void buttonSellHandler(final ActionEvent event) {
        if (!this.controllerSale.isReserved()) {
            this.listLabel.getItems().clear();
            this.listSelectedProducts.getItems().clear();
            try {
                final Stage newWindow = Loader.<ViewChoseClient>loadStage(Pages.CHOSE_CLIENT.getPath(), "", new ViewChoseClient(this.controllerSale, 
                        this.controllerClient));
                newWindow.setOnCloseRequest((closeEvent) -> {
                    closeEvent.consume();
                    final Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setContentText("You should chose a client or leave with the \"QUIT\" button.\n"
                            + "Remember if you choose QUIT the client will be always EMPTY.\n"
                            + "If you do not choose any Client and press \"SELECT\",\n the selected client will be EMPYT");
                    alert.showAndWait();
                });
                this.stage.hide();
                newWindow.showAndWait();
                this.stage.show();
                this.viewMenu.disableButtons(false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } 
    }

    @FXML
    public void buttonQuitHandler(final ActionEvent event) {
        this.controllerSale.clearReservedProducts();
        this.listLabel.getItems().clear();
        this.listSelectedProducts.getItems().clear();
        this.viewMenu.disableButtons(false);
    }

    private void addLabels(final Set<Product> products, final Department department) {
        for (final var product : products) {
            try {
                final var pane = Loader.<ViewLabelSale>loadPane(Pages.LABEL_PRODUCT_SALE.getPath(), new ViewLabelSale(product, department,
                        this.controllerSale.getQuantityOf(product, department), this, this.controllerSale));
                this.listLabel.getItems().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ListView<String> getListView() {
        return this.listSelectedProducts;
    }
}
