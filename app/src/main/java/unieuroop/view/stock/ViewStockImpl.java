package unieuroop.view.stock;

import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.stock.ControllerStock;
import unieuroop.model.product.Product;
import unieuroop.view.loader.Loader;

public final class ViewStockImpl implements Initializable, ViewStock {

    @FXML private TextField txtSearchProducts;
    @FXML private Button btnSearchFilters;
    @FXML private Button btnBuyProducts;
    @FXML private Button btnDeleteProducts;
    @FXML private Button btnResetFilters;
    @FXML private ListView<Product> listProductsStocked;
    @FXML private TextArea txtAreaInfoProducts;

    private final ControllerStock controllerStock;

    public ViewStockImpl(final ControllerStock controllerStock) {
        this.controllerStock = controllerStock;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.loadAllProductsFromStock();
    }

    @Override
    @FXML
    public void listProductsStockedHandler() {
        final var selected = this.listProductsStocked.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(selected)) {
            this.txtAreaInfoProducts.clear();
            this.txtAreaInfoProducts.setText(this.controllerStock.getInfoByProduct(selected));
        }
    }

    @Override
    @FXML
    public void btnBuyProductsHandler() {
        try {
            final Stage windowBuyProducts = Loader.<ViewStockBuyProducts>loadStage(Pages.STOCK_BUY_PRODUCTS.getPath(), "Buy Products", new ViewStockBuyProductsImpl(this.controllerStock), 500, 600);
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

    @Override
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

    @Override
    @FXML
    public void btnSearchFiltersHandler() {
        this.txtAreaInfoProducts.clear();
        try {
            final Stage windowSetFilters = Loader.<ViewStockSetFilters>loadStage(Pages.STOCK_SET_SEARCH_FILTER.getPath(), "Set Search Filters", new ViewStockSetFiltersImpl(this, this.controllerStock), 500, 500);
            final Stage mainStage = (Stage) this.btnBuyProducts.getScene().getWindow();
            mainStage.hide();
            windowSetFilters.showAndWait();
            mainStage.show();
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
    }

    @Override
    @FXML
    public void btnResetFiltersHandler() {
        this.loadAllProductsFromStock();
    }

    @Override
    @FXML
    public void txtSearchProductsHandler() {
       if (this.txtAreaInfoProducts.getText() == null || this.txtSearchProducts.getText().isBlank()) {
           this.loadAllProductsFromStock();
       } else {
           this.loadProductsByList(this.controllerStock.getProductsStocked().keySet().stream()
                   .filter((product) -> product.getName().contains(this.txtSearchProducts.getText())).collect(Collectors.toList()));
       }
    }

    @Override
    public void loadProductsByList(final List<Product> products) {
        this.listProductsStocked.getItems().clear();
        try {
            this.listProductsStocked.getItems().addAll(products);
        } catch (InputMismatchException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
    }

    /**
     * Load the ListView with all the products present in the Stock.
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
}
