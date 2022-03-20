package unieuroop.controller.analytic;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import unieuroop.model.analytic.Analytic;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;

public final class ControllerAnalyticImpl implements Analytic{

    private final Analytic analytic;

    public ControllerAnalyticImpl(final Analytic analytic) {
        this.analytic = analytic;
    }
    /**
     * 
     * @return
     */
    Map<LocalDate, Double> getTotalEarned(){
        return this.analytic.getTotalEarned((date) -> true);
    }
    /**
     * 
     * @return
     */
    Map<LocalDate, Double> getTotalSpent(){
        return this.analytic.getTotalSpent((date) -> true);
    }
    /**
     * 
     * @return
     */
    Map<LocalDate, Double> getTotalSold30Days(){
        return this.analytic.getTotalEarned((date) -> 
        Math.abs(LocalDate.now().getMonthValue() - date.getMonthValue()) == 1);
    }
    /**
     * 
     * @return
     */
    Map<LocalDate, Double> getTotalSpent30Days(){
        return this.analytic.getTotalSpent((date) -> 
        Math.abs(LocalDate.now().getMonthValue() - date.getMonthValue()) == 1);
    }

    @Override
    public Set<Product> getTotalProductsSold() {
        return this.analytic.getTotalProductsSold();
    }

    @Override
    public int getQuantitySoldOf(final Product product) {
        return this.analytic.getQuantitySoldOf(product);
    }

    @Override
    public int getQuantitySoldOf(final Product product, final Predicate<LocalDate> date) {
        return this.analytic.getQuantitySoldOf(product, date);
    }

    @Override
    public Map<Product, Integer> getOrderedByCategory(final Predicate<Category> categories) {
        return this.getOrderedByCategory(categories);
    }

    @Override
    public Set<Product> getProductByDate(final Predicate<LocalDate> date) {
        return this.analytic.getProductByDate(date);
    }

    @Override
    public Map<LocalDate, Set<Product>> getSoldOnDay(final Predicate<LocalDate> datePredicate) {
        return this.getSoldOnDay(datePredicate);
    }

    @Override
    public Set<Product> getProductByDateCategory(final BiPredicate<LocalDate, Category> predicate) {
        return this.getProductByDateCategory(predicate);
    }

    @Override
    public Map<Category, Set<Product>> getCategoriesSold() {
        return this.getCategoriesSold();
    }

    @Override
    public Map<LocalDate, Double> getTotalEarned(final Predicate<LocalDate> predicate) {
        return this.getTotalEarned(predicate);
    }

    @Override
    public Map<LocalDate, Double> getTotalSpent(final Predicate<LocalDate> predicate) {
        return this.analytic.getTotalSpent(predicate);
    }

    @Override
    public double getTotalStockPrice() {
        return this.analytic.getTotalStockPrice();
    }

    @Override
    public double getTotalShopEarned() {
        return this.analytic.getTotalShopEarned();
    }

    @Override
    public double getTotalAmountSpent() {
        return this.analytic.getTotalAmountSpent();
    }
}
