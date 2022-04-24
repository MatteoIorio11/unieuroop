package unieuroop.view.sale;

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
import unieuroop.controller.sale.ControllerSale;
import unieuroop.model.department.Department;
import unieuroop.model.product.Product;

public final class ViewLabelSaleImpl implements Initializable, ViewLabelSale {

    @FXML private Spinner<Integer> spinnerQuantity;
    @FXML private Button btnAdd;
    @FXML private Label labelProductName;

    private final Product product;
    private int maxQuantity;
    private final ViewSale view;
    private final ControllerSale controllerSale;
    private final Department department;
    private int totalQuantity;
    public ViewLabelSaleImpl(final Product product, final Department department, final int maxQuantity,
            final ViewSale view, final ControllerSale controllerSale) {
        this.product = product;
        this.maxQuantity = maxQuantity;
        this.view = view;
        this.controllerSale = controllerSale;
        this.department = department;
        this.totalQuantity = 0;
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
            this.controllerSale.reserveProducts(department, new HashMap<>(Map.of(this.product, totalQuantity)));
            this.view.getListView().getItems().clear();
            this.controllerSale.getReservedProducts().entrySet().forEach((entry) -> this.view.getListView().getItems().add("Product : " + entry.getKey().getName() + "\n"
            + "Quantity : " + entry.getValue()));
            this.maxQuantity = this.maxQuantity - quantitySelected;
            final SpinnerValueFactory<Integer> newLimit = new SpinnerValueFactory.IntegerSpinnerValueFactory(this.maxQuantity > 0 ? 1 : 0, this.maxQuantity);
            this.spinnerQuantity.setValueFactory(newLimit);
        }
    }
}
