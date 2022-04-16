package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.model.department.Department;
import unieuroop.view.loader.Loader;
import unieuroop.view.stock.ViewStockProducts;

public final class ViewDepartmentEditProducts implements Initializable {

    @FXML private Label lblDepartmentName;
    @FXML private ListView<Pane> listStockProducts;
    @FXML private ListView<Pane> listDepartmentProducts;
    @FXML private TextArea txtAreaInfoProducts;
    @FXML private Button btnAddProducts;
    @FXML private Button btnRemoveProducts;

    private final Department department;
    private final ControllerStaffImpl controllerStaff;
    private final ControllerDepartmentImpl controllerDepartment;
    private final ControllerStockImpl controllerStock;


    public ViewDepartmentEditProducts(final Department currentDepartment, final ControllerStaffImpl controllerStaff, 
            final ControllerDepartmentImpl controllerDepartment, final ControllerStockImpl controllerStock) {
        this.department = currentDepartment;
        this.controllerStaff = controllerStaff;
        this.controllerDepartment = controllerDepartment;
        this.controllerStock = controllerStock;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.loadStockProducts();
        this.loadDepartmentProducts();
    }

    @FXML
    public void listProductsDepartmentHandler() {

    }

    @FXML
    public void btnAddProductsHandler() {

    }

    @FXML
    public void btnRemoveProductsHandler() {

    }

    public void loadDepartmentProducts() {
        for (final var product : this.department.getAllProducts().entrySet()) {
            try {
                final var controller = new ViewDepartmentProducts(this.department, product.getKey(), product.getValue(), this.controllerStaff, this.controllerDepartment);
                final Pane pane = Loader.loadPane(Pages.LABEL_PRODUCT_DEPARTMENT.getPath(), controller);
                this.listDepartmentProducts.getItems().add(pane);
            } catch (IOException e) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();            }
        }
    }

    private void loadStockProducts() {
        for (final var product : this.controllerStock.getProductsStocked().entrySet()) {
            try {
                final var controller = new ViewStockProducts(this.department, product.getKey(), product.getValue(), this.controllerStock, this.controllerDepartment,
                        this);
                final Pane pane = Loader.loadPane(Pages.LABEL_PRODUCT.getPath(), controller);
                this.listStockProducts.getItems().add(pane);
            } catch (IOException e) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public ListView<Pane> getList() {
        return this.listDepartmentProducts;
    }
}
