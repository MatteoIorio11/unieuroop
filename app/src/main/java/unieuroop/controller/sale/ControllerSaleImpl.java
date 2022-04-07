package unieuroop.controller.sale;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.department.Department;
import unieuroop.model.person.Client;
import unieuroop.model.product.Product;
import unieuroop.model.sale.Sale;
import unieuroop.model.sale.SaleImpl;
import unieuroop.model.shop.Shop;

public final class ControllerSaleImpl {
    private final Map<Department, Map<Product, Integer>> reservedProductsMap = new HashMap<>();
    private final Shop shop;

    public ControllerSaleImpl(final Shop shop) {
        this.shop = shop;
    }

    public int getQuantityOf(final Product product, final Department department) {
        final int quantity = department.getAllProducts().get(product);
        return this.reservedProductsMap.containsKey(department) && this.reservedProductsMap.get(department).containsKey(product)
                 ? this.reservedProductsMap.get(department).get(product) - quantity : quantity;

    }

    public void reserveProducts(final Department departmentInput, final Map<Product, Integer> products) {
        System.out.println(this.reservedProductsMap);
        this.reservedProductsMap.merge(departmentInput, products, 
                (olderMap, newerMap) -> {
                    olderMap.putAll(newerMap); return olderMap;
                    });
    }

    public void closeSale(final Optional<Client> client) {
        if (!this.reservedProductsMap.isEmpty()) {
            System.out.println(this.reservedProductsMap);
            for (final var entry : this.reservedProductsMap.entrySet()) {
                final Department department = this.shop.getDepartments().stream()
                        .filter((d) -> d.equals(entry.getKey())).findFirst().get();
                department.takeProductFromDepartment(entry.getValue());
            }
            final Map<Product, Integer> products = this.reservedProductsMap.entrySet().stream().map((entry) -> entry.getValue())
                        .flatMap((m) -> m.entrySet().stream())
                        .map((entry) -> entry.getKey())
                        .distinct()
                        .collect(Collectors.toMap((product) -> product, (product) -> this.totalQuantityProduct(product)));
            final Sale sale = new SaleImpl(LocalDate.now(), products, client);
            this.shop.addSale(sale);
            try {
                Serialization.<Set<Sale>>serialize(Files.SALES.getPath(), this.shop.getSales());
                Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.reservedProductsMap.clear();
        }
    }

    public void clearReservedProducts() {
        this.reservedProductsMap.clear();
    }

    private int totalQuantityProduct(final Product product) {
        return this.reservedProductsMap.entrySet().stream()
                .flatMapToInt((entry) -> entry.getValue().entrySet().stream()
                        .filter((e) -> e.getKey().equals(product))
                        .mapToInt((e) -> e.getValue()))
                .sum();
    }
    public Map<Product, Integer> getReservedProducts() {
        return Map.copyOf(this.reservedProductsMap.entrySet().stream()
                    .flatMap((entry) -> entry.getValue().entrySet().stream())
                    .map((entry) -> entry.getKey())
                    .distinct()
                    .collect(Collectors.toMap((product) -> product, (product) -> this.totalQuantityProduct(product))));
    }

    public boolean isReserved() {
        return this.reservedProductsMap.isEmpty();
    }

}
