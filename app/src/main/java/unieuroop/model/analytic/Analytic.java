package unieuroop.model.analytic;

import java.util.Set;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.time.LocalDate;
import java.time.Month;

import unieuroop.model.product.Category;
import unieuroop.model.product.Product;

public interface Analytic {

    /**
     * This method has to return the Set of all product sold in the shop.
     * @return all the product sold in all the different sales
     */
    Set<Product> getTotalProductsSold();

    /**
     * This method is used to get the quantity sold of a specific product.
     * @param product
     * @return the total quantity sold of the "product"
     */
    int getQuantitySoldOf(Product product);
    /**
     * This method return all the quantity sold of a specific product in a specific date.
     * @param product
     * @param date
     * @return all the quantity of a single product sold in Date.
     */
    int getQuantitySoldOf(Product product, Predicate<LocalDate> date);

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
     * @return the Set of all products sold in the specific date
     */
    Set<Product> getProductByDate(Predicate<LocalDate> date);

    /**
     * This method return the a Map contains a LocalDate and a Set of all products sold in that day.
     * @param datePredicate : specifies which dates we have to consider
     * @return a Map with key a Date and value the Set of all products sold in that specific day
     */
    Map<LocalDate, Integer> getSoldOnDay(Predicate<LocalDate> datePredicate);

    /**
     * This method find all the product sold in a date or a range of date which categories pass the test of the BiPredicate.
     * @param predicate which categories have to be select in a specific Date
     * @return the Set of all products sold in a specific Date of different categories
     */
    Set<Product> getProductByDateCategory(BiPredicate<LocalDate, Category> predicate);

    /**
     * This method return all categories sold with the complete Set of all product .
     * @return the Map contains the Category and the complete Set of all product sold of that specific Category 
     */
    Map<Category, Integer> getCategoriesSold();
    /**
     * This method return the sum of all bills in the same year.
     * @return a Map where the Key is the Year and in the Value we can find the total spent in that year.
     */
    Map<Integer, Double> getTotalSpentByYear();
    /**
     * This method calculate the total Earned in one year.
     * @return a Map where the key is Year and the Value is the sum of all sales by that year
     */
    Map<Integer, Double> getTotalEarnedByYear();
    /**
     * This method calculate the total earned in a specific Month by the year/years specified in the predicate.
     * @param year : which year we have to consider for sales.
     * @return a Map where the Key is the Month and in the Value we find the total earned in that Month
     */
    Map<Month, Double> getTotalEarnedByMonth(Predicate<Integer> year);
    /**
     * This method calculate the total spent in a specific Month by the year/years in the predicate.
     * @param year : which year we have to consider
     * @return a Map where the Key is the Month and the Value is the sum of all spent in that month.
     */
    Map<Month, Double> getTotalSpentByMonth(Predicate<Integer> year);
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
    /**
     * 
     * @return the total spent in the Shop
     */
    double getTotalAmountSpent();
}
