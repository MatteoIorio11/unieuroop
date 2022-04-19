package unieuroop.controller.department;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import unieuroop.model.department.Department;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;

public interface ControllerDepartment {

    /**
     * 
     * @return
     */
    Set<Department> getDepartments();

    /**
     * 
     * @param department
     * @return
     */
    Set<Product> getProductsOf(Department department);

    /**
     * 
     * @param department
     * @return
     */
    Map<Product, Integer> getProductsQuantityOf(Department department);

    /**
     * 
     * @param name
     * @param staffs
     * @param products
     * @throws IOException
     */
    void addDepartment(String name, Set<Staff> staffs, Map<Product, Integer> products) throws IOException;

    /**
     * 
     * @param department
     * @throws IOException
     */
    void removeDepartment(Department department) throws IOException;

    /**
     * 
     * @param departments
     * @param name
     * @throws IOException
     */
    void mergeDepartments(Set<Department> departments, String name) throws IOException;

    /**
     * 
     * @param inputDepartment
     * @param products
     * @throws IOException
     */
    void removeProductsFrom(Department inputDepartment, Map<Product, Integer> products) throws IOException;

    /**
     * 
     * @param inputDepartment
     * @param products
     * @throws IOException
     */
    void addProductsIn(Department inputDepartment, Map<Product, Integer> products) throws IOException;

    /**
     * 
     * @param inputDepartment
     * @param staffs
     * @throws IOException
     */
    void addStaff(Department inputDepartment, Set<Staff> staffs) throws IOException;

    /**
     * 
     * @param inputDepartment
     * @param staffs
     * @throws IOException
     */
    void removeStaff(Department inputDepartment, Set<Staff> staffs) throws IOException;

    /**
     * 
     * @param departmentInput
     * @return
     */
    Set<Staff> getStaffOf(Department departmentInput);

    /**
     * 
     * @param product
     * @param quantity
     */
    void reserveProduct(Product product, int quantity);

    /**
     * 
     */
    void removeAllReservedProducts();

    /**
     * 
     * @return
     */
    Map<Product, Integer> getReservedProducts();

    /**
     * 
     * @throws IOException
     */
    void closeAddProducts() throws IOException;
}
