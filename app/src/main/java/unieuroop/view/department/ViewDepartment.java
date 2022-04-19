package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface ViewDepartment {

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
    void listSelectDepartmentHandler(MouseEvent event);

    /**
     * 
     * @param event
     */
    void buttonAddDepartmentHandler(ActionEvent event);

    /**
     * 
     * @param event
     */
    void buttonDeleteDepartmentHandler(ActionEvent event);

    /**
     * 
     * @param event
     */
    void buttonMergeDepartmentsHandler(ActionEvent event);

}
