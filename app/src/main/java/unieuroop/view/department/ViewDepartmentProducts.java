package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public interface ViewDepartmentProducts {

    void initialize(URL location, ResourceBundle resources);

    void buttonRemoveHandler(ActionEvent event) throws IOException;

}