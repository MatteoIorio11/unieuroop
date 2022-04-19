package unieuroop.model.stock;

import java.util.Set;
import java.util.function.BiPredicate;
import java.util.Comparator;
import java.util.List;

import java.util.Map;

import unieuroop.model.product.Category;
import unieuroop.model.product.Product;

public interface Stock {
    /**
     * Add multiple products.
     * @param products
     */
    void addProducts(Map<Product, Integer> products);

    /**
     * Return the entire stock of product.
     * @return products
     */
    Map<Product, Integer> getTotalStock();

    /**
     * Return the quantity of a product.
     * @param product
     * @return quantityProducts
     */
    int getQuantityOfProduct(Product product);

    /**
     * Return the Map of the products taken from the stock, then they will be putted in the Department.
     * @param productsTaken
     * @return productsTaken
     */
    Map<Product, Integer> takeFromStock(Map<Product, Integer> productsTaken);

    /**
     * Permanent delete a set of Products.
     * @param productsDelete
     */
    void deleteProducts(Set<Product> productsDelete);

    /**
     * Return the products filter by a Bipredicate.
     * @param filter
     * @return filterProducts
     */
    List<Product> getFilterProducts(BiPredicate<Integer, Category> filter);

    /**
     * Return the list of products sorted by increasing or decreasing.
     * @param sorting
     * @return sortedProducts
     */
    List<Product> getProductsSorted(Comparator<Product> sorting);

    /**
     * Return the max amount of a product present in the Stock.
     * @return maxAmount
     */
    int getMaxAmountOfProducts();
}
