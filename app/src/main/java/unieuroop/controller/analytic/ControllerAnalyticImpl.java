package unieuroop.controller.analytic;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;

import unieuroop.model.analytic.Analytic;
import unieuroop.model.analytic.AnalyticImpl;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.shop.Shop;

public final class ControllerAnalyticImpl {

    private final Analytic analytic;

    public ControllerAnalyticImpl(final Shop shop) {
        this.analytic = new AnalyticImpl(shop);
    }
    public Map<Month, Double> getLastYearSpent() {
        return this.analytic.getTotalSpentByMonth((year) -> LocalDate.now().getYear() == year);
    }
    public Map<Month, Double> getLastYearEarned() {
        return this.analytic.getTotalEarnedByMonth((year) -> LocalDate.now().getYear() == year);
    }
    public Map<Integer, Double> getYearsTotalSpent() {
        return this.analytic.getTotalSpentByYear();
    }
    public Map<Integer, Double> getYearsTotalEarned() {
        return this.analytic.getTotalEarnedByYear();
    }
    public Map<Product, Integer> getProductsSoldByCategory(final Set<Category> categories) {
        return this.analytic.getOrderedByCategory((category) -> categories.contains(category));
    }
    public Map<Category, Integer> getCategoriesSold() {
        return this.analytic.getCategoriesSold();
    }
    public Map<Integer, Double> getTotalEarnedByYear() {
        return this.analytic.getTotalEarnedByYear();
    }
    public Map<LocalDate, Integer> getSelectedDate(final Set<LocalDate> dates) {
        return this.analytic.getSoldOnDay((date) -> dates.contains(date));
    }
    public Map<LocalDate, Integer> getSelectedDate(final LocalDate dateOne, final LocalDate dateTwo) {
        final var lowerBound = dateOne.isBefore(dateTwo) ? dateOne : dateTwo;
        final var upperBound = dateOne.isAfter(dateTwo) ? dateOne : dateTwo;
        return this.analytic.getSoldOnDay((date) -> date.isAfter(lowerBound) && date.isBefore(upperBound)
                || date.isEqual(lowerBound) || date.isEqual(upperBound));
    }
    public Set<Product> getProductsSold() {
        return this.analytic.getTotalProductsSold();
    }
}
