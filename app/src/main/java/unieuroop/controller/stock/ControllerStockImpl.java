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

public final class ControllerStockImpl {

    private final Shop shop;
    private final Map<Product, Integer> productsBought = new HashMap<>();
    private final Map<Product, Integer> reservedProduct = new HashMap<>();

    public ControllerStockImpl(final Shop shop) {
        this.shop = shop;
    }

    /**
     * 
     * @return
     */
    public Set<Supplier> getSuppliers() {
        return this.shop.getSuppliers();
    }

    /**
     * 
     * @return
     */
    public Set<Category> getCategory() {
        return this.shop.getAllCategories();
    }

    /**
     * 
     * @return
     */
    public int getMaxAmountproducts() {
        return this.shop.getStock().getMaxAmountOfProducts();
    }

    /**
     * 
     * @param productBuying
     */
    public void addProductBuying(final Map.Entry<Product, Double> productBuying, final int amount) {
        this.productsBought.merge(productBuying.getKey(), amount, (existingQuantity, newQuantity) -> existingQuantity + newQuantity);
    }

    /**
     * 
     * @param productBuying
     */
    public void removeProductsBuying(final Map.Entry<Product, Double> productBuying, final int amount) {
        if (this.productsBought.containsKey(productBuying.getKey())) {
            this.productsBought.remove(productBuying.getKey(), amount);
        } else {
            throw new IllegalArgumentException("Impossible Remove a Product from your Products Buying (Cart)");
        }
    }

    /**
     * 
     * @param product
     * @return
     */
    public boolean checkIfProductPresent(final Product product) {
        return this.productsBought.containsKey(product);
    }

    /**
     * 
     * @param product
     * @return
     */
    public int getAmountofProductsBuying(final Product product) {
        return this.productsBought.get(product);
    }

    /**
     * 
     */
    public void addProductsBoughtInStock() {
        try {
            this.shop.getStock().addProducts(this.productsBought);
            Serialization.<Stock>serialize(Files.STOCK.getPath(), this.shop.getStock());
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
    }

    /**
     * 
     */
    public void resetProductsBoughtBuying() {
        this.productsBought.clear();
    }

    /**
     * 
     * @return
     * @param product
     */
    public String getInfoByProduct(final Product product) {
        return "Category: " + product.getCategory() + "\nBrand: " + product.getBrand() + "\nDescription: " + product.getDescription() + "\n\nSelling Price: " + product.getSellingPrice()
        + "\nPurchase Price: " + product.getPurchasePrice() + "\nQuantity in Stock: " + this.shop.getStock().getQuantityOfProduct(product);
    }

    /**
     * 
     * @return
     */
    public Map<Product, Integer> getProductsStocked() {
        return this.shop.getStock().getTotalStock();
    }

    /**
     * 
     * @param categoryChoose
     * @param minAmount
     * @param maxAmount
     * @param increasing
     * @return
     */
    public List<Product> getListProductsFilterBy(final Category categoryChoose, final int minAmount, final int maxAmount, final boolean increasing) {
        final List<Product> filteredProducts = this.shop.getStock().getFilterProducts((amount, category) -> minAmount <= amount && amount <= maxAmount && category == categoryChoose);
        if (increasing) {
            return this.getListProductsIncreasing(filteredProducts);
        } else {
            return this.getListProductsDecreasing(filteredProducts);
        }
    }

    /**
     * 
     * @param productSelected
     */
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
     * 
     * @param filteredProducts
     * @return
     */
    private List<Product> getListProductsIncreasing(final List<Product> filteredProducts) {
        final Comparator<Product> productsSorter = (p1, p2) -> this.shop.getStock().getQuantityOfProduct(p1) - this.shop.getStock().getQuantityOfProduct(p2);
        filteredProducts.sort(productsSorter);
        return filteredProducts;
    }

    /**
     * 
     * @param filteredProducts
     * @return
     */
    private List<Product> getListProductsDecreasing(final List<Product> filteredProducts) {
        final Comparator<Product> productsSorter = (p1, p2) -> this.shop.getStock().getQuantityOfProduct(p2) - this.shop.getStock().getQuantityOfProduct(p1);
        filteredProducts.sort(productsSorter);
        return filteredProducts;
    }

    public void reserveProduct(final Product product, final int quantity) {
        this.reservedProduct.merge(product, quantity, (oldQuantity, newQuantity) -> oldQuantity + newQuantity);
    }

    public void removeAllReservedProducts() {
        this.reservedProduct.clear();
    }

    public Map<Product, Integer> getReservedProducts() {
        return this.reservedProduct;
    }

}
