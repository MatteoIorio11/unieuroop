package unieuroop.model.analytic;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.time.LocalDate;

import unieuroop.model.product.Category;
import unieuroop.model.product.Product;

public interface Analytic {

    /**
     * This method has to return the List of all product sold in the shop.
     * @return all the product sold in all the different sales
     */
    List<Product> getTotalProductsSold();

    /**
     * This method is used to get the quantity sold of a specific product.
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
    List<Product> getOrderedByDate(Predicate<LocalDate> date);

    /**
     * This method return the a Map contains a LocalDate and a List of all products sold in that day.
     * @param datePredicate : specifies which dates we have to consider
     * @return a Map with key a Date and value the list of all products sold in that specific day
     */
    Map<LocalDate, List<Product>> getSoldOnDay(Predicate<LocalDate> datePredicate);

    /**
     * This method find all the product sold in a date or a range of date which categories pass the test of the BiPredicate.
     * @param predicate wich categories have to be select in a specific Date
     * @return the List of all products sold in a specific Date of different categories
     */
    List<Product> getProductByDateCategory(BiPredicate<LocalDate, Category> predicate);

    /**
     * This method return all categories sold with the complete list of all product .
     * @return the Map contains the Category and the complete list of all product sold of that specific Category 
     */
    Map<Category, List<Product>> getCategoriesSold();
    /**
     * 
     * @return the total earned in different days
     */
    Map<LocalDate, Double> getTotalEarned();
    /**
     * 
     * @return the total value of all products inside the stock
     */
    double getTotalStockPrice();
    /**
     * 
     * @return all the total value of all sales
     */
    double getTotalShopEarned();

}
