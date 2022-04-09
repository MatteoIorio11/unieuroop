package unieuroop.view.stock;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.model.product.Product;

public class ViewStockLabelProduct implements Initializable {

    @FXML
    private Label lblProductsInList;
    @FXML
    private Spinner<Integer> spinnerQuantities;
    @FXML
    private CheckBox chkboxProductTaken;

    private ViewStock viewStock;
    private final Map.Entry<Product, Integer> productEntry;
    private ControllerStockImpl controllerStock;

    public ViewStockLabelProduct(final Map.Entry<Product, Integer> product, final ViewStock viewStock, final ControllerStockImpl controller) {
        this.viewStock = viewStock;
        this.productEntry = product;
        this.controllerStock = controller;
    }

    /**
     * 
     */
    @Override
    public void initialize(final  URL location,  final ResourceBundle resources) {
        final int minAmount = this.productEntry.getValue() > 0 ? 1 : 0;
        final SpinnerValueFactory<Integer> rangeAmount = new SpinnerValueFactory.IntegerSpinnerValueFactory(minAmount, this.productEntry.getValue());
        this.lblProductsInList.setText("Product: " + this.productEntry.getKey().toString());
        this.spinnerQuantities.setValueFactory(rangeAmount);
        chkboxProductTaken.setIndeterminate(false);
    }

    @FXML
    public void chkboxProductTakenHandler() {
        
    }

}
