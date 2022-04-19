package unieuroop.view.staff;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public interface ViewEditStaff {

    void initialize(URL location, ResourceBundle resources);

    void buttonAddHandler(ActionEvent event);

    void buttonRemoveHandler(ActionEvent event);

    void listSelectUnsignedHandler(MouseEvent event);

    void listRemoveStaffHandler(MouseEvent event);

}