package unieuroop.view.stock;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.model.product.Product;
import unieuroop.model.supplier.Supplier;
import unieuroop.view.loader.Loader;

public class ViewStockBuyProducts implements Initializable {

    @FXML private ListView<Supplier> listSupplier;
    @FXML private ListView<Pane> listSoldProducts;
    @FXML private Label lblTotalProductsChoose;
    @FXML private Label lblTotalPrice;
    @FXML private Button btnConfimrBuyProducts;

    private final ViewStock viewStock;
    private final ControllerStockImpl controllerStock;

    public ViewStockBuyProducts(final ViewStock viewStock, final ControllerStockImpl controller) {
        this.viewStock = viewStock;
        this.controllerStock = controller;
    }

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listSoldProducts.getItems().clear();
        this.lblTotalPrice.setText("Total Price: ");
        this.lblTotalProductsChoose.setText("Total Products: ");
        this.loadSuppliersList();
    }

    @FXML
    public void btnConfimrBuyProductsHandler() {
        this.controllerStock.addProductsBoughtInStock();
        final Stage stage = (Stage) this.btnConfimrBuyProducts.getScene().getWindow();
        stage.close();
    }

    /**
     * 
     * @param event
     */
    @FXML
    public void listSupplierHandler(final MouseEvent event) {
        final Supplier supplier = this.listSupplier.getSelectionModel().getSelectedItem();
        this.addStockLabelBuyProducts(supplier.getCatalog());
    }

    /**
     * 
     */
    private void loadSuppliersList() {
        this.listSupplier.getItems().clear();
        this.listSupplier.getItems().addAll(this.controllerStock.getSuppliers());
    }

    private void addStockLabelBuyProducts(final Map<Product, Double> productsSold) {
        this.listSoldProducts.getItems().clear();
        for (final Map.Entry<Product, Double> entryProductSold : productsSold.entrySet()) {
            try {
                final var pane = Loader.<ViewStockLabelBuyProducts>loadPane(Pages.LABEL_PRODUCT.getPath(), new ViewStockLabelBuyProducts(entryProductSold, this, this.controllerStock));
                this.listSoldProducts.getItems().add(pane);
            } catch (IOException e) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(e.getMessage());
            }
        }
    }

}
