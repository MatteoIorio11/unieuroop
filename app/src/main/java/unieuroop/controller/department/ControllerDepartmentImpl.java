package unieuroop.controller.department;

import java.util.Set;
import java.util.stream.Collectors;

import unieuroop.model.department.Department;
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
}
