package unieuroop.controller.dashboard;


import java.util.List;
import unieuroop.model.sale.Sale;

public interface ControllerDashboard {

    /**
     * 
     * @return the ammount of staff of the shop.
     */
    int getStaff();

    /**
     * 
     * @return the ammount of Clients of the shop.
     */
    int getClients();

    /**
     * 
     * @return the ammount of Suppliers of the shop.
     */
    int getSuppliers();

    /**
     * 
     * @return the ammount of Departments of the shop.
     */
    int getDepartments();

    /**
     * 
     * @return an ordered list of sales by their dates.
     */
    List<Sale> getSales();

}
