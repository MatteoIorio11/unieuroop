package unieuroop.model.department;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;

public class DepartmentImpl implements Department{
    private final String name;
    private final Set<Staff> staff;
    private final Map<Product,Integer> products;

    public DepartmentImpl(final String nameDepartment, final Set<Staff> staff, 
            final Map<Product, Integer> products) {
        this.name = nameDepartment;
        this.staff = new HashSet<>(Set.copyOf(staff));
        this.products = new HashMap<>(Map.copyOf(products));
    }

    @Override
    public void addProducts(final Map<Product, Integer> products) {
        
    }

    @Override
    public void addStaff(final Staff newStaff) {
        if (!this.staff.contains(newStaff)) {
            this.staff.add(newStaff);
        } else {
            throw new IllegalArgumentException("The staff : " + newStaff.toString() + " already exist.");
        }
    }

    @Override
    public void removeStaff(final Staff deleteStaff) {
        
    }

    @Override
    public String getDepartmentName() {
        return this.name;
    }

    @Override
    public Map<Product, Integer> productsByQuantity(final Predicate<Integer> quantity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Staff> getStaff() {
        return Set.copyOf(this.staff);
    }

    @Override
    public Map<Product, Integer> getAllProducts() {
        return Map.copyOf(this.products);
    }

}
