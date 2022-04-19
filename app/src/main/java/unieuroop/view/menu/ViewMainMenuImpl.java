package unieuroop.view.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import unieuroop.controller.analytic.ControllerAnalyticImpl;
import unieuroop.controller.client.ControllerClientImpl;
import unieuroop.controller.dashboard.ControllerDashboardImpl;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.sale.ControllerSaleImpl;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.shop.ControllerShop;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.view.balance.ViewBalanceImpl;
import unieuroop.view.categoryanalytic.ViewCategoriesSoldImpl;
import unieuroop.view.client.ViewClientImpl;
import unieuroop.view.dashboard.ViewDashboardImpl;
import unieuroop.view.dateanalytic.ViewDateSoldImpl;
import unieuroop.view.department.ViewDepartmentImpl;
import unieuroop.view.loader.Loader;
import unieuroop.view.sale.ViewSaleImpl;
import unieuroop.view.staff.ViewStaffImpl;
import unieuroop.view.stock.ViewStockImpl;

public final class ViewMainMenuImpl implements Initializable, ViewMainMenu {

    @FXML private BorderPane mainPane;
    @FXML private Button btnDashBoard;
    @FXML private Button btnStock;
    @FXML private Button btnSales;
    @FXML private Button btnClients;
    @FXML private Button btnStaff;
    @FXML private Button btnDepartments;
    @FXML private Button btnBalances;
    @FXML private Button btnAnalytics;
    @FXML private Button btnDateAnalytics;

    private final ControllerShop controller;

    public ViewMainMenuImpl(final ControllerShop controllerShop) {
        this.controller = controllerShop;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.loadPage(Pages.DASHBOARD, new ViewDashboardImpl(new ControllerDashboardImpl(this.controller.getShop()), 
                new ControllerAnalyticImpl(this.controller.getShop())));
    }

    @Override
    @FXML
    public void btnDashBoardHandler(final ActionEvent event) {
        this.loadPage(Pages.DASHBOARD, new ViewDashboardImpl(new ControllerDashboardImpl(this.controller.getShop()), 
                new ControllerAnalyticImpl(this.controller.getShop())));
    }

    @Override
    @FXML
    public void btnStockHandler(final ActionEvent event) {
        this.loadPage(Pages.STOCK, new ViewStockImpl(new ControllerStockImpl(this.controller.getShop())));
    }

    @Override
    @FXML
    public void btnSalesHandler(final ActionEvent event) {
        this.loadPage(Pages.SALES, new ViewSaleImpl(this, new ControllerClientImpl(this.controller.getShop()), 
                new ControllerDepartmentImpl(this.controller.getShop()), 
                new ControllerSaleImpl(this.controller.getShop())));
    }

    @Override
    @FXML
    public void btnClientsHandler(final ActionEvent event) {
        this.loadPage(Pages.CLIENTS, new ViewClientImpl(new ControllerClientImpl(this.controller.getShop())));
    }

    @Override
    @FXML
    public void btnStaffHandler(final ActionEvent event) {
        this.loadPage(Pages.STAFF, new ViewStaffImpl(new ControllerStaffImpl(this.controller.getShop())));
    }

    @Override
    @FXML
    public void btnDepartmentsHandler(final ActionEvent event) {
        this.loadPage(Pages.DEPARTMENTS, new ViewDepartmentImpl(new ControllerDepartmentImpl(this.controller.getShop()),
                new ControllerStaffImpl(this.controller.getShop()), new ControllerStockImpl(this.controller.getShop())));
    }

    @Override
    @FXML
    public void btnDateAnalyticsHandler(final ActionEvent event) {
        this.loadPage(Pages.MAIN_DATE_SOLD, new ViewDateSoldImpl(new ControllerAnalyticImpl(this.controller.getShop())));
    }

    @Override
    @FXML
    public void btnBalancesHandler(final ActionEvent event) {
        this.loadPage(Pages.BALANCE, new ViewBalanceImpl(new ControllerAnalyticImpl(this.controller.getShop())));
    }

    @Override
    @FXML
    public void btnAnalyticsHandler(final ActionEvent event) {
        this.loadPage(Pages.MAIN_CATEGORIES_SOLD, new ViewCategoriesSoldImpl(new ControllerAnalyticImpl(this.controller.getShop())));
    }

    @Override
    public void disableButtons(final boolean status) {
        this.btnAnalytics.setDisable(status);
        this.btnBalances.setDisable(status);
        this.btnClients.setDisable(status);
        this.btnDashBoard.setDisable(status);
        this.btnDepartments.setDisable(status);
        this.btnSales.setDisable(status);
        this.btnStaff.setDisable(status);
        this.btnStock.setDisable(status);
        this.btnDateAnalytics.setDisable(status);
    }

    /**
     * 
     * @param <X>
     * @param page
     * @param controller
     */
    private <X> void loadPage(final Pages page, final X controller) {
        Pane pane;
        try {
            pane = Loader.loadPane(page.getPath(), controller);
            this.mainPane.setCenter(pane);
        } catch (IOException e) {
            final Alert errorMessage = new Alert(AlertType.ERROR);
            errorMessage.setContentText(e.getMessage());
        }
    }
}
