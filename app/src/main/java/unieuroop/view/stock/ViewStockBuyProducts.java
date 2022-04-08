package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import unieuroop.model.product.Product;
import unieuroop.model.supplier.Supplier;

public class ViewStockBuyProducts extends Stage implements Initializable {

    @FXML
    private ListView<Supplier> listSupplier;
    @FXML
    private ListView<Product> listSoldProducts;
    @FXML
    private Label lblTotalProductsChoose;
    @FXML
    private Label lblTotalPrice;
    @FXML
    private Button btnConfimrBuyProducts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

}
