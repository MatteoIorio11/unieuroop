package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface ViewStockProducts {

    /**
     * Initialized new ViewStockProducts.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * Add new Products from the Stock in the Department.
     * @param event
     */
    void buttonAddHandler(ActionEvent event);

}
