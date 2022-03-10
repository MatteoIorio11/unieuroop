package unieuroop.model.supplier;

import java.util.Map;
import java.util.Set;

import unieuroop.model.product.Product;

public class SupplierImpl implements Supplier {

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Product> getCatalog() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getPriceOf(Product product, int amount) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getTotalPriceByProducts(Map<Product, Integer> productsPurchased) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Map<Product, Integer> sellProduct(Map<Product, Integer> productsPurchased) {
        // TODO Auto-generated method stub
        return null;
    }
}
