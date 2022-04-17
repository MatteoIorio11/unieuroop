package unieuroop.view.menu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface ViewMainMenu {

    void initialize(URL location, ResourceBundle resources);

    /**
     * 
     * @param event
     */
    void btnDashBoardHandler(ActionEvent event);
    /**
     * 
     * @param event
     */
    void btnStockHandler(ActionEvent event);
    /**
     * 
     * @param event
     */
    void btnSalesHandler(ActionEvent event);
    /**
     * 
     * @param event
     */
    void btnClientsHandler(ActionEvent event);
    /**
     * 
     * @param event
     */
    void btnStaffHandler(ActionEvent event);
    /**
     * 
     * @param event
     */
    void btnDepartmentsHandler(ActionEvent event);
    /**
     * 
     * @param event
     */
    void btnDateAnalyticsHandler(ActionEvent event);
    /**
     * 
     * @param event
     */
    void btnBalancesHandler(ActionEvent event);
    /**
     * 
     * @param event
     */
    void btnAnalyticsHandler(ActionEvent event);
    /**
     * 
     * @param status
     */
    void disableButtons(boolean status);

}