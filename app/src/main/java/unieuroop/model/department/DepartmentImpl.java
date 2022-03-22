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
     * Return the products present in the department filter by their quantities.
     */
    @Override
    public Map<Product, Integer> productsByQuantity(final Predicate<Integer> quantity) {
        final Map<Product, Integer> productsFilter = new HashMap<>();
        for (final Product product : this.products.keySet()) {
            if (quantity.test(this.products.get(product))) {
                productsFilter.put(product, this.products.get(product));
            }
        }
        return productsFilter;
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
        if (!checkProductsTaken(productsTaken)) {
            throw new IllegalArgumentException();
        }
        for (final Product product : productsTaken.keySet()) {
            this.products.put(product, this.products.get(product) - productsTaken.get(product));
        }
        return productsTaken;
    }

    /**
     * Check if is possible take each products and their amount from the department.
     * @param productsTaken
     * @return boolean
     */
    private boolean checkProductsTaken(final Map<Product, Integer> productsTaken) {
        for (final Product productTake : productsTaken.keySet()) {
            if (!this.products.containsKey(productTake) || this.products.get(productTake) < productsTaken.get(productTake)) {
                return false;
            }
        }
        return true;
    }

}
