package unieuroop.view.stock;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

public class ViewStock implements Initializable {

    @FXML
    private Stage primaryStage;
    @FXML
    private TextField txtSearchProducts;
    @FXML
    private Button btnSearchFilters;
    @FXML
    private Button btnTakeProducts;
    @FXML
    private Button btnBuyProducts;
    @FXML
    private Button btnDeleteProducts;
    @FXML
    private RadioButton rdbtnIncreasing;
    @FXML
    private RadioButton rdbtnDecreasing;
    @FXML
    private ListView<Pane> listProductsStocked;

    private final ControllerShopImpl controllerShop;
    private final ViewMainMenu viewMenu;
    private final Stage stage;


    public ViewStock(final ViewMainMenu view, final ControllerShopImpl controllerShop, final Stage primaryStage) {
        this.controllerShop = controllerShop;
        this.viewMenu = view;
        this.stage = primaryStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

}
