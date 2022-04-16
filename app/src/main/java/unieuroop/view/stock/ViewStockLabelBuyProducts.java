package unieuroop.view.stock;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.model.product.Product;

public class ViewStockLabelBuyProducts implements Initializable {

    @FXML
    private Label lblProductSold;
    @FXML
    private Label lblProductPrice;
    @FXML
    private Spinner<Integer> spinnerQuantities;
    @FXML
    private CheckBox chkboxProductBought;

    private final ViewStockBuyProducts viewStockBuyProducts;
    private final ControllerStockImpl controllerStock;
    private final Map.Entry<Product, Double> productSold;

    public ViewStockLabelBuyProducts(final Map.Entry<Product, Double> product, final ViewStockBuyProducts viewStockBuyProducts, final ControllerStockImpl controller) {
        this.viewStockBuyProducts = viewStockBuyProducts;
        this.controllerStock = controller;
        this.productSold = product;
    }

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.lblProductSold.setText("Product: " + this.productSold.getKey().toString());
        this.lblProductPrice.setText("Price: " + this.productSold.getValue().toString());
        final SpinnerValueFactory<Integer> rangeAmount = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        this.spinnerQuantities.setValueFactory(rangeAmount);
        this.chkboxProductBought.setSelected(false);
    }

    @FXML
    public void chkboxProductBoughtHandler() {
        
    }

}
