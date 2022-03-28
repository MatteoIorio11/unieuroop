package unieuroop.controller.shop;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import unieuroop.model.department.Department;
import unieuroop.model.product.Product;
import unieuroop.model.sale.Sale;
import unieuroop.model.sale.SaleImpl;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;

public final class ControllerShopImpl {
    private final Map<Department, Map<Product, Integer>> reservedProductsMap = new HashMap<>();
    private final Shop shop = new ShopImpl("Test");

    public void registerSale(final Map<Product, Integer> products) {
        if (products.isEmpty()) {
            throw new IllegalArgumentException("There have to be some products in the Sale.");
        } else {
            this.shop.addSale(new SaleImpl(LocalDate.now(), products, Optional.empty()));
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

    public int getQuantityOf(final Product product, final Department department) {
        return department.getAllProducts().get(product);
    }

    public void reserveProducts(final Department departmentInput, final Map<Product, Integer> products) {
        final Department deparment = this.shop.getDepartments().stream().filter((dep) -> dep.equals(departmentInput)).findFirst().get();
        System.out.println(deparment.getAllProducts());
        deparment.takeProductFromDepartment(products);
    }
}
