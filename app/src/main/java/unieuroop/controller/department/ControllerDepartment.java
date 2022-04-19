package unieuroop.controller.department;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import unieuroop.model.department.Department;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;

public interface ControllerDepartment {

    Set<Department> getDepartments();

    Set<Product> getProductsOf(Department department);

    Map<Product, Integer> getProductsQuantityOf(Department department);

    void addDepartment(String name, Set<Staff> staffs, Map<Product, Integer> products) throws IOException;

    void removeDepartment(Department department) throws IOException;

    void mergeDepartments(Set<Department> departments, String name) throws IOException;

    void removeProductsFrom(Department inputDepartment, Map<Product, Integer> products) throws IOException;

    void addProductsIn(Department inputDepartment, Map<Product, Integer> products) throws IOException;

    void addStaff(Department inputDepartment, Set<Staff> staffs) throws IOException;

    void removeStaff(Department inputDepartment, Set<Staff> staffs) throws IOException;

    Set<Staff> getStaffOf(Department departmentInput);

    void reserveProduct(Product product, int quantity);

    void removeAllReservedProducts();

    Map<Product, Integer> getReservedProducts();

    void closeAddProducts() throws IOException;

}