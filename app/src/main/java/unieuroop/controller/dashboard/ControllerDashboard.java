package unieuroop.controller.dashboard;


import java.util.Set;

import unieuroop.model.sale.Sale;

public interface ControllerDashboard {

    int getStaff();

    int getClients();

    int getSuppliers();

    int getDepartments();

    Double getStockPrice();

    Double getShopEarnings();

    Double getTotalSpent();

    Set<Sale> getSales();

    boolean isEarning();

}
