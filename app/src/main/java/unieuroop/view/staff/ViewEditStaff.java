package unieuroop.view.staff;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface ViewEditStaff {

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

    /**
     * 
     * @param event
     */
    void buttonRemoveHandler(ActionEvent event);

    /**
     * 
     * @param event
     */
    void listSelectUnsignedHandler(MouseEvent event);

    /**
     * 
     * @param event
     */
    void listRemoveStaffHandler(MouseEvent event);

}
