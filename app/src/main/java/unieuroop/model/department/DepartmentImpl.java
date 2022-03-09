package unieuroop.model.department;

import java.util.Map;
import java.util.Set;

import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;

public class DepartmentImpl implements Department{
    private final String name;
    private final Set<Staff> staff;
    private final Map<Product,Integer> products;

    public DepartmentImpl(final String nameDepartment, final Set<Staff> staff, 
            final Map<Product, Integer> products) {
        this.name = nameDepartment;
        this.staff = Set.copyOf(staff);
        this.products = Map.copyOf(products);
    }

    @Override
    public void addProducts(final Map<Product, Integer> products) {
        
    }

    @Override
    public void addStaff(final Staff newStaff) {
        
    }

    @Override
    public void removeStaff(final Staff deleteStaff) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getDepartmentName() {
        return this.name;
    }

    @Override
    public Map<Product, Integer> productsbyQuantity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Staff> getStaff() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<Product, Integer> getAllProducts() {
        // TODO Auto-generated method stub
        return null;
    }

}
