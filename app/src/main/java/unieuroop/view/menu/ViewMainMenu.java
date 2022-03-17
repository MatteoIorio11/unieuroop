package unieuroop.view.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import unieuroop.controller.serialization.Pages;

public class ViewMainMenu implements Initializable{
    @FXML 
    private BorderPane mainPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    @FXML
    private void btn_DashBoard_action(ActionEvent event) {

    }
    @FXML
    private void btn_Stock_action(ActionEvent event) {

    }
    @FXML
    private void btn_Sale_action(ActionEvent event) {

    }
    @FXML
    private void btn_Department_action(ActionEvent event) {

    }
    @FXML
    private void btn_Analytics_action(ActionEvent event) {
        this.LoadPage(Pages.ANALYTICS);
    }
    private void LoadPage(Pages page) {
        Pane p;
        try {
            p = FXMLLoader.load(getClass().getResource(page.getPath()));
            this.mainPane.setCenter(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
