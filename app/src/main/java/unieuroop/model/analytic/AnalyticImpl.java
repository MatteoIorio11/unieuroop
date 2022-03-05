package unieuroop.model.analytic;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.LinkedList;
import unieuroop.model.sale.Sale;
import unieuroop.model.product.Product;
import unieuroop.model.sale.NullSaleException;

public class AnalyticImpl {
	
	private final List<Sale> sales;
	
	
	public AnalyticImpl() {
		this.sales = new LinkedList<>();
	}
	/**
	 * 
	 * @param sale
	 * @throws NullSaleException 
	 */
	public void addSale(final Sale sale) throws NullSaleException {
		try {
			this.sales.add(Objects.requireNonNull(sale));
		} catch (NullSaleException exception) {
		    exception.setMessage("Analytic -> addSale (insertion of the sale");
			throw exception;
		}
	}
	/**
	 * 
	 * @return all the product sold in all the different sales
	 */
	public List<Product> getTotalProductsSold() {
	    return this.sales.stream()
	                .flatMap((sale) -> sale.getProducts().stream())
	                .distinct()
	                .collect(Collectors.toList());
	}
}
