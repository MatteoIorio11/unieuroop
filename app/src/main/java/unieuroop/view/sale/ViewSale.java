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
import unieuroop.view.menu.ViewMainMenu;

public final class ViewSale implements Initializable {

    private static final String APPLE_PRODUCT="apple";
    private static final LocalTime TIME_START = LocalTime.of(1, 1);
    private static final LocalTime TIME_FINISH = LocalTime.of(1, 1);
    private static final LocalDate TIME_NOW = LocalDate.now();
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
    private final ControllerShopImpl controllerShop;
    private final ControllerClientImpl controllerClient;
    private final ViewMainMenu viewMenu;
    private final Stage stage;
    private Department input;
    private Supplier s1;


   public ViewSale(final ViewMainMenu view, final ControllerShopImpl controllerShop, final ControllerClientImpl controllerClient, final Stage primaryStage) {
        this.viewMenu = view;
        this.controllerShop = controllerShop;
        this.controllerClient = controllerClient;
        this.stage = primaryStage;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.comboDepartments.getItems().addAll(this.controllerShop.getDepartments());
        this.comboDepartments.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (this.controllerShop.isReserved()) {
                this.viewMenu.disableButtons(true);
            }
            this.input = this.comboDepartments.getValue();
            this.listLabel.getItems().clear();
            this.addLabels(this.input.getAllProducts().keySet(), this.input);
        });
        this.btnCompleteSale.setOnMouseClicked((event) -> {
            if (!this.controllerShop.isReserved()) {
                    this.listLabel.getItems().clear();
                    this.listSelectedProducts.getItems().clear();
                    final Pane pane;
                    try {
                        final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
                        final double xSize =  screenBounds.getMaxX() / 2;
                        final double ySize = screenBounds.getMaxY() / 2;
                        final Stage newWindow = new Stage();
                        final var view = new ViewChoseClient(this.controllerShop, this.controllerClient, newWindow);
                        final var loader = new FXMLLoader(getClass().getResource(Pages.CHOSE_CLIENT.getPath()));
                        loader.setController(view);
                        pane = loader.load();
                        final Scene secondScene = new Scene(pane, xSize, ySize);
                        newWindow.setTitle("Client Selection");
                        newWindow.setScene(secondScene);
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
            });

        this.btnQuit.setOnMouseClicked((event) -> {
            this.controllerShop.clearReservedProducts();
            this.listLabel.getItems().clear();
            this.listSelectedProducts.getItems().clear();
            this.viewMenu.disableButtons(false);
        });

    }

    private void addLabels(final Set<Product> products, final Department department) {
        for (final var product : products) {
            Pane pane;
            try {
                final var loader = new FXMLLoader(getClass().getResource(Pages.LABEL_PRODUCT_SALE.getPath()));
                loader.setController(new ViewLabelSale(product, department, this.controllerShop.getQuantityOf(product, department), this, this.controllerShop));
                pane = loader.load();
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
