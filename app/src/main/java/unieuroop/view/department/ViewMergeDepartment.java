package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public interface ViewMergeDepartment {

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
    void buttonMergeDepartmentHandler(ActionEvent event);

}
