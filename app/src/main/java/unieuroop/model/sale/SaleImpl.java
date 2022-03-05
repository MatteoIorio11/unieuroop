package unieuroop.model.sale;

import unieuroop.model.product.Product;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class SaleImpl {
	
	private final Date date;
	private final Map<Product, Integer> productsBuyed;
	private final Optional<Client> client;
	/*private final int id;ID created with the hashCode ???????????*/
	
	/**
	 * Constructor of Sale, it requires :
	 * @param dateSale : date of the sale
	 * @param products : the map of product buyed and their quantity. Product-Quantity
	 * @param client   : this parameter can be Empty, the most important thing is the Sale not who buyed 
	 */
	public SaleImpl(final Date dateSale, final Map<Product, Integer> products, final Optional<Client> client) {
		this.date = dateSale;
		this.productsBuyed = Map.copyOf(products);	
		this.client = client;
	}
	
	/**
	 * 
	 * @return the Date of the sale
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * 
	 * @return the copy of all products buyed
	 */
	public Set<Product> getProducts() {
		return Set.copyOf(this.productsBuyed.keySet());
	}
	
	/**
	 * 
	 * @return the total price of the sale 
	 */
	public float getTotalPrice() {
		return this.productsBuyed.entrySet().stream()
				.map((e) -> e.getKey().getSellingPrice() * e.getValue())
				.reduce((price1, price2) -> price1 + price2).get();
	}
	
	
	
}
