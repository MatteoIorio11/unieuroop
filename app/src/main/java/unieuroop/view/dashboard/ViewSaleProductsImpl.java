package unieuroop.view.dashboard;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import unieuroop.model.product.Product;
import unieuroop.model.sale.Sale;

public final class ViewSaleProductsImpl implements Initializable {
    private final Sale sale;
    @FXML
    private ListView<Product> lstProducts;

    public ViewSaleProductsImpl(final Sale sale) {
        this.sale = sale;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.lstProducts.getItems().addAll(this.sale.getProducts());
    }

}
