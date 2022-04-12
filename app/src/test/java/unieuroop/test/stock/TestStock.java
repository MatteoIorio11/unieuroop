package unieuroop.test.stock;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;

public class TestStock {

    private static final String APPLE_PRODUCT = "apple";
    private final Shop shop = new ShopImpl("Shop test");
    @Test
    public void testAddProducts() {
        final Product p1 = new ProductImpl(1, "iphone 13 pro", TestStock.APPLE_PRODUCT,  1200.00,  900.00, "best phone ever created", Category.SMARTPHONE);
        final Product p2 = new ProductImpl(2, "applewatch", TestStock.APPLE_PRODUCT, 500.00,  200.00, "best watch ever created", Category.SMARTWATCH);
        final Product p3 = new ProductImpl(3, "mac book pro 14 ", TestStock.APPLE_PRODUCT,  3000.00, 2000.00, "best mac book ever created", Category.PC);
        this.shop.getStock().addProducts(Map.of(p1, 2, p2, 4, p3, 1));
        assertEquals(Map.of(p1, 2, p2, 4, p3, 1), this.shop.getStock().getTotalStock());
    }

    @Test
    public void testQuantityOf() {
        final Product p4 = new ProductImpl(4, "mac book pro 16", TestStock.APPLE_PRODUCT,  6000.00,  3000.00, "best mac book ever created", Category.PC);
        final Product p5 = new ProductImpl(5, "ipad Air ", TestStock.APPLE_PRODUCT,  700.00,  300.00, "best ipad ever created", Category.HOME);
        final Product p6 = new ProductImpl(6, "ipad Pro", TestStock.APPLE_PRODUCT, 1000.00, 500.00, "best ipad Pro ever created", Category.HOME);
        final Product p7 = new ProductImpl(7, "ipad Pro Max", TestStock.APPLE_PRODUCT, 1200.00,  900.00, "best ipad pro max ever created", Category.HOME);
        this.shop.getStock().addProducts(Map.of(p4, 1, p5, 2, p6, 1, p7, 2));
        assertEquals(1, this.shop.getStock().getQuantityOfProduct(p4));
        assertEquals(2, this.shop.getStock().getQuantityOfProduct(p5));
        assertEquals(1, this.shop.getStock().getQuantityOfProduct(p6));
        assertEquals(2, this.shop.getStock().getQuantityOfProduct(p7));
    }

    @Test
    public void testTakeFromStock1() {
        final Product p1 = new ProductImpl(1, "iphone 13 pro", TestStock.APPLE_PRODUCT,  1200.00,  900.00, "best phone ever created", Category.SMARTPHONE);
        final Product p2 = new ProductImpl(2, "applewatch", TestStock.APPLE_PRODUCT, 500.00,  200.00, "best watch ever created", Category.SMARTWATCH);
        final Product p3 = new ProductImpl(3, "mac book pro 14 ", TestStock.APPLE_PRODUCT,  3000.00, 2000.00, "best mac book ever created", Category.PC);
        this.shop.getStock().addProducts(Map.of(p1, 1, p2, 2, p3, 1));
        try {
            this.shop.getStock().takeFromStock(Map.of(p1, 1));
            assertEquals(Map.of(p1, 0, p2, 2, p3, 1), this.shop.getStock().getTotalStock());
        } catch (IllegalArgumentException ex) {
            fail("No exception have to be thrown");
        }
    }

    @Test
    public void testTakeFromStock2() {
        final Product p1 = new ProductImpl(1, "iphone 13 pro", TestStock.APPLE_PRODUCT,  1200.00,  900.00, "best phone ever created", Category.SMARTPHONE);
        final Product p2 = new ProductImpl(2, "applewatch", TestStock.APPLE_PRODUCT, 500.00,  200.00, "best watch ever created", Category.SMARTWATCH);
        final Product p3 = new ProductImpl(3, "mac book pro 14 ", TestStock.APPLE_PRODUCT,  3000.00, 2000.00, "best mac book ever created", Category.PC);
        this.shop.getStock().addProducts(Map.of(p1, 1, p2, 2, p3, 1));
        try {
            this.shop.getStock().takeFromStock(Map.of(p1, 2));
            fail("Exception must be throwned.");
        } catch (IllegalArgumentException ex) {
            assertEquals("Some products can not be taken", ex.getMessage());
        }
    }

    @Test
    public void testDeleteProduct1() {
        final Product p1 = new ProductImpl(1, "iphone 13 pro", TestStock.APPLE_PRODUCT,  1200.00,  900.00, "best phone ever created", Category.SMARTPHONE);
        this.shop.getStock().addProducts(Map.of(p1, 1));
        try {
            this.shop.getStock().deleteProducts(Set.of(p1));
        } catch (IllegalArgumentException ex) {
            fail("product p1 exist inside the stock.");
        }
    }

    @Test
    public void testDeleteProduct2() {
        final Product p1 = new ProductImpl(1, "iphone 13 pro", TestStock.APPLE_PRODUCT,  1200.00,  900.00, "best phone ever created", Category.SMARTPHONE);
        final Product p2 = new ProductImpl(2, "applewatch", TestStock.APPLE_PRODUCT, 500.00,  200.00, "best watch ever created", Category.SMARTWATCH);
        final Product p3 = new ProductImpl(3, "mac book pro 14 ", TestStock.APPLE_PRODUCT,  3000.00, 2000.00, "best mac book ever created", Category.PC);
        this.shop.getStock().addProducts(Map.of(p1, 1, p2, 2));
        try {
            this.shop.getStock().deleteProducts(Set.of(p3));
            fail("product p3 does not exist inside the stock.");
        } catch (IllegalArgumentException ex) {
            assertEquals("Some products can not be deleted", ex.getMessage());
        }
    }

    @Test
    public void testFilterProducts() {
        final Product p1 = new ProductImpl(1, "iphone 13 pro", TestStock.APPLE_PRODUCT,  1200.00,  900.00, "best phone ever created", Category.SMARTPHONE);
        final Product p2 = new ProductImpl(2, "applewatch", TestStock.APPLE_PRODUCT, 500.00,  200.00, "best watch ever created", Category.SMARTWATCH);
        final Product p3 = new ProductImpl(3, "mac book pro 14 ", TestStock.APPLE_PRODUCT,  3000.00, 2000.00, "best mac book ever created", Category.PC);
        this.shop.getStock().addProducts(Map.of(p1, 1, p2, 2, p3, 1));
        assertEquals(Map.of(p1, 1), this.shop.getStock().getFilterProducts((quantity, category) -> category == Category.SMARTPHONE));
        assertEquals(Map.of(p2, 2), this.shop.getStock().getFilterProducts((quantity, category) -> category == Category.SMARTWATCH));
        assertEquals(Map.of(p3, 1), this.shop.getStock().getFilterProducts((quantity, category) -> category == Category.PC));
        assertEquals(Map.of(p1, 1, p3, 1), this.shop.getStock().getFilterProducts((quantity, category) -> quantity == 1));
        assertEquals(Collections.emptyMap(), this.shop.getStock().getFilterProducts((quantity, category) -> quantity == 1 
                && category == Category.DOMESTIC_APPLIANCE));
    }

    @Test
    public void testSorted() {
        final Product p1 = new ProductImpl(1, "iphone 13 pro", TestStock.APPLE_PRODUCT,  1200.00,  900.00, "best phone ever created", Category.SMARTPHONE);
        final Product p2 = new ProductImpl(2, "applewatch", TestStock.APPLE_PRODUCT, 500.00,  200.00, "best watch ever created", Category.SMARTWATCH);
        final Product p3 = new ProductImpl(3, "mac book pro 14 ", TestStock.APPLE_PRODUCT,  3000.00, 2000.00, "best mac book ever created", Category.PC);
        this.shop.getStock().addProducts(Map.of(p1, 1, p2, 2, p3, 1));
        final var productsGrowing = this.shop.getStock().getProductsSorted((product1, product2) -> product1.getName().compareTo(product2.getName()));
        final var productsDecreasing = this.shop.getStock().getProductsSorted((product1, product2) -> product2.getName().compareTo(product1.getName()));
        assertEquals(List.of(p2, p1, p3), productsGrowing);
        assertEquals(List.of(p3, p1, p2), productsDecreasing);
    }
}
