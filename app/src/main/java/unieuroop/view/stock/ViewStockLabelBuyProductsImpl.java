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
import unieuroop.controller.stock.ControllerStock;
import unieuroop.model.product.Product;

public final class ViewStockLabelBuyProductsImpl implements Initializable, ViewStockLabelBuyProducts {

    @FXML
    private Label lblProductSold;
    @FXML
    private Label lblProductPrice;
    @FXML
    private Spinner<Integer> spinnerQuantities;
    @FXML
    private CheckBox chkboxProductBought;

    private final ViewStockBuyProducts viewStockBuyProducts;
    private final ControllerStock controllerStock;
    private final Map.Entry<Product, Double> productSold;

    public ViewStockLabelBuyProductsImpl(final Map.Entry<Product, Double> product, final ViewStockBuyProducts viewStockBuyProducts, final ControllerStock controller) {
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
        this.checkIfProductPresent();
    }

    @Override
    @FXML
    public void chkboxProductBoughtHandler() {
        if (this.chkboxProductBought.isSelected()) {
            this.controllerStock.addProductBuying(this.productSold, this.spinnerQuantities.getValue());
            this.viewStockBuyProducts.uploadInfoLabelBuying();
            this.checkIfProductPresent();
        } else if (!this.chkboxProductBought.isSelected()) {
            this.controllerStock.removeProductsBuying(this.productSold, this.spinnerQuantities.getValue());
            this.viewStockBuyProducts.uploadInfoLabelBuying();
            this.checkIfProductPresent();
        }
    }

    /**
     * 
     */
    private void checkIfProductPresent() {
        if (this.controllerStock.checkIfProductBuyingPresent(this.productSold.getKey())) {
            this.spinnerQuantities.setDisable(true);
            this.chkboxProductBought.setSelected(true);
            final SpinnerValueFactory<Integer> rangeAmount = new SpinnerValueFactory.IntegerSpinnerValueFactory(this.controllerStock.getAmountofProductBuying(this.productSold.getKey()), 100);
            this.spinnerQuantities.setValueFactory(rangeAmount);
        } else {
            this.spinnerQuantities.setDisable(false);
            final SpinnerValueFactory<Integer> rangeAmount = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
            this.spinnerQuantities.setValueFactory(rangeAmount);
            this.chkboxProductBought.setSelected(false);
        }
    }

}
