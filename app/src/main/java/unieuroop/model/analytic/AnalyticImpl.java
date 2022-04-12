package unieuroop.model.analytic;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.Month;

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
    public Set<Product> getTotalProductsSold() {
        return this.shop.getSales().stream()
                .flatMap((sale) -> sale.getProducts().stream())
                .distinct()
                .sorted((product1, product2) -> product1.getName().compareTo(product2.getName()))
                .collect(Collectors.toSet());
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
    public int getQuantitySoldOf(final Product product, final Predicate<LocalDate> date) {
        return this.shop.getSales().stream()
                .filter((sale) -> date.test(sale.getDate()))
                .flatMap((sale) -> sale.getProducts().stream()
                        .filter((pro) -> pro.equals(product))
                        .map((pro) -> sale.getQuantityOf(pro)))
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

    private int totalQuantitySold(final LocalDate date) {
        return this.shop.getSales((sale) -> date.isEqual(sale.getDate())).stream()
                .mapToInt((sale) -> sale.getTotalQuantity())
                .sum();
    }

    @Override 
    public Map<LocalDate, Integer> getSoldOnDay(final Predicate<LocalDate> datePredicate) {
        return this.shop.getSales((sale) -> datePredicate.test(sale.getDate())).stream()
                .map((sale) -> sale.getDate())
                .distinct()
                .sorted((date1, date2) -> date1.compareTo(date2))
                .collect(Collectors.toMap((date) -> date, 
                        (date) -> this.totalQuantitySold(date)));
    }

    private Set<Product> allSalesCategory(final Category category) {
        return this.shop.getSales().stream()
                .flatMap((sale) -> sale.getProducts().stream()
                        .filter((product) -> product.getCategory().equals(category)))
                .distinct()
                .sorted((product1, product2) -> product1.getName().compareTo(product2.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public Map<Category, Integer> getCategoriesSold() {
        return this.shop.getSales().stream()
                .flatMap((sale) -> sale.getProducts().stream().map((product) -> product.getCategory()))
                .distinct()
                .sorted((product1, product2) -> product1.getName().compareTo(product2.getName()))
                .collect(Collectors.toMap((category) -> category, 
                        (category) -> this.allSalesCategory(category).size()));
    }

    private double spentInYear(final int year) {
        return this.shop.getBills().entrySet().stream()
               .filter((entry) -> entry.getKey().getYear() == year)
               .mapToDouble((entry) -> entry.getValue())
               .sum();
    }

    private double earnedInYear(final int year) {
        return this.shop.getSales().stream()
                .filter((sale) -> sale.getDate().getYear() == year)
                .mapToDouble((sale) -> sale.getTotalSpent())
                .sum();
    }

    @Override
    public Map<Integer, Double> getTotalSpentByYear() {
        return this.shop.getBills().entrySet().parallelStream()
                .map((entry) -> entry.getKey().getYear())
                .distinct()
                .collect(Collectors.toMap((year) -> year, (year) -> this.spentInYear(year)));
    }

    @Override
    public Map<Integer, Double> getTotalEarnedByYear() {
        return this.shop.getSales().parallelStream()
                .map((sale) -> sale.getDate().getYear())
                .distinct()
                .collect(Collectors.toMap((year) -> year, (year) -> this.earnedInYear(year)));
    }

    private double earnedInMonth(final Month month, final Predicate<Integer> year) {
        return this.shop.getSales().parallelStream()
                .filter((sale) -> year.test(sale.getDate().getYear()) && sale.getDate().getMonth() == month)
                .mapToDouble((sale) -> sale.getTotalSpent())
                .sum();
    }

    @Override
    public Map<Month, Double> getTotalEarnedByMonth(final Predicate<Integer> year) {
        return this.shop.getSales().parallelStream()
                .filter((sale) -> year.test(sale.getDate().getYear()))
                .map((sale) -> sale.getDate().getMonth())
                .distinct()
                .collect(Collectors.toMap((month) -> month, (month) -> this.earnedInMonth(month, year)));
    }

    private double spentInMonth(final Month month, final Predicate<Integer> year) {
        return this.shop.getBills().entrySet().parallelStream()
                .filter((entry) -> year.test(entry.getKey().getYear()) && entry.getKey().getMonth() == month)
                .mapToDouble((entry) -> entry.getValue())
                .sum();
    }

    @Override
    public Map<Month, Double> getTotalSpentByMonth(final Predicate<Integer> year) {
        return this.shop.getBills().entrySet().parallelStream()
                .filter((entry) -> year.test(entry.getKey().getYear()))
                .map((entry) -> entry.getKey().getMonth())
                .distinct()
                .collect(Collectors.toMap((month) -> month, (month) -> this.spentInMonth(month, year)));
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

    @Override
    public double getTotalAmountSpent() {
        return this.shop.getBills().entrySet().stream()
                .mapToDouble((entry) -> entry.getValue())
                .sum();
    }
}
