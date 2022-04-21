package unieuroop.model.stock;

import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;


public final class StockImpl implements Stock {
    private final Map<Product, Integer> productsStocked = new HashMap<>();

    @Override
    public void addProducts(final Map<Product, Integer> products) {
        for (final Product product : products.keySet()) {
            this.productsStocked.merge(product, products.get(product), (existingQuantity, newQuantity) -> existingQuantity + newQuantity);
        }
    }

    @Override
    public Map<Product, Integer> getTotalStock() {
        return new HashMap<Product, Integer>(this.productsStocked);
    }

    @Override
    public int getQuantityOfProduct(final Product product) {
        if (this.productsStocked.containsKey(product)) {
            return this.productsStocked.get(product);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Map<Product, Integer> takeFromStock(final Map<Product, Integer> productsTaken) {
        if (!checkProductTaken(productsTaken)) {
            throw new IllegalArgumentException("Some products can not be taken");
        }
        for (final Product product : productsTaken.keySet()) {
            this.productsStocked.put(product, this.productsStocked.get(product) - productsTaken.get(product));
        }
        return productsTaken;
    }

    @Override
    public void deleteProducts(final Set<Product> productsDelete) {
        for (final Product productDeleted : productsDelete) {
            if (!this.productsStocked.containsKey(productDeleted)) {
                throw new IllegalArgumentException("Some products can not be deleted");
            }
        }
        this.productsStocked.keySet().removeAll(productsDelete);
    }

    @Override
    public List<Product> getFilterProducts(final BiPredicate<Integer, Category> filter) {
        final List<Product> productsFilter = new ArrayList<>();
        for (final Map.Entry<Product, Integer> entryProduct : this.productsStocked.entrySet()) {
            if (filter.test(entryProduct.getValue(), entryProduct.getKey().getCategory())) {
                productsFilter.add(entryProduct.getKey());
            }
        }
        return productsFilter;
    }

    @Override
    public List<Product> getProductsSorted(final Comparator<Product> sorting) {
        final List<Product> sortedProducts = new ArrayList<>(this.productsStocked.keySet());
        sortedProducts.sort(sorting);
        return sortedProducts;
    }

    @Override
    public int getMaxAmountOfProducts() {
        return Collections.max(this.productsStocked.values());
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
