package unieuroop.view.department;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.model.department.Department;
import unieuroop.model.product.Product;

public class ViewDepartmentProducts implements Initializable {

    @FXML private Spinner<Integer> spinnerQuantity;
    @FXML private Button btnAdd;
    @FXML private Label labelProductName;

    private final Product product;
    private int maxQuantity;
    private final ControllerStaffImpl controllerStaff;
    private final ControllerDepartmentImpl controllerDepartment;
    
    public ViewDepartmentProducts(final Product product, final int quantity, final ControllerStaffImpl controllerStaff,
            final ControllerDepartmentImpl controllerDepartment) {
        this.product = product;
        this.maxQuantity = quantity;
        this.controllerStaff = controllerStaff;
        this.controllerDepartment = controllerDepartment;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final int minValue = this.maxQuantity > 0 ? 1 : 0;
        final SpinnerValueFactory<Integer> values = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, this.maxQuantity);
        this.labelProductName.setText("Product : " + this.product.toString());
        this.spinnerQuantity.setValueFactory(values);
    }

    @FXML
    public void buttonAddHandler(final ActionEvent event) {

    }

}
