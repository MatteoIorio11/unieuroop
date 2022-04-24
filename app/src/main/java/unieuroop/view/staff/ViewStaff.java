package unieuroop.view.staff;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public interface ViewStaff extends Initializable {

    /**
     * initialize new ViewStaff.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * handler for add staff.
     * @param event
     */
    void buttonAddHandler(ActionEvent event);

    /**
     * handler for edit staff.
     * @param event
     */
    void buttonEditHandler(ActionEvent event);

    /**
     * handler for delete staff.
     * @param event
     */
    void buttonDeleteHandler(ActionEvent event);

    /**
     * handler for selected list.
     * @param event
     */
    void listSelectHandler(MouseEvent event);

}
