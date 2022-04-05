package unieuroop.view.stock;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import unieuroop.controller.shop.ControllerShopImpl;
import unieuroop.model.department.Department;
import unieuroop.model.product.Product;

public class ViewStockLabelProduct implements Initializable {

    @FXML
    private Label lblProductsInList;
    @FXML
    private Spinner<Integer> spinnerQuantities;
    @FXML
    private CheckBox chkboxProductTaken;

    private ViewStock viewStock;
    private Map.Entry<Product, Integer> productEntry;
    private ControllerShopImpl controller;

    public ViewStockLabelProduct(final Map.Entry<Product, Integer> product, final ViewStock viewStock, final ControllerShopImpl controller) {
        this.viewStock = viewStock;
        this.productEntry = product;
        this.controller = controller;
    }

    /**
     * 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final int minAmount = this.productEntry.getValue() > 0 ? 1 : 0;
        final SpinnerValueFactory<Integer> rangeAmount = new SpinnerValueFactory.IntegerSpinnerValueFactory(minAmount, this.productEntry.getValue());
        this.lblProductsInList.setText("Product: " + this.productEntry.getKey().toString());
        this.spinnerQuantities.setValueFactory(rangeAmount);
        chkboxProductTaken.setIndeterminate(false);
    }

}
