package unieuroop.controller.sale;

import java.util.Map;
import java.util.Optional;

import unieuroop.model.department.Department;
import unieuroop.model.person.Client;
import unieuroop.model.product.Product;

public interface ControllerSale {

    int getQuantityOf(Product product, Department department);

    void reserveProducts(Department departmentInput, Map<Product, Integer> products);

    void closeSale(Optional<Client> client);

    void clearReservedProducts();

    Map<Product, Integer> getReservedProducts();

    boolean isReserved();

}