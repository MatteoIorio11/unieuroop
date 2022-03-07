package unieuroop.model.stock;

import java.util.Set;
import unieuroop.model.product.Product;

public interface Stock {
	
	void addProduct(Product product, int amount);
	
	
	Set<Product> getTotalStock();
	
	int getQuantityOfProduct(Product product);
	
	float getTotalPrice();
	

}
