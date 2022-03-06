package unieuroop.model.sale;

import java.time.LocalDate;
import java.util.Set;

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
	float getTotalPrice();

}