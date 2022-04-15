package unieuroop.controller.stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.sale.Sale;
import unieuroop.model.shop.Shop;
import unieuroop.model.supplier.Supplier;

public class ControllerStockImpl {

    private final Shop shop;
    private Map<Product, Integer> productsBought;

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
    public void addProductBuying(Map<Product, Integer> productBuying) {
        this.productsBought.putAll(productBuying);
    }

    /**
     * 
     */
    public void addProductsBoughtInStock() {
        this.shop.getStock().addProducts(this.productsBought);
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
            Serialization.<Map<Product, Integer>>serialize(Files.STOCK.getPath(), this.shop.getStock().getTotalStock());
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
}
