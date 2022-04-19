package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public interface ViewLabelDepartment {

    void initialize(URL location, ResourceBundle resources);

    void buttonEditStaffHandler(ActionEvent event);

    void buttonEditProductsHandler(ActionEvent event);

}