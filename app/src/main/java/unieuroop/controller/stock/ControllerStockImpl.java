package unieuroop.controller.stock;

import java.util.HashMap;
import java.util.Map;

import unieuroop.model.product.Product;
import unieuroop.model.shop.Shop;

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
}
