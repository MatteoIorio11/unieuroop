package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public interface ViewAddDepartment {

    void initialize(URL location, ResourceBundle resources);

    void buttonAddHandler(ActionEvent event);

    void buttonExitHandler(ActionEvent event);

    void listSelectStaffHandler(MouseEvent event);

}