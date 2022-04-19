package unieuroop.view.stock;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Alert.AlertType;
import unieuroop.controller.department.ControllerDepartment;
import unieuroop.model.department.Department;
import unieuroop.model.product.Product;
import unieuroop.view.department.ViewDepartmentEditProducts;

public final class ViewStockProductsImpl implements Initializable, ViewStockProducts {

    @FXML private Spinner<Integer> spinnerQuantity;
    @FXML private Button btnAdd;
    @FXML private Label labelProductName;

    private final Department department;
    private final Product product;
    private int totalQuantity;
    private int maxQuantity;
    private final ControllerDepartment controllerDepartment;
    private final ViewDepartmentEditProducts viewDepartment;
    public ViewStockProductsImpl(final Department department, final Product product, final int maxQuantity,
            final ControllerDepartment controllerDepartment, final ViewDepartmentEditProducts viewDepartment) {
        this.department = department;
        this.product = product;
        this.maxQuantity = maxQuantity;
        this.controllerDepartment = controllerDepartment;
        this.totalQuantity = 0;
        this.viewDepartment = viewDepartment;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final int minValue = this.maxQuantity > 0 ? 1 : 0;
        final SpinnerValueFactory<Integer> values = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, this.maxQuantity);
        this.labelProductName.setText("Product : " + this.product.toString());
        this.spinnerQuantity.setValueFactory(values);
    }

    @Override
    @FXML
    public void buttonAddHandler(final ActionEvent event) {
        final int quantitySelected = this.spinnerQuantity.getValue();
        if (quantitySelected > 0) {
            this.totalQuantity = this.totalQuantity + quantitySelected;
            this.totalQuantity = this.totalQuantity - quantitySelected;
            this.maxQuantity = this.maxQuantity - quantitySelected;
            final SpinnerValueFactory<Integer> newLimit = new SpinnerValueFactory.IntegerSpinnerValueFactory(this.maxQuantity > 0 ? 1 : 0, this.maxQuantity);
            this.spinnerQuantity.setValueFactory(newLimit);
            try {
                this.controllerDepartment.addProductsIn(this.department, Map.of(this.product, quantitySelected));
                this.viewDepartment.updateView();
            } catch (IOException e) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
