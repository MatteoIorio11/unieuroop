package unieuroop.model.stock;

import unieuroop.model.product.Product;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class StockImpl implements Stock {
    private final Map<Product, Integer> productsStocked = new HashMap<>();

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
        return new HashMap<Product, Integer>(this.productsStocked);
    }

    /**
     * Return the quantities present in the stock.
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
        if (!checkProductTaken(productsTaken)) {
            throw new IllegalArgumentException();
        }
        for (final Product product : productsTaken.keySet()) {
            this.productsStocked.put(product, this.productsStocked.get(product) - productsTaken.get(product));
        }
        return productsTaken;
    }

    /**
     * Delete products from the stock.
     */
    @Override
    public void deleteProducts(final Set<Product> productsDelete) {
        for (final Product productDeleted : productsDelete) {
            if (!this.productsStocked.containsKey(productDeleted)) {
                throw new IllegalArgumentException();
            }
        }
        this.productsStocked.keySet().removeAll(productsDelete);
    }

    /**
     * Check if is possible take each products and their amount from the stock.
     * @param productsTaken
     * @return boolean
     */
    private boolean checkProductTaken(final Map<Product, Integer> productsTaken) {
        for (final Product productTake : productsTaken.keySet()) {
            if (!this.productsStocked.containsKey(productTake) || this.productsStocked.get(productTake) < productsTaken.get(productTake)) {
                return false;
            }
        }
        return true;
    }

}
