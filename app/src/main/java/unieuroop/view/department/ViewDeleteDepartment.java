package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public interface ViewDeleteDepartment {

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
    void listDeleteDepartment(MouseEvent event);

}
