package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface ViewAddDepartment {

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
    void buttonExitHandler(ActionEvent event);

    /**
     * 
     * @param event
     */
    void listSelectStaffHandler(MouseEvent event);

}
