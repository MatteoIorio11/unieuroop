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
import unieuroop.view.analytic.ViewAnalytic;
import unieuroop.view.department.ViewDepartments;

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
        this.loadPage(Pages.MAIN_DATE_SOLD, null);
    }
    @FXML
    private void btnClientsHandler(final ActionEvent event) {
        this.loadPage(Pages.CLIENTS, null);
    }
    @FXML
    private void btnStaffHandler(final ActionEvent event) {
        this.loadPage(Pages.STAFF, null);
    }
    @FXML
    private void btnDepartmentsHandler(final ActionEvent event) {
        this.loadPage(Pages.DEPARTMENTS, new ViewDepartments());
    }
    @FXML
    private void btnBalancesHandler(final ActionEvent event) {
        this.loadPage(Pages.BALANCE, new ViewAnalytic());
    }
    @FXML
    private void btnAnalyticsHandler(final ActionEvent event) {
        this.loadPage(Pages.MAIN_CATEGORIES_SOLD, null);
    }
    private <X>void loadPage(final Pages page, X controller) {
        Pane p;
        try {
            var loader = new FXMLLoader(getClass().getResource(page.getPath()));
            loader.setController(controller);
            p = loader.load();
            this.mainPane.setCenter(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
