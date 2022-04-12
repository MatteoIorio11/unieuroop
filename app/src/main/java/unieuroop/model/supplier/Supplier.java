package unieuroop.model.supplier;

import java.util.Map;
import java.util.Set;

import unieuroop.model.product.Product;

public interface Supplier {
    /**
     * Return the name of the supplier.
     * @return supplierName
     */
    String getName();
    /**
     * Return all the supplier products. 
     * @return supplierProducts
     */
    Map<Product, Double> getCatalog();
    /**
     * Return the price of the product by their quantities.
     * @param product
     * @param amount
     * @return productPrice
     */
    double getPriceOf(Product product, int amount);
    /**
     * Return the total prices of the products purchased.
     * @param productsPurchased
     * @return productTotalPrice
     */
    double getTotalPriceByProducts(Map<Product, Integer> productsPurchased);
    /**
     * Sell given product.
     * @param productsPurchased
     * @return productPurchased
     */
    Map<Product, Integer> sellProduct(Map<Product, Integer> productsPurchased);
}
