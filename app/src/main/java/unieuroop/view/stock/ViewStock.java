package unieuroop.view.stock;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.model.product.Product;
import unieuroop.view.loader.Loader;
import unieuroop.view.menu.ViewMainMenu;

public class ViewStock implements Initializable {

    @FXML
    private TextField txtSearchProducts;
    @FXML
    private Button btnSearchFilters;
    @FXML
    private Button btnBuyProducts;
    @FXML
    private Button btnDeleteProducts;
    @FXML
    private Button btnResetFilters;
    @FXML
    private ListView<Product> listProductsStocked;
    @FXML
    private TextArea txtAreaInfoProducts;

    private final ControllerStockImpl controllerStock;
    private final ViewMainMenu viewMenu;

    public ViewStock(final ViewMainMenu view, final ControllerStockImpl controllerStock) {
        this.controllerStock = controllerStock;
        this.viewMenu = view;
    }

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.loadAllProductsFromStock();
    }

    /**
     * 
     */
    @FXML
    public void listProductsStockedHandler() {
        if (this.listProductsStocked.getSelectionModel().getSelectedItem() != null) {
            this.txtAreaInfoProducts.clear();
            this.txtAreaInfoProducts.setText(this.controllerStock.getInfoByProduct(this.listProductsStocked.getSelectionModel().getSelectedItem()));
        }
    }

    /**
     * 
     */
    @FXML
    public void btnBuyProductsHandler() {
        try {
            final Stage windowBuyProducts = Loader.<ViewStockBuyProducts>loadStage(Pages.STOCK_BUY_PRODUCTS.getPath(), "Buy Products", new ViewStockBuyProducts(this, this.controllerStock), 500, 600);
            final Stage mainStage = (Stage) this.btnBuyProducts.getScene().getWindow();
            mainStage.hide();
            windowBuyProducts.showAndWait();
            mainStage.show();
            this.controllerStock.resetProductsBoughtBuying();
            this.loadAllProductsFromStock();
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
    }

    /**
     * 
     */
    @FXML
    public void btnDeleteProductsHandler() {
        if (this.listProductsStocked.getSelectionModel().getSelectedItem() != null) {
            try {
                this.controllerStock.deleteSelectedProduct(this.listProductsStocked.getSelectionModel().getSelectedItem());
                this.loadAllProductsFromStock();
                final Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Deleted");
                alert.setHeaderText("Product Permantly Deleted");
                alert.setContentText("The selected product has been permanently deleted.\n" + "All products have been reloaded");
                alert.showAndWait();
            } catch (InternalError e) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(e.getMessage());
            }
        } else {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Impossible Delete a Product");
            alert.setContentText("Select first the product to * Permanently Delete * from the Stock.");
            alert.showAndWait();
        }
    }

    /**
     * 
     */
    @FXML
    public void btnSearchFiltersHandler() {
        this.txtAreaInfoProducts.clear();
        try {
            final Stage windowSetFilters = Loader.<ViewStockSetFilters>loadStage(Pages.STOCK_SET_SEARCH_FILTER.getPath(), "Set Search Filters", new ViewStockSetFilters(this, this.controllerStock), 500, 500);
            final Stage mainStage = (Stage) this.btnBuyProducts.getScene().getWindow();
            mainStage.hide();
            windowSetFilters.showAndWait();
            mainStage.show();
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
    }

    /**
     * 
     */
    @FXML
    public void btnResetFiltersHandler() {
        this.loadAllProductsFromStock();
    }

    /**
     * 
     */
    private void loadAllProductsFromStock() {
        this.listProductsStocked.getItems().clear();
        this.txtAreaInfoProducts.clear();
        try {
            this.listProductsStocked.getItems().addAll(this.controllerStock.getProductsStocked().keySet().stream().collect(Collectors.toList()));
        } catch (InputMismatchException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
    }

    /**
     * 
     * @param products
     */
    public void loadProductsByList(final List<Product> products) {
        this.listProductsStocked.getItems().clear();
        try {
            this.listProductsStocked.getItems().addAll(products);
        } catch (InputMismatchException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
    }

}
