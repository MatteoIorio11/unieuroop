package unieuroop.model.stock;

import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import java.util.Map;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;


public class StockImpl implements Stock, Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
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
     * Return the the products and their quantities filter by their amount and categories.
     */
    @Override
    public Map<Product, Integer> getFilterProducts(final BiPredicate<Integer, Category> filter) {
        final Map<Product, Integer> productsFilter = new HashMap<>();
        for (final var entryProduct : this.productsStocked.entrySet()) {
            if (filter.test(entryProduct.getValue(), entryProduct.getKey().getCategory())) {
                productsFilter.put(entryProduct.getKey(), entryProduct.getValue());
            }
        }
        return productsFilter;
    }

    /**
     * Return the list of product sorted increasing or decreasing.
     */
    @Override
    public List<Product> getProductsSorted(final Comparator<Product> sorting) {
        final List<Product> sortedProducts = new ArrayList<>(this.productsStocked.keySet());
        sortedProducts.sort(sorting);
        return sortedProducts;
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
