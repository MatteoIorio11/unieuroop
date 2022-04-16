package unieuroop.controller.department;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
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

    public void addDepartment(final String name, final Set<Staff> staffs, final Map<Product, Integer> products) throws IOException {
        if (name.isBlank() || !staffs.isEmpty() && !products.isEmpty()) {
            final var deoartment = new DepartmentImpl(name, staffs, products);
            this.shop.addDepartment(deoartment);
            Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
        } else {
            throw new IllegalArgumentException("One of the input or more than one are empty");
        }
    }

    public void removeDepartment(final Department department) throws IOException {
        this.shop.removeDepartment(department);
        Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());

    }

    public void mergeDepartments(final Set<Department> departments, final String name) throws IOException {
        if (!departments.isEmpty()) {
            this.shop.mergeDepartments(departments, name);
            Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());

        } else {
            throw new IllegalArgumentException("The set of Departments is empty");
        }
    }

    public void removeProductsFrom(final Department inputDepartment, final Set<Staff> staff) throws IOException {
        if (!Objects.isNull(inputDepartment) && this.shop.getDepartments().contains(inputDepartment)) {
            final Department department = this.shop.getDepartments().stream().filter((departmentInput) -> departmentInput.equals(inputDepartment)).findFirst().get();
            department.removeStaff(staff);
            Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
        }
    }

    public void addProductsIn(final Department inputDepartment, final Map<Product, Integer> products) throws IOException {
        if (!Objects.isNull(inputDepartment) && !products.isEmpty()) {
            final Department department = this.shop.getDepartments().stream().filter((dep) -> dep.equals(inputDepartment)).findAny().get();
            department.addProducts(products);
            Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
        }
    }

    public Set<Staff> getStaffOf(final Department departmentInput)  {
        if (this.shop.getDepartments().contains(departmentInput)) {
            return this.shop.getDepartments().stream()
                    .filter((department) -> department.equals(departmentInput))
                    .map((department) -> department.getStaff()).findAny().get();
        } else {
            throw new IllegalArgumentException("The selected department does not exist");
        }
    }
}
