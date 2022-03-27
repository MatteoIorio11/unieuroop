package unieuroop.view.sale;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import unieuroop.model.product.Product;

public final class ViewLabelSale implements Initializable {

    @FXML
    private Spinner<Integer> spinnerQuantity;
    @FXML
    private Button btnAdd;
    @FXML
    private Label labelProductName;

    private final Product product;
    private final int maxQuantity;
    public ViewLabelSale(final Product product, final int maxQuantity) {
        this.product = product;
        this.maxQuantity = maxQuantity;
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final SpinnerValueFactory<Integer> values = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, this.maxQuantity);
        this.labelProductName.setText(this.product.toString());
        this.spinnerQuantity.setValueFactory(values);
    }
    @FXML
    public void btnAddToSale(final ActionEvent event) {
        final int quantity = this.spinnerQuantity.getValue();
        
    }
}
