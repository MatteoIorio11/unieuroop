package unieuroop.model.analytic;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.time.LocalDate;
import unieuroop.model.product.Product;
import unieuroop.model.product.Category;
import unieuroop.model.shop.Shop;
public final class AnalyticImpl implements Analytic {

    private final Shop shop;
    /**
     * Constructor of Analytic, in this method we initialise the List of sale.
     * @param shop
     */

    public AnalyticImpl(final Shop shop) {
        this.shop = shop;
    }

    @Override
    public List<Product> getTotalProductsSold() {
        return this.shop.getSales().stream()
                .flatMap((sale) -> sale.getProducts().stream())
                .distinct()
                .sorted((product1, product2) -> product1.getName().compareTo(product2.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public int getQuantitySoldOf(final Product product) {
        return this.shop.getSales().stream()
                .flatMap((sale) -> sale.getProducts().stream()
                        .filter((singleProduct) -> singleProduct.getProductCode() == product.getProductCode())
                        .map((singleProduct) -> sale.getQuantityOf(singleProduct)))
                .mapToInt((quantity) -> quantity)
                .sum();
    }

    @Override
    public Map<Product, Integer> getOrderedByCategory(final Predicate<Category> categories) {
        return this.shop.getSales().stream()
                .flatMap((sale) -> sale.getProducts().stream()
                        .filter((product) -> categories.test(product.getCategory())))
                .distinct()
                .sorted((product1, product2) -> product1.getName().compareTo(product2.getName()))
                .collect(Collectors.toMap((product) -> product, (procuct) -> this.getQuantitySoldOf(procuct)));
    }

    @Override
    public Set<Product> getProductByDate(final Predicate<LocalDate> date) {
        return this.shop.getSales((sale) -> date.test(sale.getDate())).stream()
                .flatMap((sale) -> sale.getProducts().stream())
                .distinct()
                .sorted((product1, product2) -> product1.getName().compareTo(product2.getName()))
                .collect(Collectors.toSet());
    }

    @Override 
    public Map<LocalDate, Set<Product>> getSoldOnDay(final Predicate<LocalDate> datePredicate) {
        return this.shop.getSales((sale) -> datePredicate.test(sale.getDate())).stream()
                .map((sale) -> sale.getDate())
                .distinct()
                .sorted((date1, date2) -> date1.compareTo(date2))
                .collect(Collectors.toMap((date) -> date, 
                        (date) -> this.getProductByDate((inputDate) -> datePredicate.test(inputDate))));
    }

    @Override
    public List<Product> getProductByDateCategory(final BiPredicate<LocalDate, Category> predicate) {
        return this.shop.getSales().stream()
                .flatMap((sale) -> sale.getProducts().stream()
                        .filter((product) -> predicate.test(sale.getDate(), product.getCategory())))
                .distinct()
                .sorted((product1, product2) -> product1.getName().compareTo(product2.getName())) 
                .collect(Collectors.toList());
    }

    private List<Product> allSalesCategory(final Category category) {
        return this.shop.getSales().stream()
                .flatMap((sale) -> sale.getProducts().stream()
                        .filter((product) -> product.getCategory().equals(category)))
                .distinct()
                .sorted((product1, product2) -> product1.getName().compareTo(product2.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Category, List<Product>> getCategoriesSold() {
        return this.shop.getSales().stream()
                .flatMap((sale) -> sale.getProducts().stream().map((product) -> product.getCategory()))
                .distinct()
                .sorted((product1, product2) -> product1.getName().compareTo(product2.getName()))
                .collect(Collectors.toMap((category) -> category, 
                        (category) -> this.allSalesCategory(category)));
    }

    private double getTotalEarnedIn(final Predicate<LocalDate> predicate) {
        return this.shop.getSales().stream()
                .filter((sale) -> predicate.test(sale.getDate()))
                .mapToDouble((sale) -> sale.getTotalSpent())
                .sum();
    }

    @Override
    public Map<LocalDate, Double> getTotalEarned() {
        return this.shop.getSales().stream()
                .map((sale) -> sale.getDate())
                .distinct()
                .collect(Collectors.toMap((date) -> date, 
                        (date) -> this.getTotalEarnedIn((dateInput) -> dateInput.equals(date))));
    }

    @Override
    public Map<LocalDate, Double> getTotalSpent() {
        return Collections.emptyMap();
    }

    @Override
    public double getTotalStockPrice() {
        return this.shop.getStock().getTotalStock().entrySet().stream()
                .mapToDouble((entry) -> entry.getKey().getSellingPrice() * entry.getValue())
                .sum();
    }

    @Override
    public double getTotalShopEarned() {
        return this.shop.getSales().stream()
                .mapToDouble((sale) -> sale.getTotalSpent())
                .sum();
    }
}
