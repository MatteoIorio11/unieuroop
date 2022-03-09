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
    Set<Product> getCatalog();
    /**
     * Return the price of the product by their quantities.
     * @param product
     * @param amount
     * @return productPrice
     */
    float getPriceByProduct(Product product, int amount);
    /**
     * Sells the quantities product requested.
     * @param product
     * @param amount
     * @return productSold
     */
    Map<Product, Integer> sellProduct(Product product, int amount);
}
