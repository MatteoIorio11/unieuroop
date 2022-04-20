package unieuroop.controller.stock;

import java.util.List;
import java.util.Map;
import java.util.Set;

import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.supplier.Supplier;

public interface ControllerStock {

    /**
     * Return all the Suppliers present in the Shop.
     * @return suppliers.
     */
    Set<Supplier> getSuppliers();

    /**
     * Return all the categories present in the Shop.
     * @return categories.
     */
    Set<Category> getCategory();

    /**
     * Return the Max amount for a product stocked.
     * @return maxAmount.
     */
    int getMaxAmountproducts();

    /**
     * Add in the "Shopping Bag" the selected product.
     * @param productBuying
     * @param amount
     */
    void addProductBuying(Map.Entry<Product, Double> productBuying, int amount);

    /**
     * Remove from the "Shopping Bag" the selected product.
     * @param productBuying
     * @param amount
     */
    void removeProductsBuying(Map.Entry<Product, Double> productBuying, int amount);

    /**
     * Check if the product buying is already present in the "Shopping Bag".
     * @param product
     * @return True or False.
     */
    boolean checkIfProductBuyingPresent(Product product);

    /**
     * Return the amount of a specify product stocked.
     * @param product
     * @return amount.
     */
    int getAmountofProductBuying(Product product);

    /**
     * Return the total amount of all the products stocked.
     * @return totalAmount.
     */
    int getAmountOfAllProductsBuying();

    /**
     * Return the total price of all the products buying.
     * @return totalPrice.
     */
    double getTotalPriceOfAllProductsBuying();

    /**
     * Add the products bought from the "Shopping Bag" in the Stock.
     */
    void addProductsBoughtInStock();

    /**
     * Reset the Map of the product buying, the "Shopping Bag".
     */
    void resetProductsBoughtBuying();

    /**
     * Return all the info about a product.
     * @return infoProduct.
     * @param product
     */
    String getInfoByProduct(Product product);

    /**
     * Return all the Products Stocked.
     * @return productsStocked.
     */
    Map<Product, Integer> getProductsStocked();

    /**
     * Return the List of all products filtered by a specify parameters.
     * @param categoryChoose
     * @param minAmount
     * @param maxAmount
     * @param increasing
     * @return filteredProducts.
     */
    List<Product> getListProductsFilterBy(Category categoryChoose, int minAmount, int maxAmount, boolean increasing);

    /**
     * Delete permanent a product from the Stock.
     * @param productSelected
     */
    void deleteSelectedProduct(Product productSelected);
}
