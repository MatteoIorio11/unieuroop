package unieuroop.view.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import unieuroop.model.sale.Sale;

public final class ViewSaleProductsImpl implements Initializable {
    private final Sale sale;
    @FXML
    private ListView<String> lstProducts;

    public ViewSaleProductsImpl(final Sale sale) {
        this.sale = sale;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final var products = this.sale.getProducts().stream().map(p -> "PRODUCT: " + p.toString() + " - QUANTITY: " + this.sale.getQuantityOf(p)).collect(Collectors.toList());
        this.lstProducts.getItems().addAll(products);
    }

}
