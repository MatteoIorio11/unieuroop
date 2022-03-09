package unieuroop.model.department;

import java.util.Map;
import java.util.Set;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;

public interface Department {
    void addProducts(Map<Product, Integer> products);
    
    void addStaff(Staff newStaff);
    
    void removeStaff(Staff deleteStaff);
    
    String getDepartmentName();
    
    Map<Product, Integer> productsbyQuantity(); //Con Function?
    
    Set<Staff> getStaff();
    
    Map<Product, Integer> getAllProducts();
}
