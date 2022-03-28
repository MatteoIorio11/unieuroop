package unieuroop.view.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import unieuroop.controller.serialization.Pages;
import unieuroop.view.balance.ViewBalance;
import unieuroop.view.categories.ViewCategoriesSold;
import unieuroop.view.department.ViewDepartments;
import unieuroop.view.sale.ViewSale;

public final class ViewMainMenu implements Initializable{
    @FXML 
    private BorderPane mainPane;
    @FXML
    private Button btnDashBoard;
    @FXML
    private Button btnStock;
    @FXML
    private Button btnSales;
    @FXML
    private Button btnClients;
    @FXML
    private Button btnStaff;
    @FXML
    private Button btnDepartments;
    @FXML
    private Button btnBalances;
    @FXML
    private Button btnAnalytics;
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        
    }
    @FXML
    private void btnDashBoardHandler(final ActionEvent event) {

    }
    @FXML
    private void btnStockHandler(final ActionEvent event) {
        this.loadPage(Pages.STOCK, null);
    }
    @FXML
    private void btnSalesHandler(final ActionEvent event) {
        this.loadPage(Pages.SALES, new ViewSale(this));
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
        this.loadPage(Pages.BALANCE, new ViewBalance());
    }
    @FXML
    private void btnAnalyticsHandler(final ActionEvent event) {
        this.loadPage(Pages.MAIN_CATEGORIES_SOLD, new ViewCategoriesSold());
    }
    private <X> void loadPage(final Pages page, final X controller) {
        Pane p;
        try {
            final var loader = new FXMLLoader(getClass().getResource(page.getPath()));
            loader.setController(controller);
            p = loader.load();
            this.mainPane.setCenter(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVisibility(final boolean status) {
        this.btnAnalytics.setDisable(status);
        this.btnBalances.setDisable(status);
        this.btnClients.setDisable(status);
        this.btnDashBoard.setDisable(status);
        this.btnDepartments.setDisable(status);
        this.btnSales.setDisable(status);
        this.btnStaff.setDisable(status);
        this.btnStock.setDisable(status);
    }

}
