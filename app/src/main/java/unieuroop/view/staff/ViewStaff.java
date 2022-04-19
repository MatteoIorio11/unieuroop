package unieuroop.view.staff;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public interface ViewStaff extends Initializable {

    /**
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
    void buttonEditHandler(ActionEvent event);
    /**
     * 
     * @param event
     */
    void buttonDeleteHandler(ActionEvent event);
    /**
     * 
     * @param event
     */
    void listSelectHandler(MouseEvent event);

}