package unieuroop.view.dateanalytic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public interface ViewDataSold {

    void initialize(URL location, ResourceBundle resources);

    void dateSelectStartHandler(ActionEvent event);

    void dateSelectEndHandler(ActionEvent event);

    void listSelectDateHandler(MouseEvent event);

    void buttonClearAllHandler(ActionEvent event);

}