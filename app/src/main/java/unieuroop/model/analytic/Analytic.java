package unieuroop.model.analytic;

import java.util.List;
import java.util.LinkedList;
import unieuroop.model.sale.Sale;

/**
 * 
 * Class used for Analytics on sales
 *
 */
public class Analytic {
	
	private final List<Sale> sales;
	
	
	public Analytic() {
		this.sales = new LinkedList<>();
	}
}
