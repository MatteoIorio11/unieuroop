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
import javafx.stage.Stage;
import unieuroop.controller.analytic.ControllerAnalyticImpl;
import unieuroop.controller.client.ControllerClientImpl;
import unieuroop.controller.dashboard.ControllerDashboardImpl;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.shop.ControllerShopImpl;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.model.shop.Shop;
import unieuroop.view.balance.ViewBalance;
import unieuroop.view.categories.ViewCategoriesSold;
import unieuroop.view.client.ViewClient;
import unieuroop.view.dashboard.ViewDashboardImpl;
import unieuroop.view.dates.ViewDateSold;
import unieuroop.view.department.ViewDepartment;
import unieuroop.view.sale.ViewSale;

public final class ViewMainMenu implements Initializable {
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
    @FXML
    private Button btnDateAnalytics;
    private final ControllerShopImpl controller;
    private final Stage stage;
    public ViewMainMenu(final Stage stage, final ControllerShopImpl controller) {
        this.stage = stage;
        this.controller = controller;
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }
    @FXML
    public void btnDashBoardHandler(final ActionEvent event) {
        this.loadPage(Pages.DASHBOARD, new ViewDashboardImpl(new ControllerDashboardImpl(this.controller.getShop())));
    }
    @FXML
    public void btnStockHandler(final ActionEvent event) {
        this.loadPage(Pages.STOCK, null);
    }
    @FXML
    public void btnSalesHandler(final ActionEvent event) {
        this.loadPage(Pages.SALES, new ViewSale(this, this.controller, new ControllerClientImpl(this.controller.getShop()), this.stage));
    }
    @FXML
    public void btnClientsHandler(final ActionEvent event) {
        this.loadPage(Pages.CLIENTS, new ViewClient(this, new ControllerClientImpl(this.controller.getShop())));
    }
    @FXML
    public void btnStaffHandler(final ActionEvent event) {
        this.loadPage(Pages.STAFF, null);
    }
    @FXML
    public void btnDepartmentsHandler(final ActionEvent event) {
        this.loadPage(Pages.DEPARTMENTS, new ViewDepartment(new ControllerDepartmentImpl(this.controller.getShop()),
                new ControllerStaffImpl(this.controller.getShop()), 
                        this.stage));
    }
    @FXML
    public void btnDateAnalyticsHandler(final ActionEvent event) {
        this.loadPage(Pages.MAIN_DATE_SOLD, new ViewDateSold(new ControllerAnalyticImpl(this.controller.getShop())));
    }
    @FXML
    public void btnBalancesHandler(final ActionEvent event) {
        this.loadPage(Pages.BALANCE, new ViewBalance(new ControllerAnalyticImpl(this.controller.getShop())));
    }
    @FXML
    public void btnAnalyticsHandler(final ActionEvent event) {
        this.loadPage(Pages.MAIN_CATEGORIES_SOLD, new ViewCategoriesSold(new ControllerAnalyticImpl(this.controller.getShop())));
    }
    private <X> void loadPage(final Pages page, final X controller) {
        Pane pane;
        try {
            final var loader = new FXMLLoader(getClass().getResource(page.getPath()));
            loader.setController(controller);
            pane = loader.load();
            this.mainPane.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

}
