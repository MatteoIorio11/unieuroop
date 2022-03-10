package unieuroop.model.stock;

import java.util.Set;
import java.util.Map;
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
}
