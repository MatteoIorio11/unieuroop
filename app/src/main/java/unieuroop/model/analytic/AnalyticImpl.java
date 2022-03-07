package unieuroop.model.analytic;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import unieuroop.model.sale.Sale;
import unieuroop.model.product.Product;
import unieuroop.model.product.Category;

public final class AnalyticImpl implements Analytic {

    private final List<Sale> sales;
    /*POTREBBE AVERE SENSO AVERE QUA DENTRO UNA SERIE DI VARIABILI PER IMPLEMENTARE L'EFFICIENZA NEI 
     * CALCOLO PER RENDERLI MOLTO PIU VELOCI OPPURE PROPRIO UNA CLASSE CON UNA SERIE DI MAPPE Da vedere */

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
                        .filter((singleProduct) -> singleProduct.getProductCode() == product.getProductCode()))
                .collect(Collectors.toList()).size();
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
    public List<Product> getOrderedByDate(final Predicate<Date> date) {
        return this.sales.stream()
                .filter((sale) -> date.test(sale.getDate()))
                .flatMap((sale) -> sale.getProducts().stream())
                .collect(Collectors.toList());
    }

    @Override 
    public List<Product> getBestSoldDay() {
        return Collections.emptyList();
    }

    @Override
    public List<Product> getProductByCategoryDate(final BiPredicate<Date, Category> predicate) {
        return Collections.emptyList();
    }


}
