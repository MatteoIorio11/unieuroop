package unieuroop.view.stock;

import java.net.URL;
import java.util.ResourceBundle;


public interface ViewStockSetFilters {

    /**
     * 
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * 
     */
    void btnConfirmFilterHandler();

    /**
     * 
     */
    void rdbtnIncreasingHandler();

    /**
     * 
     */
    void rdbtnDecreasingHandler();

    /**
     * 
     */
    void cmboxCategoriesHandler();

    /**
     * 
     */
    void sliderMinAmountHandler();

    /**
     * 
     */
    void sliderMaxAmountHandler();

}
