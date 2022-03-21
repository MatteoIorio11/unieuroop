package unieuroop.model.department;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;

public final class DepartmentImpl implements Department {

    private final String name;
    private final Set<Staff> staff;
    private final Map<Product, Integer> products;

    public DepartmentImpl(final String nameDepartment, final Set<Staff> staff, final Map<Product, Integer> products) {
        this.name = nameDepartment;
        this.staff = new HashSet<>(staff);
        this.products = new HashMap<>(products);
    }

    /**
     * Add products and their amount to the department.
     */
    @Override
    public void addProducts(final Map<Product, Integer> products) {
        for (final Product product : products.keySet()) {
            this.products.merge(product, products.get(product), (quantityPresent, newQuantity) -> quantityPresent + newQuantity);
        }
    }

    /**
     * Add Staff to the department.
     */
    @Override
    public void addStaff(final Staff newStaff) {
        if (!this.staff.contains(newStaff)) {
            this.staff.add(newStaff);
        } else {
            throw new IllegalArgumentException("The staff : " + newStaff.toString() + " already exist.");
        }
    }

    /**
     * Remove the Staff assigned to the department.
     */
    @Override
    public void removeStaff(final Staff deleteStaff) {
        if (this.staff.contains(deleteStaff)) {
            this.staff.remove(deleteStaff);
        } else {
            throw new IllegalArgumentException("The staff : " + deleteStaff.toString() + " not exist.");
        }
    }

    /**
     * Return the department name.
     */
    @Override
    public String getDepartmentName() {
        return this.name;
    }

    /**
     * 
     */
    @Override
    public Map<Product, Integer> productsByQuantity(final Predicate<Integer> quantity) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Return all the staff assigned to the department.
     */
    @Override
    public Set<Staff> getStaff() {
        return Set.copyOf(this.staff);
    }

    /**
     * Return all the products present in the departments.
     */
    @Override
    public Map<Product, Integer> getAllProducts() {
        return Map.copyOf(this.products);
    }

    /**
     * Return the products taken from the department.
     */
    @Override
    public Map<Product, Integer> takeProductFromDepartment(final Map<Product, Integer> productsTaken) {
        if (!checkProductTaken(productsTaken)) {
            throw new IllegalArgumentException();
        }
        for (final Product product : productsTaken.keySet()) {
            this.products.put(product, this.products.get(product) - productsTaken.get(product));
        }
        return productsTaken;
    }

    /**
     * Check if is possible take each products and their amount from the stock.
     * @param productsTaken
     * @return boolean
     */
    private boolean checkProductTaken(final Map<Product, Integer> productsTaken) {
        for (final Product productTake : productsTaken.keySet()) {
            if (!this.products.containsKey(productTake) || this.products.get(productTake) < productsTaken.get(productTake)) {
                return false;
            }
        }
        return true;
    }

}
