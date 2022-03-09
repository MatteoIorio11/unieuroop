package unieuroop.model.stock;

import unieuroop.model.product.Product;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class StockImpl implements Stock {
    private final Map<Product, Integer> productsStocked = new HashMap<Product, Integer>();

    @Override
    public void addProducts(Map<Product, Integer> products) {
        for (Product product : products.keySet()) {
            this.productsStocked.merge(product, products.get(product), (existingQuantity, newQuantity) -> existingQuantity + newQuantity);
        }
    }

    @Override
    public Map<Product, Integer> getTotalStock() {
        return this.productsStocked;
    }

    @Override
    public int getQuantityOfProduct(Product product) {
        return this.productsStocked.getOrDefault(product, 0);
    }

    @Override
    public Map<Product, Integer> takeFromStock(Map<Product, Integer> productsTaken) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteProduct(Set<Product> productsDelete) {
        this.productsStocked.keySet().removeAll(productsDelete);
    }

}
