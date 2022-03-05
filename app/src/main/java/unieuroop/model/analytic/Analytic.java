package unieuroop.model.analytic;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
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
     * PR : inside the view we build the set of categories, then inside the predicate we only check 
     * if ( a )  -> set.contains( a ).
     * @param categories : specifies which categories we have to consider
     * @return a Map that contains all the Product of the specified categories with theri quantity
     */
    Map<Product, Integer> getOrderedByCategory(Predicate<Category> categories);

    /**
     * All the products sold in date, it can be also a range like 02/11/2022 < x < 10/27/2022.
     * @param date : specifies which dates we have to consider
     * @return the List of all products sold in the specific date
     */
    List<Product> getOrderedByDate(Predicate<Date> date);

    /**
     * This method find the best day of sold (based on the total quantity sold) and return the total of product sold.
     * @return the List of products most sold in a day
     */
    List<Product> getBestSoldDay();

    /**
     * 
     * @param predicate wich categories have to be select in a specific Date
     * @return the List of all products sold in a specific Date of different categories
     */
    List<Product> getProductByCategoryDate(BiPredicate<Date, Category> predicate);

}