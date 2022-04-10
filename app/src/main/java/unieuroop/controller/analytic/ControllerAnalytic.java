package unieuroop.controller.analytic;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;

import unieuroop.model.product.Category;
import unieuroop.model.product.Product;

public interface ControllerAnalytic {

    Map<Month, Double> getLastYearSpent();

    Map<Month, Double> getLastYearEarned();

    Map<Integer, Double> getYearsTotalSpent();

    Map<Integer, Double> getYearsTotalEarned();

    Map<Product, Integer> getProductsSoldByCategory(Set<Category> categories);

    Map<Category, Integer> getCategoriesSold();

    Map<Integer, Double> getTotalEarnedByYear();

    Map<LocalDate, Integer> getSelectedDate(Set<LocalDate> dates);

    Map<LocalDate, Integer> getSelectedDate(LocalDate dateOne, LocalDate dateTwo);

    Set<Product> getProductsSold();

    double getStockPrice();

    double getShopEarnings();

    double getTotalSpent();

}