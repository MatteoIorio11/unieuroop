package unieuroop.model.supplier;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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

    @Override
    public String getName() {
        return this.supplierName;
    }

    @Override
    public Map<Product, Double> getCatalog() {
        return Map.copyOf(this.salableProduct);
    }

    @Override
    public double getPriceOf(final Product product, final int amount) {
        if (this.salableProduct.containsKey(product)) {
            return this.salableProduct.get(product) * amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

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

    @Override
    public Map<Product, Integer> sellProduct(final Map<Product, Integer> productsPurchased) {
        if (this.salableProduct.keySet().containsAll(productsPurchased.keySet())) {
            return productsPurchased;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "Supplier: " + supplierName;
    }
}
