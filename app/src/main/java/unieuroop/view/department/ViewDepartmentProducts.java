package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public interface ViewDepartmentProducts {

    /**
     * 
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * 
     * @param event
     * @throws IOException
     */
    void buttonRemoveHandler(ActionEvent event) throws IOException;

}
