package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import unieuroop.controller.department.ControllerDepartment;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.stock.ControllerStock;
import unieuroop.model.department.Department;
import unieuroop.view.loader.Loader;
import unieuroop.view.stock.ViewStockProductsImpl;

public final class ViewDepartmentEditProductsImpl implements Initializable, ViewDepartmentEditProducts {

    @FXML private Label lblDepartmentName;
    @FXML private ListView<Pane> listStockProducts;
    @FXML private ListView<Pane> listDepartmentProducts;

    private final Department department;
    private final ControllerDepartment controllerDepartment;
    private final ControllerStock controllerStock;


    public ViewDepartmentEditProductsImpl(final Department currentDepartment, final ControllerDepartment controllerDepartment, final ControllerStock controllerStock) {
        this.department = currentDepartment;
        this.controllerDepartment = controllerDepartment;
        this.controllerStock = controllerStock;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.updateView();
    }

    @Override
    public void updateView() {
        this.lblDepartmentName.setText("Department Name: " + this.department.getDepartmentName());
        this.listDepartmentProducts.getItems().clear();
        this.listStockProducts.getItems().clear();
        this.loadStockProducts();
        this.loadDepartmentProducts();
    }

    /**
     * 
     */
    private void loadDepartmentProducts() {
        for (final var product : this.controllerDepartment.getProductsQuantityOf(this.department).entrySet()) {
            try {
                final ViewDepartmentProducts controller = new ViewDepartmentProductsImpl(this, this.department, product.getKey(), product.getValue(), this.controllerDepartment);
                final Pane pane = Loader.loadPane(Pages.LABEL_PRODUCT_DEPARTMENT.getPath(), controller);
                this.listDepartmentProducts.getItems().add(pane);
            } catch (IOException e) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    /**
     * 
     */
    private void loadStockProducts() {
        for (final var product : this.controllerStock.getProductsStocked().entrySet()) {
            try {
                final var controller = new ViewStockProductsImpl(this.department, product.getKey(), product.getValue(), this.controllerDepartment,
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
}
