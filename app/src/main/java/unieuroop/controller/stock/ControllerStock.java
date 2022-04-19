package unieuroop.controller.stock;

import java.util.List;
import java.util.Map;
import java.util.Set;

import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.supplier.Supplier;

public interface ControllerStock {

    /**
     * 
     * @return
     */
    Set<Supplier> getSuppliers();

    /**
     * 
     * @return
     */
    Set<Category> getCategory();

    /**
     * 
     * @return
     */
    int getMaxAmountproducts();

    /**
     * 
     * @param productBuying
     */
    void addProductBuying(Map.Entry<Product, Double> productBuying, int amount);

    /**
     * 
     * @param productBuying
     */
    void removeProductsBuying(Map.Entry<Product, Double> productBuying, int amount);

    /**
     * 
     * @param product
     * @return
     */
    boolean checkIfProductBuyingPresent(Product product);

    /**
     * 
     * @param product
     * @return
     */
    int getAmountofProductBuying(Product product);

    /**
     * 
     * @return
     */
    int getAmountOfAllProductsBuying();

    /**
     * 
     * @return
     */
    double getTotalPriceOfAllProductsBuying();

    /**
     * 
     */
    void addProductsBoughtInStock();

    /**
     * 
     */
    void resetProductsBoughtBuying();

    /**
     * 
     * @return
     * @param product
     */
    String getInfoByProduct(Product product);

    /**
     * 
     * @return
     */
    Map<Product, Integer> getProductsStocked();

    /**
     * 
     * @param categoryChoose
     * @param minAmount
     * @param maxAmount
     * @param increasing
     * @return
     */
    List<Product> getListProductsFilterBy(Category categoryChoose, int minAmount, int maxAmount, boolean increasing);

    /**
     * 
     * @param productSelected
     */
    void deleteSelectedProduct(Product productSelected);
}
