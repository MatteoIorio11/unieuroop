package unieuroop.model.supplier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import unieuroop.model.product.Product;

@JsonIdentityInfo(scope = SupplierImpl.class, generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class SupplierImpl implements Supplier {
    private final String supplierName;
    private final Map<Product, Double> salableProduct;
    @JsonCreator
    public SupplierImpl(final String name, 
            final Map<Product, Double> products) {
        this.supplierName = name;
        salableProduct = new HashMap<>(products);
    }

    /**
     * Return the Supplier name.
     */
    @Override
    public String getName() {
        return this.supplierName;
    }

    /**
     * Return all avaible supplier's products. 
     */
    @Override
    public Map<Product, Double> getCatalog() {
        return Map.copyOf(this.salableProduct);
    }

    /**
     * Return the price of a product based on the requested amount.
     */
    @Override
    public double getPriceOf(final Product product, final int amount) {
        if (this.salableProduct.containsKey(product)) {
            return this.salableProduct.get(product) * amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Return the total price (as receipt) given products and their amount.
     */
    @Override
    public double getTotalPriceByProducts(final Map<Product, Integer> productsPurchased) {
        double totalPrice = 0;
        for (final Product product : productsPurchased.keySet()) {
            if (this.salableProduct.containsKey(product)) {
                totalPrice += this.salableProduct.get(product) * productsPurchased.get(product);
            }
        }
        return totalPrice;
    }

    /**
     * Return all the products buyed with their quantities. (Limitazione nelle vendite?)
     */
    @Override
    public Map<Product, Integer> sellProduct(final Map<Product, Integer> productsPurchased) {
        if (this.salableProduct.keySet().containsAll(productsPurchased.keySet())) {
            return productsPurchased;
        }
        throw new IllegalArgumentException();
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "Supplier: " + supplierName;
    }
}
