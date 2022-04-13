package unieuroop.controller.stock;

import java.io.IOException;
import java.util.ArrayList;
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
import unieuroop.model.sale.Sale;
import unieuroop.model.shop.Shop;
import unieuroop.model.supplier.Supplier;

public class ControllerStockImpl {

    private final Shop shop;
    private List<Product> listProductsStocked;
    private Map<Product, Integer> productsBought;

    public ControllerStockImpl(final Shop shop) {
        this.shop = shop;
        this.listProductsStocked = new ArrayList<>(this.shop.getStock().getTotalStock().keySet());
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
     */
    public Map<Product, Integer> getProductsStocked() {
        return this.shop.getStock().getTotalStock();
    }

    /**
     * 
     * @return
     */
    public List<Product> getListProductsStocke() {
        return this.listProductsStocked;
    }

    /**
     * 
     */
    public void resetListproductsStocked() {
        this.listProductsStocked = new ArrayList<>(this.shop.getStock().getTotalStock().keySet());
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
}
