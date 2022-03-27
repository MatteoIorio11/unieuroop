package unieuroop.controller.shop;

import java.util.Set;

import unieuroop.model.department.Department;
import unieuroop.model.sale.Sale;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;

public final class ControllerShopImpl {
    private final Shop shop = new ShopImpl("Test");

    public void registerSale(final Sale sale) {
        if (sale.getProducts().isEmpty()) {
            throw new IllegalArgumentException("There have to be some products in the Sale.");
        } else {
            this.shop.addSale(sale);
        }
    }
    public Set<Department> getDepartments() {
        return this.shop.getDepartments();
    }
    public void addDepartment(final Department department) {
        if (department == null) {
            throw new IllegalArgumentException("The input department can not be null.");
        } else {
            this.shop.addDepartment(department);
        }
    }
}
