package unieuroop.model.department;

import java.util.Map;
import java.util.Set;

import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;
public interface Department {

    /**
     * We call this method if we want to add a new Product inside the Department.
     * @param product that will be add in the Department
     * @param quantity of the product that will be add inside Department
     */
    void addProduct(Product product, int quantity);
    /**
     * Remove a specified product with a specified quantity.
     * @param product that we want to remove from department
     * @param quantity of we will remove from Department
     */
    void removeProduct(Product product, int quantity);
    /**
     * 
     * @return the Set of all Staff inside the department
     */
    Set<Staff> getStaff();
    /**
     * 
     * @return the Set of all Products inside the Department
     */
    Set<Product> getAllProducts();
    /**
     * 
     * @return the Map of every product inside the Department with their quantity
     */
    Map<Product, Integer> getProducts();
}
