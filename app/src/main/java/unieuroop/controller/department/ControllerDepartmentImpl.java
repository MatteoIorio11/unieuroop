package unieuroop.controller.department;

import java.io.IOException;
import java.util.HashMap;
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
import unieuroop.model.stock.Stock;

public final class ControllerDepartmentImpl implements ControllerDepartment {
    private final Shop shop;
    private final Map<Product, Integer> reservedProduct = new HashMap<>();


    public ControllerDepartmentImpl(final Shop shop) {
        this.shop = shop;
    }

    @Override
    public Set<Department> getDepartments() {
        return this.shop.getDepartments();
    }

    @Override
    public Set<Product> getProductsOf(final Department department) {
        return this.shop.getDepartments().stream()
                .filter((strDepartment) -> strDepartment.equals(department))
                .flatMap((strDepartment) -> strDepartment.getAllProducts().keySet().stream())
                .collect(Collectors.toSet());
    }

    @Override
    public Map<Product, Integer> getProductsQuantityOf(final Department department) {
        return this.shop.getDepartments().stream()
                .filter(d -> d.equals(department))
                .findAny().get().getAllProducts();
    }
    @Override
    public void addDepartment(final String name, final Set<Staff> staffs, final Map<Product, Integer> products) throws IOException {
        if (name.isBlank() || !staffs.isEmpty() && !products.isEmpty()) {
            final var department = new DepartmentImpl(name, staffs, products);
            this.shop.addDepartment(department);
        } else {
            throw new IllegalArgumentException("One of the input or more than one are empty");
        }
    }

    @Override
    public void removeDepartment(final Department department) throws IOException {
        if (!Objects.isNull(department)) {
            this.shop.removeDepartment(department);
            Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
        } else {
            throw new IllegalArgumentException("The department is null");
        }
    }

    @Override
    public void mergeDepartments(final Set<Department> departments, final String name) throws IOException {
        if (!departments.isEmpty() || name.isBlank()) {
            this.shop.mergeDepartments(departments, name);
            Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
        } else {
            throw new IllegalArgumentException("The set of Departments is empty or the name is blank");
        }
    }

    @Override
    public void removeProductsFrom(final Department inputDepartment, final Map<Product, Integer> products) throws IOException {
        if (!Objects.isNull(inputDepartment) && this.shop.getDepartments().contains(inputDepartment) && !products.isEmpty()) {
            this.shop.putProductsBackInStock(inputDepartment, products);
            Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
            Serialization.<Stock>serialize(Files.STOCK.getPath(), this.shop.getStock());
        } else {
            throw new IllegalArgumentException("One of the parameter of more than one are empty.");
        }
    }

    @Override
    public void addProductsIn(final Department inputDepartment, final Map<Product, Integer> products) throws IOException {
        if (!Objects.isNull(inputDepartment) && !products.isEmpty()) {
            this.shop.supplyDepartment(inputDepartment, products);
            Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
            Serialization.<Stock>serialize(Files.STOCK.getPath(), this.shop.getStock());
        }
    }

    @Override
    public void addStaff(final Department inputDepartment, final Set<Staff> staffs) throws IOException {
        if (!Objects.isNull(inputDepartment) && !staffs.isEmpty()) {
            final var dep = this.shop.getDepartments().stream().filter((department) -> department.equals(inputDepartment)).findAny().get();
            for (final var staff : staffs) {
                dep.addStaff(staff);
            }
            Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
        } else {
            throw new IllegalArgumentException("One or both of the parameters are null");
        }
    }

    @Override
    public void removeStaff(final Department inputDepartment, final Set<Staff> staffs) throws IOException {
        if (!Objects.isNull(inputDepartment) && !staffs.isEmpty()) {
            final var dep = this.shop.getDepartments().stream().filter((department) -> department.equals(inputDepartment)).findAny().get();
            dep.removeStaff(staffs);
            Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
        } else {
            throw new IllegalArgumentException("One or both of the parameters are null");
        }
    }

    @Override
    public Set<Staff> getStaffOf(final Department departmentInput)  {
        if (!Objects.isNull(departmentInput) && this.shop.getDepartments().contains(departmentInput)) {
            return this.shop.getDepartments().stream()
                    .filter((department) -> department.equals(departmentInput))
                    .map((department) -> department.getStaff()).findAny().get();
        } else {
            throw new IllegalArgumentException("The selected department does not exist");
        }
    }

    @Override
    public void reserveProduct(final Product product, final int quantity) {
        this.reservedProduct.merge(product, quantity, (oldQuantity, newQuantity) -> oldQuantity + newQuantity);
    }

    @Override
    public void removeAllReservedProducts() {
        this.reservedProduct.clear();
    }

    @Override
    public Map<Product, Integer> getReservedProducts() {
        return this.reservedProduct;
    }

    @Override
    public void closeAddProducts() throws IOException {
        if (!this.reservedProduct.isEmpty()) {
            this.reservedProduct.clear();
        } else {
            throw new IllegalArgumentException("The map of products is empty");
        }
    }
}
