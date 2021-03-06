package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import unieuroop.controller.department.ControllerDepartment;
import unieuroop.model.department.Department;
import unieuroop.model.product.Product;

public final class ViewDepartmentProductsImpl implements Initializable, ViewDepartmentProducts {

    @FXML private Spinner<Integer> spinnerQuantity;
    @FXML private Button btnRemove;
    @FXML private Label labelProductName;

    private final Product product;
    private int maxQuantity;
    private final Department department;
    private final ControllerDepartment controllerDepartment;
    private final ViewDepartmentEditProducts viewDepartment;

    public ViewDepartmentProductsImpl(final ViewDepartmentEditProducts viewDepartment, final Department department, final Product product, final int quantity,
            final ControllerDepartment controllerDepartment) {
        this.viewDepartment = viewDepartment;
        this.department = department;
        this.product = product;
        this.maxQuantity = quantity;
        this.controllerDepartment = controllerDepartment;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.setSpinner();
    }
    private void setSpinner() {
        final int minValue = this.maxQuantity > 0 ? 1 : 0;
        final SpinnerValueFactory<Integer> values = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, this.maxQuantity);
        this.labelProductName.setText("Product : " + this.product.toString());
        this.spinnerQuantity.setValueFactory(values);
    }

    @Override
    @FXML
    public void buttonRemoveHandler(final ActionEvent event) throws IOException {
        final var qta = this.spinnerQuantity.getValue();
        this.maxQuantity -= qta;
        this.setSpinner();
        this.controllerDepartment.removeProductsFrom(this.department, Map.of(this.product, qta));
        this.viewDepartment.updateView();
    }

}
