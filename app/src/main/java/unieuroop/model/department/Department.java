package unieuroop.model.department;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import unieuroop.model.person.StaffImpl;
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
    void addStaff(StaffImpl newStaff);

    /**
     * Remove a set of Staff from the assigned Department.
     * @param deleteStaff
     */
    void removeStaff(Set<StaffImpl> deleteStaff);

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
    Set<StaffImpl> getStaff();

    /**
     * Return all products presents in the Department.
     * @return departmentProducts
     */
    Map<Product, Integer> getAllProducts();

    /**
     * Return the products and their amount taken from the departments.
     * @param productsTaken
     * @return productsTaken
     */
    Map<Product, Integer> takeProductFromDepartment(Map<Product, Integer> productsTaken);
}
