package unieuroop.controller.stock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import unieuroop.model.product.Product;
import unieuroop.model.shop.Shop;
import unieuroop.model.supplier.Supplier;

public class ControllerStockImpl {

    private final Shop shop;

    public ControllerStockImpl(final Shop shop) {
        this.shop = shop;
    }

    /**
     * 
     * @return
     */
    public Map<Product, Integer> getProductsStocked() {
        return this.shop.getStock().getTotalStock();
    }

    /**
     * 
     * @return
     */
    public Set<Supplier> getSuppliers() {
        return this.shop.getSuppliers();
    }
}
