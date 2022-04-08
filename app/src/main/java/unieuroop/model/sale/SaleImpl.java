package unieuroop.model.sale;

import unieuroop.model.product.Product;
import unieuroop.model.person.Client;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class SaleImpl implements Sale {

    private final LocalDate date;
    private final Map<Product, Integer> productsBuyed;
    private final Optional<Client> client;

    /**
     * Constructor of Sale, it requires.
     * @param dateSale : date of the sale
     * @param products : the map of product buyed and their quantity. Product-Quantity
     * @param client   : this parameter can be Empty, the most important thing is the Sale not who buyed 
     */
    public SaleImpl(final LocalDate dateSale, final Map<Product, Integer> products, final Optional<Client> client) {
        this.date = dateSale;
        this.productsBuyed = Map.copyOf(products);
        this.client = client;
    }

    /**
     * 
     * @return the Date of the sale
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * 
     * @return the copy of all products buyed
     */
    @Override
    public Set<Product> getProducts() {
        return Set.copyOf(this.productsBuyed.keySet());
    }

    /**
     * 
     * @return the total price of the sale 
     */
    @Override
    public double getTotalSpent() {
        return this.productsBuyed.entrySet().stream()
                .map((e) -> e.getKey().getSellingPrice() * e.getValue())
                .reduce((price1, price2) -> price1 + price2).get();
    }

    @Override
    public int getQuantityOf(final Product product) {
        return this.productsBuyed.containsKey(product) ? this.productsBuyed.get(product) : 0;
    }

    @Override
    public int getTotalQuantity() {
        return this.productsBuyed.entrySet().stream()
                .mapToInt((entry) -> entry.getValue())
                .sum();
    }

    @Override
    public Optional<Client> getClient() {
        return this.client;
    }

    @Override
    public String toString() {
        return null;
    }
    
}
