package unieuroop.model.sale;

import unieuroop.model.product.Product;
import java.util.Date;
import java.util.Map;

public class Sale {
	
	private final Date date;
	private final Map<Product, Integer> productsBuyed;
	private final Optional<Client> client;
	
	/**
	 * Constructor of Sale, it requires :
	 * @param dateSale : date of the sale
	 * @param products : the map of product buyed, I take the copy of the map
	 */
	public Sale(final Date dateSale, final Map<Product, Integer> products, final Optiona<Client> client) {
		this.date = dateSale;
		this.productsBuyed = Map.copyOf(products);
		this.client = client;
	}
	
	
}
