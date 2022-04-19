package unieuroop.view.dateanalytic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface ViewDataSold {

    /**
     * initialize new ViewDataSold.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * handler selection of the start date.
     * @param event
     */
    void dateSelectStartHandler(ActionEvent event);

    /**
     * handler selection of the end date.
     * @param event
     */
    void dateSelectEndHandler(ActionEvent event);

    /**
     * handler for the product selection to make it evident in the chart.
     * @param event
     */
    void listSelectDateHandler(MouseEvent event);

    /**
     * handler to clear the start and end date.
     * @param event
     */
    void buttonClearAllHandler(ActionEvent event);
}
