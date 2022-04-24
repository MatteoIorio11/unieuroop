package unieuroop.view.stock;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import unieuroop.model.product.Product;

public interface ViewStock {

    /**
     * Initialized a new ViewStock.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * Handler for Products clicked in the ViewList.
     */
    void listProductsStockedHandler();

    /**
     * Open ViewStockBuyProducts for buy new products.
     */
    void btnBuyProductsHandler();

    /**
     * Delete a product already present in the Shop.
     */
    void btnDeleteProductsHandler();

    /**
     * Open ViewStockSetFilters for set some filters for a research.
     */
    void btnSearchFiltersHandler();

    /**
     * Reset all the set filter.
     */
    void btnResetFiltersHandler();

    /**
     * Handler for the search bar.
     */
    void txtSearchProductsHandler();

    /**
     * Load the ListView of products with a specify List of products.
     * @param products
     */
    void loadProductsByList(List<Product> products);

}
