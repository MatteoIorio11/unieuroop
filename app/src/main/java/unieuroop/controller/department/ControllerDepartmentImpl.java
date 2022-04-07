package unieuroop.controller.department;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import unieuroop.model.department.Department;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;
import unieuroop.model.shop.Shop;

public final class ControllerDepartmentImpl {
    private final Shop shop;

    public ControllerDepartmentImpl(final Shop shop) {
        this.shop = shop;
    }

    public Set<Department> getDepartments() {
        return this.shop.getDepartments();
    }

    public Set<Product> getProductsOf(final Department department) {
        return this.shop.getDepartments().stream()
                .filter((strDepartment) -> strDepartment.equals(department))
                .flatMap((strDepartment) -> strDepartment.getAllProducts().keySet().stream())
                .collect(Collectors.toSet());
    }

    public void addDepartment(final String name, final Set<Staff> staffs, final Map<Product, Integer> products) {
        final var deoartment = new DepartmentImpl(name, staffs, products);
    }
}
