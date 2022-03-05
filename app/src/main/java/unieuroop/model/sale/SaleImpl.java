package unieuroop.model.sale;

import unieuroop.model.product.Product;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class SaleImpl {
	
	private final Date date;
	private final Map<Product, Integer> productsBuyed;
	private final Optional<Client> client;
	
	/**
	 * Constructor of Sale, it requires :
	 * @param dateSale : date of the sale
	 * @param products : the map of product buyed, I take the copy of the map
	 * @param client   : this parameter can be Empty, the most important thing is the Sale not who buyed 
	 */
	public SaleImpl(final Date dateSale, final Map<Product, Integer> products, final Optional<Client> client) {
		this.date = dateSale;
		this.productsBuyed = Map.copyOf(products);	
		this.client = client;
	}
	
	/**
	 * 
	 * @return the copy of all products buyed
	 */
	public Set<Product> getProducts(){
		return Set.copyOf(this.productsBuyed.keySet());
	}
	
	public Float getTotalPrice() {
		return this.productsBuyed.entrySet().stream()
				.map((e) -> e.getKey().getSellingPrice())
				.reduce(0, (price1, price2) -> price1+price2);
	}
	
	
	
}
