package unieuroop.test.analytic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashMap;
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
    /*All the money earned from sales*/
    private static final double TOTAL_SHOP_EARNED = 961_600;
    private static final double TOTAL_SPENT_NOW = 16;
    private static final int TOTAL_SOLD_NOW = 7;
    /*ERROR tolerance*/
    private static final double ERROR_TOLLERANCE = 0.01;
    /*Data for a temporary local date*/
    private static final int YEAR_TEST = 2001;
    private static final int YEAR_TEST2 = 2002;
    private static final int MONTH_TEST = 2;
    private static final int DAY_TEST = 11;

    private static final String APPLE_PRODUCT = "APPLE"; /*Brand of products*/
    private static final int TOTAL_PRODUCT_SOLD = 7;  /*all the total product sold , not the quantity*/
    private static final LocalDate TIME_NOW = LocalDate.now();
    private Analytic analytic;
    private final Shop shop = new ShopImpl("TEST");
    private final Supplier s1 = new SupplierImpl("nome", Map.of());
    /**
     * ALL THE PRODUCTS THAT WILL BE USED IN THIS TEST.
     */
    private final Product p1 = new ProductImpl(1, "iphone 13 pro", TestAnalytic.APPLE_PRODUCT,  1200.00,  900.00, Optional.empty(), "best phone ever created", Category.SMARTPHONE, s1);
    private final Product p2 = new ProductImpl(2, "applewatch", TestAnalytic.APPLE_PRODUCT, 500.00,  200.00, Optional.empty(), "best watch ever created", Category.SMARTWATCH, s1);
    private final Product p3 = new ProductImpl(3, "mac book pro 14 ", TestAnalytic.APPLE_PRODUCT,  3000.00, 2000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p4 = new ProductImpl(4, "mac book pro 16", TestAnalytic.APPLE_PRODUCT,  6000.00,  3000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p5 = new ProductImpl(5, "ipad Air ", TestAnalytic.APPLE_PRODUCT,  700.00,  300.00, Optional.empty(), "best ipad ever created", Category.HOME, s1);
    private final Product p6 = new ProductImpl(6, "ipad Pro", TestAnalytic.APPLE_PRODUCT, 1000.00, 500.00, Optional.empty(), "best ipad Pro ever created", Category.HOME, s1);
    private final Product p7 = new ProductImpl(7, "ipad Pro Max", TestAnalytic.APPLE_PRODUCT, 1200.00,  900.00, Optional.empty(), "best ipad pro max ever created", Category.HOME, s1);
    private final Product p8 = new ProductImpl(8, "ipad Pro Max v2", TestAnalytic.APPLE_PRODUCT, 1200.00, 900.00, Optional.empty(), "best ipad pro max ever created", Category.HOME, s1);
    /**
     * ALL THE SALES THAT WILL BE USED IN THIS TEST.
     */
    private final Sale sale1 = new SaleImpl(TestAnalytic.TIME_NOW, Map.of(p1, 10, p2, 100, p5, 1), Optional.empty());
    private final Sale sale2 = new SaleImpl(TestAnalytic.TIME_NOW, Map.of(p1, 10, p2, 100, p5, 1, p7, 10), Optional.empty());
    private final Sale sale3 = new SaleImpl(TestAnalytic.TIME_NOW, Map.of(p5, 10, p2, 100, p6, 1), Optional.empty());
    private final Sale sale4 = new SaleImpl(TestAnalytic.TIME_NOW, Map.of(p3, 10, p7, 100, p1, 1), Optional.empty());
    private final Sale sale5 = new SaleImpl(TestAnalytic.TIME_NOW, Map.of(p1, 10, p4, 100, p3, 1), Optional.empty());

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
       this.shop.addBills(TestAnalytic.TIME_NOW, 4);
       this.shop.addBills(TestAnalytic.TIME_NOW, 10);
       this.shop.addBills(TestAnalytic.TIME_NOW, 2);
       analytic = new AnalyticImpl(shop);
       assertTrue(this.analytic.getTotalShopEarned() > 0);
    }

    /**
     * Test the total product Sold in all Sales.
     */
    @Test
    public void testGetTotalProductsSold() {
        assertNotEquals(Collections.emptySet(), this.analytic.getTotalProductsSold());
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
     *  TEST FOR : analytic.getQuantityOf(Product p) {@link Analytic}
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
     * TEST FOR : analytic.getQuantityOf(Product p, Predicate<LocalDate> date); {@link Analytic}
     * Test quantity of a specific product in a specific date.
     */
    @Test
    public void testQuantitySoldOf2() {
        final LocalDate dateTemp = LocalDate.of(TestAnalytic.YEAR_TEST, TestAnalytic.MONTH_TEST, TestAnalytic.DAY_TEST);
        final int quantityP1 = this.analytic.getQuantitySoldOf(p1,
                (date) -> date.equals(TestAnalytic.TIME_NOW));
        final int quantityP2 = this.analytic.getQuantitySoldOf(p2,
                (date) -> date.equals(TestAnalytic.TIME_NOW));
        int quantityP3 = this.analytic.getQuantitySoldOf(p3,
                (date) -> date.equals(dateTemp));

        assertTrue(quantityP1 > 0);
        assertTrue(quantityP2 > 0);
        assertEquals(0, quantityP3);
        assertEquals(TestAnalytic.P1_TOTAL_SOLD, quantityP1);
        assertEquals(TestAnalytic.P2_TOTAL_SOLD, quantityP2);

        quantityP3 = this.analytic.getQuantitySoldOf(p3, 
                (date) -> date.equals(TestAnalytic.TIME_NOW));
        assertTrue(quantityP3 > 0);
        assertEquals(TestAnalytic.P3_TOTAL_SOLD, quantityP3);
    }
    /**
     * TEST FOR : analytic.getOrderedByCategory(Predicate<Category> c); {@link Analytic}
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
     * TEST FOR : analytic.getOrderedByCategory(Predicate<Category> c); {@link Analytic}
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
    /**
     * TEST FOR : analytic.getOrderedByDate(Predicate<LocalDate> c); {@link Analytic}
     *  Testing the method getProductByDate where we specified a Date or a time lapse,
     *  and we get a Set of all different products sold in the Date or in the time lapse.
     */
    @Test
    public void testProductByDate() {
        final Set<LocalDate> dates = new HashSet<>(Set.of(TestAnalytic.TIME_NOW));
        Set<Product> products = this.analytic.getProductByDate((date) -> dates.contains(date));
        final LocalDate dateTemp = LocalDate.of(TestAnalytic.YEAR_TEST, TestAnalytic.MONTH_TEST, TestAnalytic.DAY_TEST);
        final LocalDate dateTemp2 = LocalDate.of(TestAnalytic.YEAR_TEST2, TestAnalytic.MONTH_TEST, TestAnalytic.DAY_TEST);
        final Sale saleTest = new SaleImpl(dateTemp, Map.of(p8, 1), Optional.empty());
        final Sale saleTest2 = new SaleImpl(dateTemp2, Map.of(p1, 100), Optional.empty());
        final Sale saleTest3 = new SaleImpl(dateTemp2, Map.of(p2, 100), Optional.empty());

        assertNotEquals(Collections.emptySet(), products);
        assertEquals(Set.of(p1, p2, p3, p4, p5, p6, p7), products);

        products = this.analytic.getProductByDate((date) -> date == dateTemp);
        assertEquals(Collections.emptySet(), products);

        this.shop.addSale(saleTest);
        products = this.analytic.getProductByDate((date) -> date == dateTemp);
        assertEquals(Set.of(p8), products);

        products = this.analytic.getProductByDate((date) -> date.getYear() > LocalDate.MIN.getYear());
        assertEquals(Set.of(p1, p2, p3, p4, p5, p6, p7, p8), products);

        this.shop.addSale(saleTest2);
        this.shop.addSale(saleTest3);
        products = this.analytic.getProductByDate((date) -> date.getYear() >= TestAnalytic.YEAR_TEST && date.getYear() <= TestAnalytic.YEAR_TEST2);
        assertEquals(Set.of(p1, p2, p8), products);
    }
    /**
     * TEST FOR : analytic.getSoldOnDay(Predicate<LocalDate> d);{@link Analytic}
     * This method use a Date or a time lapse for return a Map where we find in the key
     * the LocalDate and in the Value we find all the different products sold in that day.
     */
    @Test
    public void testSoldDays() {
        final Set<LocalDate> dates = new HashSet<>(Set.of(TestAnalytic.TIME_NOW));
        Map<LocalDate, Integer> products = this.analytic.getSoldOnDay((date) -> dates.contains(date));
        final LocalDate dateTemp = LocalDate.of(TestAnalytic.YEAR_TEST, TestAnalytic.MONTH_TEST, TestAnalytic.DAY_TEST);
        final Sale saleTest = new SaleImpl(dateTemp, Map.of(p8, 1), Optional.empty());
        int totalProducts = products.get(LocalDate.now());

        assertNotEquals(Collections.emptyMap(), products);
        assertEquals(1, products.size());
        assertEquals(TestAnalytic.TOTAL_SOLD_NOW, totalProducts);

        this.shop.addSale(saleTest);
        dates.add(dateTemp);
        products = this.analytic.getSoldOnDay((date) -> dates.contains(date));
        totalProducts = products.get(LocalDate.now());
        assertNotEquals(Collections.emptyMap(), products);
        assertEquals(2, products.size());
        assertEquals(8, totalProducts);
    }

    /**
     * TEST FOR : analytic.getCategoriesSold(); {@link Analytic}
     * This test is for the method getCategoriesSold, where the method return a Map
     * with Key the category and the Value is all the set of values. 
     */
    @Test
    public void testCategoriesSold() {
        final Map<Category, Integer> categoriesSold = this.analytic.getCategoriesSold();
        final Map<Category, Integer> testMap = new HashMap<>();

        testMap.put(Category.HOME, 3);
        testMap.put(Category.PC, 2);
        testMap.put(Category.SMARTPHONE, 1);
        testMap.put(Category.SMARTWATCH, 1);

        assertNotEquals(Collections.emptyMap(), categoriesSold);
        assertEquals(testMap.get(Category.HOME), categoriesSold.get(Category.HOME));
        assertEquals(testMap.get(Category.PC), categoriesSold.get(Category.PC));
        assertEquals(testMap.get(Category.SMARTPHONE), categoriesSold.get(Category.SMARTPHONE));
        assertEquals(testMap.get(Category.SMARTWATCH), categoriesSold.get(Category.SMARTWATCH));
        assertEquals(testMap.get(Category.TABLET), categoriesSold.get(Category.TABLET));
    }
    /**
     * TEST FOR : analytic.getTotalStockPrice(); {@link Analytic}
     * test the stock price of all products.
     */
    @Test
    public void testTotalStockPrice() {
        this.shop.getStock().addProducts(Map.of(p1, 10, p2, 10, p3, 3));
        final double total = this.analytic.getTotalStockPrice();
        final double totalCheck = p1.getSellingPrice() * 10
                + p2.getSellingPrice() * 10
                + p3.getSellingPrice() * 3;
        assertTrue(total > 0);
        assertEquals(totalCheck, total, TestAnalytic.ERROR_TOLLERANCE);
    }
    /**
     * TEST FOR : analytic.getTotalShopEarned(); {@link Analytic}
     * test where analytic return all the money resulting from sales.
     */
    @Test
    public void testTotalShopEarned() {
        final double totalEarned = this.analytic.getTotalShopEarned();
        assertTrue(totalEarned > 0);
        assertEquals(TestAnalytic.TOTAL_SHOP_EARNED, totalEarned, TestAnalytic.ERROR_TOLLERANCE);
    }
    /**
     * TEST FOR : analytic.getTotalSpentByYear(); {@link Analytic}
     * test where analytic return a map where we find the year and the total spent in that year.
     */
    @Test
    public void testTotalSpentByYear() {
        final Map<Integer, Double> spentYear = this.analytic.getTotalSpentByYear();
        final double value = spentYear.get(TestAnalytic.TIME_NOW.getYear());

        assertFalse(spentYear.isEmpty());
        assertTrue(spentYear.containsKey(TestAnalytic.TIME_NOW.getYear()));
        assertEquals(TestAnalytic.TOTAL_SPENT_NOW, value, TestAnalytic.ERROR_TOLLERANCE);
    }
    /**
     * TEST FOR : analytic.getTotalEarnedByYear(); {@link Analytic}
     * test where analytic return a Map where we can find in the Key the year and in the value the total earned in that year.
     */
    @Test
    public void testTotalEarnedByYear() {
        final Map<Integer, Double> spentYear = this.analytic.getTotalEarnedByYear();
        final double value = spentYear.get(TestAnalytic.TIME_NOW.getYear());

        assertFalse(spentYear.isEmpty());
        assertTrue(spentYear.containsKey(TestAnalytic.TIME_NOW.getYear()));
        assertEquals(TestAnalytic.TOTAL_SHOP_EARNED, value, TestAnalytic.ERROR_TOLLERANCE);
    }
    /**
     *  TEST FOR : analytic.getTotalEarnedByMonth(Predicate<Integer> year); {@link Analytic}
     *  test where analytic return a Map where we can find in the key the month and in the value the total earned in that month. 
     */
    @Test
    public void testTotalEarnedByMonth() {
        final Map<Month, Double> totalEarnedMonth = this.analytic.getTotalEarnedByMonth((year) -> TestAnalytic.TIME_NOW.getYear() == year);
        assertFalse(totalEarnedMonth.isEmpty());
        final double spentInThisMonth = totalEarnedMonth.get(TestAnalytic.TIME_NOW.getMonth());

        assertTrue(spentInThisMonth > 0);
        assertEquals(TestAnalytic.TOTAL_SHOP_EARNED, spentInThisMonth, TestAnalytic.ERROR_TOLLERANCE);
    }
    /**
     * TEST FOR : analytic.getTotalSpentByMonth(Predicate<Integer> year); {@link Analytic}
     * test where analytic return a Map where in the Key there is the month and in the value the total spent in that month.
     */
    @Test
    public void testTotalSpentByMonth() {
        final Map<Month, Double> spentTotalMonth = this.analytic.getTotalSpentByMonth((year) -> TestAnalytic.TIME_NOW.getYear() == year);
        assertFalse(spentTotalMonth.isEmpty());

        final double spent = spentTotalMonth.get(TestAnalytic.TIME_NOW.getMonth());

        assertTrue(spent > 0);
        assertEquals(spent, TestAnalytic.TOTAL_SPENT_NOW, TestAnalytic.ERROR_TOLLERANCE);
    }
}
