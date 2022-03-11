package unieuroop.test.analytic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.sale.Sale;
import unieuroop.model.sale.SaleImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierImpl;
import unieuroop.model.analytic.Analytic;
import unieuroop.model.analytic.AnalyticImpl;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;

public class TestAnalytic {

    private static final int P1_TOTAL_SOLD = 31; /*sum of all p1s product*/
    private static final int P2_TOTAL_SOLD = 300; /*sum of all p2s product*/
    private static final int P3_TOTAL_SOLD = 11; /*sum of all p3s product*/;

    private static final String APPLE_PRODUCT = "APPLE"; /*Brand of products*/
    private static final int TOTAL_PRODUCT_SOLD = 7;  /*all the total product sold */

    private Analytic analytic;
    private final Shop shop = new ShopImpl("TEST");
    private final Supplier s1 = new SupplierImpl();
    private final Product p1 = new ProductImpl(1, "iphone 13 pro", TestAnalytic.APPLE_PRODUCT,  1200.00,  900.00, Optional.empty(), "best phone ever created", Category.SMARTPHONE, s1);
    private final Product p2 = new ProductImpl(2, "applewatch", TestAnalytic.APPLE_PRODUCT, 500.00,  200.00, Optional.empty(), "best watch ever created", Category.SMARTWATCH, s1);
    private final Product p3 = new ProductImpl(3, "mac book pro 14 ", TestAnalytic.APPLE_PRODUCT,  3000.00, 2000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p4 = new ProductImpl(4, "mac book pro 16", TestAnalytic.APPLE_PRODUCT,  6000.00,  3000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p5 = new ProductImpl(5, "ipad Air ", TestAnalytic.APPLE_PRODUCT,  700.00,  300.00, Optional.empty(), "best ipad ever created", Category.HOME, s1);
    private final Product p6 = new ProductImpl(6, "ipad Pro", TestAnalytic.APPLE_PRODUCT, 1000.00, 500.00, Optional.empty(), "best ipad Pro ever created", Category.HOME, s1);
    private final Product p7 = new ProductImpl(7, "ipad Pro Max", TestAnalytic.APPLE_PRODUCT, 1200.00,  900.00, Optional.empty(), "best ipad pro max ever created", Category.HOME, s1);
    private final Product p8 = new ProductImpl(8, "ipad Pro Max v2", TestAnalytic.APPLE_PRODUCT, 1200.00, 900.00, Optional.empty(), "best ipad pro max ever created", Category.HOME, s1);

    private final Sale sale1 = new SaleImpl(LocalDate.now(), Map.of(p1, 10, p2, 100, p5, 1), Optional.empty());
    private final Sale sale2 = new SaleImpl(LocalDate.now(), Map.of(p1, 10, p2, 100, p5, 1, p7, 10), Optional.empty());
    private final Sale sale3 = new SaleImpl(LocalDate.now(), Map.of(p5, 10, p2, 100, p6, 1), Optional.empty());
    private final Sale sale4 = new SaleImpl(LocalDate.now(), Map.of(p3, 10, p7, 100, p1, 1), Optional.empty());
    private final Sale sale5 = new SaleImpl(LocalDate.now(), Map.of(p1, 10, p4, 100, p3, 1), Optional.empty());

    /**
     * Set up of the class AnalyticImpl.
     */

    @Before
    public void setUp() {
       this.shop.addSale(sale1);
       this.shop.addSale(sale2);
       this.shop.addSale(sale3);
       this.shop.addSale(sale4);
       this.shop.addSale(sale5);
       analytic = new AnalyticImpl(shop);
    }

    /**
     * Test the total product Sold in all Sales.
     */
    @Test
    public void testGetTotalProductsSold() {
        assertNotEquals(Collections.emptyList(), this.analytic.getTotalProductsSold());
        /*Check if all 7 product are contained in Analytic*/
        assertTrue(this.analytic.getTotalProductsSold().contains(p1));
        assertTrue(this.analytic.getTotalProductsSold().contains(p2));
        assertTrue(this.analytic.getTotalProductsSold().contains(p3));
        assertTrue(this.analytic.getTotalProductsSold().contains(p4));
        assertTrue(this.analytic.getTotalProductsSold().contains(p5));
        assertTrue(this.analytic.getTotalProductsSold().contains(p6));
        assertTrue(this.analytic.getTotalProductsSold().contains(p7));
        /*Check if a specific product that is not present in any Sale is contained in Analytic*/
        assertFalse(this.analytic.getTotalProductsSold().contains(p8));
        /*Check if all the seven product are inside the list of all product sold*/
        assertEquals(TestAnalytic.TOTAL_PRODUCT_SOLD, this.analytic.getTotalProductsSold().size());
    }

    /**
     * Test on quantity of total bought products, checking if the sum of all products bought are correct.
     */
    @Test
    public void testQuantitySoldOf() {

        assertEquals(TestAnalytic.P1_TOTAL_SOLD, this.analytic.getQuantitySoldOf(p1));
        assertEquals(0, this.analytic.getQuantitySoldOf(p8)); /*p8 does not exist in all the sales*/

        /*Add the new sale inside the analytic with the product p8*/
        final Sale sale6 = new SaleImpl(LocalDate.now(), Map.of(p8, 100), Optional.empty());
        this.shop.addSale(sale6);
        assertEquals(100, this.analytic.getQuantitySoldOf(p8));
        assertEquals(TestAnalytic.P2_TOTAL_SOLD, this.analytic.getQuantitySoldOf(p2));
        assertNotEquals(0, this.analytic.getQuantitySoldOf(p5));
    }
    /**
     * Test of getOrderedByCategory, this method return a Map contain a Product and it's quantity sold.
     * In this test I have to check if every product's category in Sale are present in the analytic's result.
     */
    @Test
    public void testOrderedByCategory1() {
        final Set<Category> categories = Set.of(Category.HOME, Category.PC, 
                Category.SMARTPHONE, Category.SMARTWATCH);

        assertNotEquals(Collections.emptyMap(), 
                this.analytic.getOrderedByCategory((category) -> categories.contains(category)));

        assertNotEquals(Collections.emptyMap(), 
                this.analytic.getOrderedByCategory((category) -> category == Category.SMARTPHONE));

        assertNotEquals(Collections.emptyMap(), 
                this.analytic.getOrderedByCategory((category) -> category == Category.HOME));

        assertNotEquals(Collections.emptyMap(), 
                this.analytic.getOrderedByCategory((category) -> category == Category.SMARTWATCH));

        assertNotEquals(Collections.emptyMap(), 
                this.analytic.getOrderedByCategory((category) -> category == Category.PC));

        /*we don't have any product of type Tablet*/
        assertEquals(Collections.emptyMap(), 
                this.analytic.getOrderedByCategory((category) -> category == Category.TABLET));
    }
    /**
     * Test of getOrderedByCategory, this method return a Map contain a Product and it's quantity sold.
     * Test if every categories on sale are present in Analytic's result. By adding or removing some categories
     * in the Predicate of the method
     */
    @Test
    public void testOrderedByCategory2() {
        final Set<Category> categories = new HashSet<>(Set.of(Category.SMARTPHONE, Category.SMARTWATCH));
        Map<Product, Integer> products = this.analytic.getOrderedByCategory((category) -> category == Category.SMARTPHONE);

        assertEquals(1, products.size());
        assertEquals(Set.of(p1), products.keySet());

        products =  this.analytic.getOrderedByCategory((category) -> categories.contains(category));
        final int totalP1products = products.get(p1);
        final int totalP2products = products.get(p2);

        assertEquals(Set.of(p1, p2), products.keySet());
        assertTrue(this.analytic.getOrderedByCategory((category) -> category == Category.SMARTPHONE).get(p1) > 0);
        assertEquals(TestAnalytic.P1_TOTAL_SOLD, totalP1products);
        assertEquals(TestAnalytic.P2_TOTAL_SOLD, totalP2products);

        categories.add(Category.PC);
        products =  this.analytic.getOrderedByCategory((category) -> categories.contains(category));
        final int totalP3products = products.get(p3);
        assertEquals(Set.of(p1, p2, p3, p4), products.keySet());
        assertEquals(TestAnalytic.P3_TOTAL_SOLD, totalP3products);

        categories.add(Category.TABLET);
        products =  this.analytic.getOrderedByCategory((category) -> categories.contains(category));
        assertEquals(Set.of(p1, p2, p3, p4), products.keySet());

        categories.remove(Category.SMARTPHONE);
        products =  this.analytic.getOrderedByCategory((category) -> categories.contains(category));
        assertEquals(Set.of(p2, p3, p4), products.keySet());

        categories.remove(Category.SMARTWATCH);
        products =  this.analytic.getOrderedByCategory((category) -> categories.contains(category));
        assertEquals(Set.of(p3, p4), products.keySet());

        categories.remove(Category.PC);
        products =  this.analytic.getOrderedByCategory((category) -> categories.contains(category));
        assertEquals(Collections.emptySet(), products.keySet());

        categories.add(Category.TABLET);
        assertEquals(Collections.emptySet(), products.keySet());
    }

    @Test
    public void testProductByDate() {
        final Set<LocalDate> dates = new HashSet<>(Set.of(LocalDate.now()));
        final Set<Product> products = this.analytic.getProductByDate((date) -> dates.contains(date));

        assertNotEquals(Collections.emptySet(), products);
        assertEquals(Set.of(p1, p2, p3, p4, p5, p6, p7), products);
        
        final int totalEarnedToday = products.g
    }

    @Test
    public void testBestSoldDays() {
        
    }

    @Test
    public void testCategoryDate() {
        
    }

    @Test
    public void testCategoriesSold() {
        
    }

    @Test
    public void testTotalEarned() {
    }
}
