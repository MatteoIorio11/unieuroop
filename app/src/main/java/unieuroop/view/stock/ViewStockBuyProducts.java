package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public interface ViewStockBuyProducts {

    /**
     * 
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