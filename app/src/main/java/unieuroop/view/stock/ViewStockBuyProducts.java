package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public interface ViewStockBuyProducts {

    /**
     * Initialized a new ViewStockBuyProducts.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * Confirm all the products bought, and put its in the Stock.
     */
    void btnConfimrBuyProductsHandler();

    /**
     * Load for each Supplier their products catalog.
     * @param event
     */
    void listSupplierHandler(MouseEvent event);

    /**
     * Upload the Label that give the info about products buying.
     */
    void uploadInfoLabelBuying();

}
