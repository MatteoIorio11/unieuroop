package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public interface ViewStockBuyProducts {

    /**
     * 
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    void btnConfimrBuyProductsHandler();

    /**
     * 
     * @param event
     */
    void listSupplierHandler(MouseEvent event);

    /**
     * 
     */
    void uploadInfoLabelBuying();

}
