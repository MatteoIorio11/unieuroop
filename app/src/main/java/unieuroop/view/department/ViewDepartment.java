package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public interface ViewDepartment {

    void initialize(URL location, ResourceBundle resources);

    void listSelectDepartmentHandler(MouseEvent event);

    void buttonAddDepartmentHandler(ActionEvent event);

    void buttonDeleteDepartmentHandler(ActionEvent event);

    void buttonMergeDepartmentsHandler(ActionEvent event);

}