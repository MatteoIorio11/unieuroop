package unieuroop.controller.sale;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import unieuroop.model.department.Department;
import unieuroop.model.person.Client;
import unieuroop.model.product.Product;
import unieuroop.model.sale.Sale;

public interface ControllerSale {

    /**
     * 
     * @param product
     * @param department
     * @return return the remaining quantity inside the input department of the specified product
     */
    int getQuantityOf(Product product, Department department);

    /**
     * Reserve the input products of the input department.
     * @param departmentInput
     * @param products
     */
    void reserveProducts(Department departmentInput, Map<Product, Integer> products);

    /**
     * Close the sale with the input client, remember that the input client can be Empty.
     * @param client
     * @return the new sale.
     */
    Optional<Sale> closeSale(Optional<Client> client) throws IOException;

    /**
     * Remove all the reserved products inside the Map and their reserved quantity.
     */
    void clearReservedProducts();
    /**
     * Create the invoice PDF.
     * @param path
     * @param sale
     */
    void createInvoice(String path, Sale sale);

    /**
     * 
     * @return all the reserved products.
     */
    Map<Product, Integer> getReservedProducts();

    /**
     * 
     * @return if there are any reserved products. 
     */
    boolean isNotReserved();

}
