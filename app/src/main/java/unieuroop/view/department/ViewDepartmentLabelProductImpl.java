package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import unieuroop.controller.department.ControllerDepartment;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.model.product.Product;

public final class ViewDepartmentLabelProductImpl implements Initializable, ViewDepartmentLabelProduct {
    @FXML private Spinner<Integer> spinnerQuantity;
    @FXML private Button btnAdd;
    @FXML private Label labelProductName;

    private final ControllerDepartment controllerDepartment;
    private final Product product;
    private int maxQuantity;
    private int totalQuantity;

    public ViewDepartmentLabelProductImpl(final ControllerDepartment controllerDepartment, final Product product, final int maxQuantity) {
        this.controllerDepartment = controllerDepartment;
        this.product = product;
        this.maxQuantity = maxQuantity;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.labelProductName.setText(product.toString());
        final int minValue = this.maxQuantity > 0 ? 1 : 0;
        final SpinnerValueFactory<Integer> values = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, this.maxQuantity);
        this.spinnerQuantity.setValueFactory(values);

    }

    @Override
    @FXML
    public void buttonAddHandler(final ActionEvent event) {
        final int quantitySelected = this.spinnerQuantity.getValue();
        if (quantitySelected > 0) {
            this.totalQuantity = this.totalQuantity + quantitySelected;
            this.controllerDepartment.reserveProduct(product, this.totalQuantity);
            this.maxQuantity = this.maxQuantity - quantitySelected;
            final SpinnerValueFactory<Integer> newLimit = new SpinnerValueFactory.IntegerSpinnerValueFactory(this.maxQuantity > 0 ? 1 : 0, this.maxQuantity);
            this.spinnerQuantity.setValueFactory(newLimit);
        }
    }
}
