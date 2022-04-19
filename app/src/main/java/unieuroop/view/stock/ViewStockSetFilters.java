package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;


public interface ViewStockSetFilters {

    /**
     * Initialized new ViewStockSetFilters.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * Confirm all the set filter for the research.
     */
    void btnConfirmFilterHandler();

    /**
     * Set the increasing order for the list of products searched.
     */
    void rdbtnIncreasingHandler();

    /**
     * Set the decreasing order for the list of products searched.
     */
    void rdbtnDecreasingHandler();

    /**
     * Select the Categories for the search.
     */
    void cmboxCategoriesHandler();

    /**
     * Set the minimum amount of products, for the research.
     */
    void sliderMinAmountHandler();

    /**
     * Set the maximum amount of products, for the research.
     */
    void sliderMaxAmountHandler();

}
