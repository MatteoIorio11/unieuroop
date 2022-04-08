package unieuroop.view.stock;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.model.product.Product;
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
    private RadioButton rdbtnIncreasing;
    @FXML
    private RadioButton rdbtnDecreasing;
    @FXML
    private ListView<Pane> listProductsStocked;

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
    public void initialize(URL location, ResourceBundle resources) {
        addStockLabel(this.controllerStock.getProductsStocked());
    }

    @FXML
    public void btnBuyProductsHandler() {
        listProductsStocked.getItems().clear();
        final Pane pane;
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    @FXML
    public void btnDeleteProductsHandler() {
        
    }

    @FXML
    public void btnSearchFiltersHandler() {
        
    }

    @FXML
    public void rdbtnIncreasingHandler() {
        
    }

    @FXML
    public void rdbtnDecreasingHandler() {
        
    }

    /**
     * 
     * @param products
     */
    private void addStockLabel(final Map<Product, Integer> products) {
        listProductsStocked.getItems().clear();
        for (final Map.Entry<Product, Integer> entryProduct : products.entrySet()) {
            Pane pane;
            try {
                final var loaderLabel = new FXMLLoader(getClass().getResource(Pages.STOCK_LABEL_FOR_STOCK.getPath()));
                loaderLabel.setController(new ViewStockLabelProduct(entryProduct, this, this.controllerStock));
                pane = loaderLabel.load();
                this.listProductsStocked.getItems().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
