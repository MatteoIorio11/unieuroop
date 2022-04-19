package unieuroop.view.dashboard;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public interface ViewDashboard {

    void initialize(URL location, ResourceBundle resources);

    void lstSalesSelectClientHandler(MouseEvent event);

}