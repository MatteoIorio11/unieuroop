package unieuroop.view.sale;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface ViewLabelSale {

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
