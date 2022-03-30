package unieuroop.view.sale;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import unieuroop.controller.shop.ControllerShopImpl;
import unieuroop.model.department.Department;
import unieuroop.model.product.Product;

public final class ViewLabelSale implements Initializable {

    @FXML
    private Spinner<Integer> spinnerQuantity;
    @FXML
    private Button btnAdd;
    @FXML
    private Label labelProductName;

    private final Product product;
    private int maxQuantity;
    private final ViewSale view;
    private final ControllerShopImpl controller;
    private final Department department;
    private int totalQuantity;
    public ViewLabelSale(final Product product, final Department department, final int maxQuantity,
            final ViewSale view, final ControllerShopImpl controller) {
        this.product = product;
        this.maxQuantity = maxQuantity;
        this.view = view;
        this.controller = controller;
        this.department = department;
        this.totalQuantity = 0;
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final int minValue = this.maxQuantity > 0 ? 1 : 0;
        final SpinnerValueFactory<Integer> values = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, this.maxQuantity);
        this.labelProductName.setText("Product : " + this.product.toString());
        this.spinnerQuantity.setValueFactory(values);

        this.btnAdd.setOnMouseClicked((e) -> {
            final int quantitySelected = this.spinnerQuantity.getValue();
            if (quantitySelected > 0) {
                this.totalQuantity = this.totalQuantity + quantitySelected;
                this.controller.reserveProducts(department, new HashMap<>(Map.of(this.product, totalQuantity)));
                this.view.getListView().getItems().clear();
                System.out.println(this.controller.getReservedProducts());
                this.controller.getReservedProducts().entrySet().forEach((entry) -> this.view.getListView().getItems().add("Product : " + entry.getKey().getName() + ", Quantity : " + entry.getValue()));
                this.maxQuantity = this.maxQuantity - quantitySelected;
                final SpinnerValueFactory<Integer> newLimit = new SpinnerValueFactory.IntegerSpinnerValueFactory(this.maxQuantity > 0 ? 1 : 0, this.maxQuantity);
                this.spinnerQuantity.setValueFactory(newLimit);
            }
        });
    }
}
