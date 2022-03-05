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
	/*POTREBBE AVERE SENSO AVERE QUA DENTRO UNA SERIE DI VARIABILI PER IMPLEMENTARE L'EFFICIENZA NEI 
	 * CALCOLO PER RENDERLI MOLTO PIU VELOCI OPPURE PROPRIO UNA CLASSE CON UNA SERIE DI MAPPE Da vedere */

	/**
	 * Constructor of Analytic, in this method we initialize the List of sale
	 */
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
	/**
	 * 
	 * @param product
	 * @return the total amount sold of the "product"
	 */
	public int getQuantitySoldOf(final Product product) {
	    return this.sales.stream()
	            .flatMap((sale) -> sale.getProducts().stream()
	                    .filter((singleProduct) -> singleProduct.getProductCode() == product.getProductCode()))
	            .collect(Collectors.toList()).size();
	}
	/**
	 * PROMEMRORIA : NELLA VIEW DOVRO COSTRUIRE UN SET DELLE CATEOGRIE E NEL PREDICATE METTERE
	 * (categoria) -> set.contains(categoria) ====> DOVE set VIENE COSTRUITO IN BASE A CIO CHE SI SCEGLIE DALLA VIEW 
	 * @return a
	 */
	public Map<Product, Integer> getOrdered(final Predicate<Category> predicate) {
	    return this.sales.stream()
	            .flatMap((sale) -> sale.getProducts().stream()
	                    .filter((product) -> predicate.test(product.getCategory())))
	            .collect(Collectors.toMap((product) -> product, (procuct) -> this.getQuantitySoldOf(procuct)));
	}
}
