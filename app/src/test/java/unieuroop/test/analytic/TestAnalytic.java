package unieuroop.test.analytic;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.sale.Sale;
import unieuroop.model.sale.SaleImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.analytic.Analytic;
import unieuroop.model.analytic.AnalyticImpl;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;

public class TestAnalytic {

    private static final int FIRST_RESULT = 31; /*sum of all p1s product*/
    private static final int SECOND_RESULT = 300; /*sum of all p2s product*/
    private static final int TOTAL_PRODUCT_SOLD = 7;  /*all the total product sold */

    private Analytic analytic;
    private final Supplier s1 = null;
    private final Product p1 = new ProductImpl(1, "iphone 13 pro", (float) 1200.00, (float) 900.00, Optional.empty(), "best phone ever created", Category.SMARTPHONE, s1);
    private final Product p2 = new ProductImpl(2, "applewatch", (float) 500.00, (float) 200.00, Optional.empty(), "best watch ever created", Category.SMARTWATCH, s1);
    private final Product p3 = new ProductImpl(3, "mac book pro 14 ", (float) 3000.00, (float) 2000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p4 = new ProductImpl(4, "mac book pro 16", (float) 6000.00, (float) 3000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p5 = new ProductImpl(5, "ipad Air ", (float) 700.00, (float) 300.00, Optional.empty(), "best ipad ever created", Category.HOME, s1);
    private final Product p6 = new ProductImpl(6, "ipad Pro", (float) 1000.00, (float) 500.00, Optional.empty(), "best ipad Pro ever created", Category.HOME, s1);
    private final Product p7 = new ProductImpl(7, "ipad Pro Max", (float) 1200.00, (float) 900.00, Optional.empty(), "best ipad pro max ever created", Category.HOME, s1);
    private final Product p8 = new ProductImpl(8, "ipad Pro Max", (float) 1200.00, (float) 900.00, Optional.empty(), "best ipad pro max ever created", Category.HOME, s1);

    private final Sale sale1 = new SaleImpl(LocalDate.now(), Map.of(p1, 10, p2, 100, p5, 1), Optional.empty());
    private final Sale sale2 = new SaleImpl(LocalDate.now(), Map.of(p1, 10, p2, 100, p5, 1, p7, 10), Optional.empty());
    private final Sale sale3 = new SaleImpl(LocalDate.now(), Map.of(p5, 10, p2, 100, p6, 1), Optional.empty());
    private final Sale sale4 = new SaleImpl(LocalDate.now(), Map.of(p3, 10, p7, 100, p1, 1), Optional.empty());
    private final Sale sale5 = new SaleImpl(LocalDate.now(), Map.of(p1, 10, p4, 100, p3, 1), Optional.empty());

    private final Shop shop = new ShopImpl();

    @Before
    public void setUp() throws Exception {
       analytic = new AnalyticImpl();
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

        assertEquals(TestAnalytic.FIRST_RESULT, this.analytic.getQuantitySoldOf(p1));
        assertEquals(0, this.analytic.getQuantitySoldOf(p8)); /*p8 does not exist in all the sales*/

        /*Add the new sale inside the analytic with the product p8*/
        final Sale sale6 = new SaleImpl(LocalDate.now(), Map.of(p8, 100), Optional.empty());
        this.analytic.addSale(sale6);

        assertEquals(100, this.analytic.getQuantitySoldOf(p8));
        assertEquals(TestAnalytic.SECOND_RESULT, this.analytic.getQuantitySoldOf(p2));
        assertNotEquals(0, this.analytic.getQuantitySoldOf(p5));

    }

    @Test
    public void testOrderedByCategory() {
        
    }

    @Test
    public void testOrderedByDate() {
        
    }

    @Test
    public void testBestSoldDays() {
        
    }

    @Test
    public void testCategoryDate() {
        
    }
}
