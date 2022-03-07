package unieuroop.model.analytic;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Collections;
import java.time.LocalDate;
import java.util.LinkedList;
import unieuroop.model.sale.Sale;
import unieuroop.model.product.Product;
import unieuroop.model.product.Category;

public final class AnalyticImpl implements Analytic {

    private final List<Sale> sales;
    /**
     * Constructor of Analytic, in this method we initialize the List of sale.
     */

    public AnalyticImpl() {
        this.sales = new LinkedList<>();
    }

    @Override
    public void addSale(final Sale sale) {
        this.sales.add(sale);
    }

    @Override
    public List<Product> getTotalProductsSold() {
        return this.sales.stream()
                .flatMap((sale) -> sale.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public int getQuantitySoldOf(final Product product) {
        return this.sales.stream()
                .flatMap((sale) -> sale.getProducts().stream()
                        .filter((singleProduct) -> singleProduct.getProductCode() == product.getProductCode())
                        .map((singleProduct) -> sale.getQuantityOf(singleProduct)))
                .mapToInt((quantity) -> quantity)
                .sum();
    }

    @Override
    public Map<Product, Integer> getOrderedByCategory(final Predicate<Category> categories) {
        return this.sales.stream()
                .flatMap((sale) -> sale.getProducts().stream()
                        .filter((product) -> categories.test(product.getCategory())))
                .collect(Collectors.toMap((product) -> product, (procuct) -> this.getQuantitySoldOf(procuct)));
    }
    /*PR : in the view the predicate will be build and inside it we put some Date*/
    @Override
    public List<Product> getOrderedByDate(final Predicate<LocalDate> date) {
        return this.sales.stream()
                .filter((sale) -> date.test(sale.getDate()))
                .flatMap((sale) -> sale.getProducts().stream())
                .collect(Collectors.toList());
    }

    @Override 
    public Map<LocalDate, List<Product>> getBestSoldDay(final Predicate<LocalDate> datePredicate) {
        return this.sales.stream()
                .map((sale) -> sale.getDate())
                .distinct()
                .collect(Collectors.toMap((date) -> date, 
                        (date) -> this.getOrderedByDate((inputDate) -> datePredicate.test(inputDate))));
    }

    /*VINCI does this*/
    @Override
    public List<Product> getProductByCategoryDate(final BiPredicate<LocalDate, Category> predicate) {
        return Collections.emptyList();
    }

    private List<Product> allSalesCategory(final Category category) {
        return this.sales.stream()
                .flatMap((sale) -> sale.getProducts().stream()
                        .filter((product) -> product.getCategory().equals(category)))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Category, List<Product>> geCategoriesSold() {
        return this.sales.stream()
                .flatMap((sale) -> sale.getProducts().stream().map((product) -> product.getCategory()))
                .distinct()
                .collect(Collectors.toMap((category) -> category, 
                        (category) -> this.allSalesCategory(category)));
    }

    @Override
    public Map<LocalDate, Double> getTotalEarned() {
        return Collections.emptyMap();
    }


}
