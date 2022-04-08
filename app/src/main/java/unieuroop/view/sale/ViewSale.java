package unieuroop.view.sale;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import unieuroop.controller.client.ControllerClientImpl;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.sale.ControllerSaleImpl;
import unieuroop.controller.serialization.Pages;
import unieuroop.model.department.Department;
import unieuroop.model.product.Product;
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


    public ViewSale(final ViewMainMenu viewMainMenu, final ControllerClientImpl controllerClient,
        final ControllerDepartmentImpl controllerDepartment, final ControllerSaleImpl controllerSale) {
        this.viewMenu = viewMainMenu;
        this.controllerClient = controllerClient;
        this.controllerDepartment = controllerDepartment;
        this.controllerSale = controllerSale;
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
        final Department input = this.comboDepartments.getValue();
        this.listLabel.getItems().clear();
        this.addLabels(input.getAllProducts().keySet(), input);
    }

    @FXML
    public void buttonSellHandler(final ActionEvent event) {
        if (!this.controllerSale.isReserved()) {
            this.listLabel.getItems().clear();
            this.listSelectedProducts.getItems().clear();
            try {
                final Stage newWindow = Loader.<ViewChoseClient>loadStage(Pages.CHOSE_CLIENT.getPath(), "Chose Client", new ViewChoseClient(this.controllerSale, 
                        this.controllerClient), 100, 100);
                newWindow.setOnCloseRequest((closeEvent) -> {
                    closeEvent.consume();
                    final Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setContentText("You should chose a client or leave with the \"QUIT\" button.\n"
                            + "Remember if you choose QUIT the client will be always EMPTY.\n"
                            + "If you do not choose any Client and press \"SELECT\",\n the selected client will be EMPYT");
                    alert.showAndWait();
                });
                final Stage stage = (Stage) this.btnCompleteSale.getScene().getWindow();
                stage.hide();
                newWindow.showAndWait();
                stage.show();
                this.viewMenu.disableButtons(false);
            } catch (IOException ex) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(ex.getMessage());
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
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(e.getMessage());
            }
        }
    }

    public ListView<String> getListView() {
        return this.listSelectedProducts;
    }
}
