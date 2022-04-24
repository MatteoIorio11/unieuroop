package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;


public interface ViewStockLabelBuyProducts {

    /**
     * Initialized new ViewStockLabelProducts.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * Handler for every products checked, take and add in the sale.
     */
    void chkboxProductBoughtHandler();

}
