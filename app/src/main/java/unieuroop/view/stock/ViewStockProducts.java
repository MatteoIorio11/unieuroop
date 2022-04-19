package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface ViewStockProducts {

    /**
     * 
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * 
     * @param event
     */
    void buttonAddHandler(ActionEvent event);

}
