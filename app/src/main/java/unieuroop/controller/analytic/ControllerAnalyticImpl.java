package unieuroop.controller.analytic;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import unieuroop.model.analytic.Analytic;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;

public final class ControllerAnalyticImpl{

    private final Analytic analytic;

    public ControllerAnalyticImpl(final Analytic analytic) {
        this.analytic = analytic;
    }
    public Map<Month, Double> getLastYearSpent(){
        return this.analytic.getTotalSpentByMonth((year) -> LocalDate.now().getYear() == year);
    }
    public Map<Month, Double> getLastYearEarned(){
        return this.analytic.getTotalEarnedByMonth((year) -> LocalDate.now().getYear() == year);
    }
    public Map<Integer, Double> getYearsTotalSpent(){
        return this.analytic.getTotalSpentByYear();
    }
    public Map<Integer, Double> getYearsTotalEarned(){
        return this.analytic.getTotalEarnedByYear();
    }
    public Map<Product, Integer> getProductsSoldByCategory(final Set<Category> categories){
        return this.analytic.getOrderedByCategory((category) -> categories.contains(category));
    }
    public Map<Category, Set<Product>> getCategoriesSold(){
        return this.analytic.getCategoriesSold();
    }
}
