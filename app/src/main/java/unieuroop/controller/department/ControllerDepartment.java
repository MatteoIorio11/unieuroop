package unieuroop.controller.department;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import unieuroop.model.department.Department;
import unieuroop.model.person.StaffImpl;
import unieuroop.model.product.Product;

public interface ControllerDepartment {

    /**
     * Return all the Departments present in the Shop.
     * @return departments.
     */
    Set<Department> getDepartments();

    /**
     * Return all the products contained in the specify department.
     * @param department
     * @return departmentProducts.
     */
    Set<Product> getProductsOf(Department department);

    /**
     * Return all the product contained in the specify department with their quantities.
     * @param department
     * @return departmentProductsAndQuantities.
     */
    Map<Product, Integer> getProductsQuantityOf(Department department);

    /**
     * Add a new Department in the Shop.
     * @param name
     * @param staffs
     * @param products
     * @throws IOException
     */
    void addDepartment(String name, Set<StaffImpl> staffs, Map<Product, Integer> products) throws IOException;

    /**
     * Remove a Department from the Shop.
     * @param department
     * @throws IOException
     */
    void removeDepartment(Department department) throws IOException;

    /**
     * Merge two already present department in the shop.
     * @param departments
     * @param name
     * @throws IOException
     */
    void mergeDepartments(Set<Department> departments, String name) throws IOException;

    /**
     * Remove the amount of each product contained in a specify Department.
     * @param inputDepartment
     * @param products
     * @throws IOException
     */
    void removeProductsFrom(Department inputDepartment, Map<Product, Integer> products) throws IOException;

    /**
     * Add the amount of each product contained in a specify Department.
     * @param inputDepartment
     * @param products
     * @throws IOException
     */
    void addProductsIn(Department inputDepartment, Map<Product, Integer> products) throws IOException;

    /**
     * Add new Staff in a specify Department.
     * @param inputDepartment
     * @param staffs
     * @throws IOException
     */
    void addStaff(Department inputDepartment, Set<StaffImpl> staffs) throws IOException;

    /**
     * Remove new Staff in a specify Department.
     * @param inputDepartment
     * @param staffs
     * @throws IOException
     */
    void removeStaff(Department inputDepartment, Set<StaffImpl> staffs) throws IOException;

    /**
     * Return Department's Staff.
     * @param departmentInput
     * @return departmentStaff.
     */
    Set<StaffImpl> getStaffOf(Department departmentInput);

    /**
     * Reserve the selected product and their amount from the stock.
     * @param product
     * @param quantity
     */
    void reserveProduct(Product product, int quantity);

    /**
     * Reset all the reserved Products.
     */
    void removeAllReservedProducts();

    /**
     * Return the reserved products and their quantities.
     * @return reservedProducts.
     */
    Map<Product, Integer> getReservedProducts();

    /**
     * Move all the reserved products from the Stock to a Department.
     * @throws IOException
     */
    void closeAddProducts() throws IOException;
}
