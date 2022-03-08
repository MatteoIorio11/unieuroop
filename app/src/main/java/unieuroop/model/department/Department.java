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
     * With this method we can add a new the Staff inside the Department.
     * @param staff we will add in the Department
     */
    void addStaff(Staff staff);
    /**
     * Remove a specified Staff inside  the Department.
     * @param staff we will remove from the Department
     */
    void removeStaff(Staff staff);
    /**
     * Get all the staff inside the Department, the Set can be empty if nobody work inside this Department
     * this can happen if the Department is new.
     * @return the Set of all Staff inside the department
     */
    Set<Staff> getStaff();
    /**
     * Return all the products inside the Department  without their quantity.
     * @return the Set of all Products inside the Department
     */
    Set<Product> getAllProducts();
    /**
     * If we want more information about the Products inside the Department we can call this method that
     * return a Map with the Product and it's quantity.
     * @return the Map of every product inside the Department with their quantity
     */
    Map<Product, Integer> getProducts();
}
