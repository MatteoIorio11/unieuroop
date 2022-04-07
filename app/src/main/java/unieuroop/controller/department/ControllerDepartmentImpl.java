package unieuroop.controller.department;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
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
        if (!staffs.isEmpty() && !products.isEmpty()) {
            final var deoartment = new DepartmentImpl(name, staffs, products);
            this.shop.addDepartment(deoartment);
            try {
                Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Staff or Products or BOTH are empty");
        }
    }

    public void removeDepartment(final Department department) {
        this.shop.removeDepartment(department);
        try {
            Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mergeDepartments(final Set<Department> departments, final String name) {
        if (!departments.isEmpty()) {
            this.shop.mergeDepartments(departments, name);
            try {
                Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("The set of Departments is empty");
        }
    }

    public void removeProductsFrom(final Department department, final Map<Product, Integer> products) {
    }
}
