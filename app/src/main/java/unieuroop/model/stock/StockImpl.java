package unieuroop.model.stock;

import unieuroop.model.product.Product;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class StockImpl implements Stock {
    private final Map<Product, Integer> productsStocked = new HashMap<Product, Integer>();

    /**
     * Add Products in the Stock.
     */
    @Override
    public void addProducts(final Map<Product, Integer> products) {
        for (final Product product : products.keySet()) {
            this.productsStocked.merge(product, products.get(product), (existingQuantity, newQuantity) -> existingQuantity + newQuantity);
        }
    }

    /**
     * Get the entire stock of product and their relative quantities.
     */
    @Override
    public Map<Product, Integer> getTotalStock() {
        return this.productsStocked;
    }

    /**
     * Return the amount of a specified product.
     */
    @Override
    public int getQuantityOfProduct(final Product product) {
        if (this.productsStocked.containsKey(product)) {
            return this.productsStocked.get(product);
        }
        throw new IllegalArgumentException();
    }

    /**
     * Return the products and their quantities taken from the stock.
     */
    @Override
    public Map<Product, Integer> takeFromStock(final Map<Product, Integer> productsTaken) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Delete products from the stock.
     */
    @Override
    public void deleteProduct(final Set<Product> productsDelete) {
        this.productsStocked.keySet().removeAll(productsDelete);
    }

}
