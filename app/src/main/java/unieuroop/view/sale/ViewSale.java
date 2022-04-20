package unieuroop.view.sale;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

public interface ViewSale {

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
    void comboSelectDepartment(ActionEvent event);

    /**
     * 
     * @param event
     */
    void buttonSellHandler(ActionEvent event);

    /**
     * 
     * @param event
     */
    void buttonQuitHandler(ActionEvent event);

    /**
     * 
     * @return To Do.
     */
    ListView<String> getListView();
}
