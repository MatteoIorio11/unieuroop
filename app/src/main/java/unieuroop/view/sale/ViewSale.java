package unieuroop.view.sale;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public interface ViewSale {

    void initialize(URL location, ResourceBundle resources);

    void comboSelectDepartment(ActionEvent event);

    void buttonSellHandler(ActionEvent event);

    void buttonQuitHandler(ActionEvent event);

    ListView<String> getListView();

}