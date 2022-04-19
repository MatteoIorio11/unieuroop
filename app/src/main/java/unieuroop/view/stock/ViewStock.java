package unieuroop.view.stock;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import unieuroop.model.product.Product;

public interface ViewStock {

    /**
     * 
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * 
     */
    void listProductsStockedHandler();

    /**
     * 
     */
    void btnBuyProductsHandler();

    /**
     * 
     */
    void btnDeleteProductsHandler();

    /**
     * 
     */
    void btnSearchFiltersHandler();

    /**
     * 
     */
    void btnResetFiltersHandler();

    /**
     * 
     */
    void txtSearchProductsHandler();

    /**
     * 
     * @param products
     */
    void loadProductsByList(List<Product> products);

}