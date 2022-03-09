package unieuroop.model.department;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;

public interface Department {
    /**
     * Add an amount of products in the Department.
     * @param products
     */
    void addProducts(Map<Product, Integer> products);
    /**
     * assigns new Staff in the Department.
     * @param newStaff
     */
    void addStaff(Staff newStaff);
    /**
     * Remove a Staff from the assigned Department.
     * @param deleteStaff
     */
    void removeStaff(Staff deleteStaff);
    /**
     * Return the Department name.
     * @return departmentName
     */
    String getDepartmentName();
    /**
     * Return products filtered by their quantities.
     * @param quantity 
     * @return productsByQuantities
     */
    Map<Product, Integer> productsByQuantity(Predicate<Integer> quantity); 
    /**
     * Return the entire Staff assigned in the Department.
     * @return staffsDepartment
     */
    Set<Staff> getStaff();
    /**
     * Return all products presents in the Department.
     * @return departmentProducts
     */
    Map<Product, Integer> getAllProducts();
}
