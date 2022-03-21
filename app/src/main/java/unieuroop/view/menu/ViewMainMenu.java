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
    public void initialize(final URL location, final ResourceBundle resources) {
        
    }
    @FXML
    private void btnDashBoardHandler(final ActionEvent event) {

    }
    @FXML
    private void btnStockHandler(final ActionEvent event) {

    }
    @FXML
    private void btnSalesHandler(final ActionEvent event) {
        this.loadPage(Pages.MAIN_DATE_SOLD);
    }
    @FXML
    private void btnClientsHandler(final ActionEvent event) {
        this.loadPage(Pages.MAIN_DATE_SOLD);
    }
    @FXML
    private void btnStaffHandler(final ActionEvent event) {
        this.loadPage(Pages.MAIN_CATEGORIES_SOLD);
    }
    @FXML
    private void btnDepartmentsHandler(final ActionEvent event) {
        this.loadPage(Pages.DEPARTMENTS);
    }
    @FXML
    private void btnBalancesHandler(final ActionEvent event) {
        this.loadPage(Pages.BALANCE);
    }
    @FXML
    private void btnAnalyticsHandler(final ActionEvent event) {
        this.loadPage(Pages.MAIN_CATEGORIES_SOLD);
    }
    private void loadPage(final Pages page) {
        Pane p;
        try {
            p = FXMLLoader.load(getClass().getResource(page.getPath()));
            this.mainPane.setCenter(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
