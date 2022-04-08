package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import unieuroop.controller.stock.ControllerStockImpl;
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

    private ViewStock viewStock;
    private final ControllerStockImpl controllerStock;

    public ViewStockBuyProducts(final ViewStock viewStock, final ControllerStockImpl controller) {
        this.viewStock = viewStock;
        this.controllerStock = controller;
    }

    /**
     * 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listSoldProducts.getItems().clear();
        this.lblTotalPrice.setText("Total Price: ");
        this.lblTotalProductsChoose.setText("Total Products: ");
        loadSuppliersList();
    }

    @FXML
    public void btnConfimrBuyProductsHandler() {
        
    }

    /**
     * 
     */
    private void loadSuppliersList() {
        this.listSupplier.getItems().clear();
        this.listSupplier.getItems().addAll(this.controllerStock.getSuppliers());
    }

}
