package unieuroop.controller.analytic;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;

import unieuroop.model.product.Category;
import unieuroop.model.product.Product;

public interface ControllerAnalytic {

    /**
     * 
     * @return Result of analytic.getTotalSpentByMonth filtered by the current year.
     */
    Map<Month, Double> getLastYearSpent();

    /**
     * 
     * @return Result of nalytic.getTotalEarnedByMonth filtered by the current year.
     */
    Map<Month, Double> getLastYearEarned();

    /**
     * 
     * @return the total spent in every year.
     */
    Map<Integer, Double> getYearsTotalSpent();

    /**
     * 
     * @return the total earned in every year.
     */
    Map<Integer, Double> getTotalEarnedByYear();

    /**
     * 
     * @param categories
     * @return all the products sold which category is contained in "categories".
     */
    Map<Product, Integer> getProductsSoldByCategory(Set<Category> categories);

    /**
     * 
     * @return all the categories sold with the sum of every different product of that category.
     */
    Map<Category, Integer> getCategoriesSold();

    /**
     * 
     * @param dates
     * @return all the products sold that are contained inside "dates".
     */
    Map<LocalDate, Integer> getSelectedDate(Set<LocalDate> dates);

    /**
     * Inside the method is calculate which is the upper bound and the lower bound.
     * @param dateOne
     * @param dateTwo
     * @return all the products between the two parameters.
     */
    Map<LocalDate, Integer> getSelectedDate(LocalDate dateOne, LocalDate dateTwo);

    /**
     * 
     * @return all the products sold.
     */
    Set<Product> getProductsSold();

    /**
     * 
     * @return the total value of the stock
     */
    double getStockPrice();

    /**
     * 
     * @return all the money earned by all the sales.
     */
    double getShopEarnings();

    /**
     * 
     * @return all the money spent with the suppliers.
     */
    double getTotalSpent();

}