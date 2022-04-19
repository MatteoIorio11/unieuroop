package unieuroop.view.categoryanalytic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface ViewCategoriesSold {

    /**
     * initialize new ViewCategorisSold.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * handler for clearing view.
     * @param event
     */
    void buttonClearEvent(ActionEvent event);

    /**
     * handler for selection of the categories to display in the view.
     * @param event
     */
    void comboSelectCategoryHandler(ActionEvent event);

    /**
     * handler for product selection to make it evident in the chart.
     * @param event
     */
    void listSelectValueHandler(MouseEvent event);

}
