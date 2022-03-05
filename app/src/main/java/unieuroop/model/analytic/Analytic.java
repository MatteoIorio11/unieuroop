package unieuroop.model.analytic;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.Date;

import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.sale.NullSaleException;
import unieuroop.model.sale.Sale;


public interface Analytic {

    /**
     * 
     * @param sale
     * @throws NullSaleException 
     */
    void addSale(Sale sale) throws NullSaleException;

    /**
     * 
     * @return all the product sold in all the different sales
     */
    List<Product> getTotalProductsSold();

    /**
     * 
     * @param product
     * @return the total quantity sold of the "product"
     */
    int getQuantitySoldOf(Product product);

    /**
     * PROMEMRORIA : NELLA VIEW DOVRO COSTRUIRE UN SET DELLE CATEOGRIE E NEL PREDICATE METTERE
     * (categoria) -> set.contains(categoria) ====> DOVE set VIENE COSTRUITO IN BASE A CIO CHE SI SCEGLIE DALLA VIEW 
     * @return a
     */
    Map<Product, Integer> getOrderedByCategory(Predicate<Category> predicate);

    /**
     * 
     * @param predicate
     * @return a
     */
    List<Product> getOrderedByDate(Predicate<Date> predicate);

}