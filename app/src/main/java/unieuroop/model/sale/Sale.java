package unieuroop.model.sale;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import unieuroop.model.person.Client;
import unieuroop.model.product.Product;

public interface Sale {

    /**
    * 
    * @return the specific Date of the sale
    */
    LocalDate getDate();

    /**
    * 
    * @return the copy of all products buyed, stored in a Set
    */
    Set<Product> getProducts();

    /**
    * 
    * @return the total price of the sale 
    */
    double getTotalSpent();

    /**
     * 
     * @param product
     * @return the total quantity of the specified product, if the product is not contained, 
     * I return 0
     */
    int getQuantityOf(Product product);

    /**
     * 
     * @return the sum of all Product's quantity.
     */
    int getTotalQuantity();
    /**
     * 
     * @return the client of this specific Sale, is optional
     */
    Optional<Client> getClient();
}
