package unieuroop.controller.stock;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.shop.Shop;
import unieuroop.model.stock.Stock;
import unieuroop.model.supplier.Supplier;

public final class ControllerStockImpl implements ControllerStock {

    private final Shop shop;
    private final Map<Product, Integer> productsBought = new HashMap<>();

    public ControllerStockImpl(final Shop shop) {
        this.shop = shop;
    }

    @Override
    public Set<Supplier> getSuppliers() {
        return this.shop.getSuppliers();
    }

    @Override
    public Set<Category> getCategory() {
        return this.shop.getAllCategories();
    }

    @Override
    public int getMaxAmountproducts() {
        return this.shop.getStock().getMaxAmountOfProducts();
    }

    @Override
    public void addProductBuying(final Map.Entry<Product, Double> productBuying, final int amount) {
        this.productsBought.merge(productBuying.getKey(), amount, (existingQuantity, newQuantity) -> existingQuantity + newQuantity);
    }

    @Override
    public void removeProductsBuying(final Map.Entry<Product, Double> productBuying, final int amount) {
        if (this.productsBought.containsKey(productBuying.getKey())) {
            this.productsBought.remove(productBuying.getKey(), amount);
        } else {
            throw new IllegalArgumentException("Impossible Remove a Product from your Products Buying (Cart)");
        }
    }

    @Override
    public boolean checkIfProductBuyingPresent(final Product product) {
        return this.productsBought.containsKey(product);
    }

    @Override
    public int getAmountofProductBuying(final Product product) {
        return this.productsBought.get(product);
    }

    @Override
    public int getAmountOfAllProductsBuying() {
        return this.productsBought.entrySet().stream().mapToInt(entryProduct -> entryProduct.getValue()).sum();
    }

    @Override
    public double getTotalPriceOfAllProductsBuying() {
        return this.productsBought.entrySet().stream().mapToDouble(entryProduct -> entryProduct.getKey().getSellingPrice() * entryProduct.getValue()).sum();
    }

    @Override
    public void addProductsBoughtInStock() {
        try {
            this.shop.getStock().addProducts(this.productsBought);
            Serialization.<Stock>serialize(Files.STOCK.getPath(), this.shop.getStock());
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
    }

    @Override
    public void resetProductsBoughtBuying() {
        this.productsBought.clear();
    }

    @Override
    public String getInfoByProduct(final Product product) {
        return "Category: " + product.getCategory() + "\nBrand: " + product.getBrand() + "\nDescription: " + product.getDescription() + "\n\nSelling Price: " + product.getSellingPrice()
        + "\nPurchase Price: " + product.getPurchasePrice() + "\nQuantity in Stock: " + this.shop.getStock().getQuantityOfProduct(product);
    }

    @Override
    public Map<Product, Integer> getProductsStocked() {
        return this.shop.getStock().getTotalStock();
    }

    @Override
    public List<Product> getListProductsFilterBy(final Category categoryChoose, final int minAmount, final int maxAmount, final boolean increasing) {
        final List<Product> filteredProducts = this.shop.getStock().getFilterProducts((amount, category) -> minAmount <= amount && amount <= maxAmount && category == categoryChoose);
        if (increasing) {
            return this.getListProductsIncreasing(filteredProducts);
        } else {
            return this.getListProductsDecreasing(filteredProducts);
        }
    }

    @Override
    public void deleteSelectedProduct(final Product productSelected) {
        final Set<Product> productDelete = new HashSet<>();
        productDelete.add(productSelected);
        try {
            this.shop.getStock().deleteProducts(productDelete);
            Serialization.<Stock>serialize(Files.STOCK.getPath(), this.shop.getStock());
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
    }

    /**
     * Return the filteredProducts list, sorted increasing.
     * @param filteredProducts
     * @return sortedList.
     */
    private List<Product> getListProductsIncreasing(final List<Product> filteredProducts) {
        final Comparator<Product> productsSorter = (p1, p2) -> this.shop.getStock().getQuantityOfProduct(p1) - this.shop.getStock().getQuantityOfProduct(p2);
        filteredProducts.sort(productsSorter);
        return filteredProducts;
    }

    /**
     * Return the filteredProducts list, sorted decreasing.
     * @param filteredProducts
     * @return sortedList.
     */
    private List<Product> getListProductsDecreasing(final List<Product> filteredProducts) {
        final Comparator<Product> productsSorter = (p1, p2) -> this.shop.getStock().getQuantityOfProduct(p2) - this.shop.getStock().getQuantityOfProduct(p1);
        filteredProducts.sort(productsSorter);
        return filteredProducts;
    }
}
