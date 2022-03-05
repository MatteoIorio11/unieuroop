package unieuroop.model.analytic;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.LinkedList;
import unieuroop.model.sale.Sale;
import unieuroop.model.product.Product;
import unieuroop.model.product.Category;
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
	public int getQuantitySoldOf(final Product product) {
	    return 0;
	}
	/**
	 * PROMEMRORIA : NELLA VIEW DOVRO COSTRUIRE UN SET DELLE CATEOGRIE E NEL PREDICATE METTERE
	 * (categoria) -> set.contains(categoria) ====> DOVE set VIENE COSTRUITO IN BASE A CIO CHE SI SCEGLIE DALLA VIEW 
	 * @return a
	 */
	public Map<Product, Integer> getOrdered(final Predicate<Set<Category>> predicate) {
	    final Map<Product, Integer> out = this.sales.stream()
	            .flatMap((sale) -> sale.getProducts().stream()
	                    .filter((product) -> predicate.test(product.getCategory())))
	            .collect(Collectors.toMap((product) -> product, (prod)))
	    return null;
	}
}
