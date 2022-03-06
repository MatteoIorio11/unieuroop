package unieuroop.test.analytic;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.sale.NullSaleException;
import unieuroop.model.sale.Sale;
import unieuroop.model.sale.SaleImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.analytic.Analytic;
import unieuroop.model.analytic.AnalyticImpl;

public class TestAnalytic {

    private Analytic analytic = new AnalyticImpl();
    private final Supplier s1 = null;
    private final Product p1 = new ProductImpl(1, "iphone 13 pro", (float) 1200.00, (float) 900.00, Optional.empty(), "best phone ever created", Category.SMARTPHONE, s1);
    private final Product p2 = new ProductImpl(2, "applewatch", (float) 500.00, (float) 200.00, Optional.empty(), "best watch ever created", Category.SMARTWATCH, s1);
    private final Product p3 = new ProductImpl(3, "mac book pro 14 ", (float) 3000.00, (float) 2000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p4 = new ProductImpl(4, "mac book pro 16", (float) 6000.00, (float) 3000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p5 = new ProductImpl(5, "ipad Air ", (float) 700.00, (float) 300.00, Optional.empty(), "best ipad ever created", Category.HOME, s1);
    private final Product p6 = new ProductImpl(6, "ipad Pro", (float) 1000.00, (float) 500.00, Optional.empty(), "best ipad Pro ever created", Category.HOME, s1);
    private final Product p7 = new ProductImpl(7, "ipad Pro Max", (float) 1200.00, (float) 900.00, Optional.empty(), "best ipad pro max ever created", Category.HOME, s1);

    private final Sale sale1 = new SaleImpl(LocalDate.now(), Map.of(p1, 10, p2, 100, p5, 1), Optional.empty());
    private final Sale sale2 = new SaleImpl(LocalDate.now(), Map.of(p1, 10, p2, 100, p5, 1, p7, 10), Optional.empty());
    private final Sale sale3 = new SaleImpl(LocalDate.now(), Map.of(p5, 10, p2, 100, p6, 1), Optional.empty());
    private final Sale sale4 = new SaleImpl(LocalDate.now(), Map.of(p3, 10, p7, 100, p1, 1), Optional.empty());
    private final Sale sale5 = new SaleImpl(LocalDate.now(), Map.of(p1, 10, p4, 100, p3, 1), Optional.empty());

    @Before
    public void setUp() throws Exception {
        /**
         * Constructor of product with discount.
         * @param productCode
         * @param name
         * @param sellingPrice
         * @param purchasePrice
         * @param discount
         * @param description
         * @param category
         * @param supplier
         */


        this.analytic.addSale(sale1);
        this.analytic.addSale(sale2);
        this.analytic.addSale(sale3);
        this.analytic.addSale(sale4);
        this.analytic.addSale(sale5);

        }

    @Test
    public void test1() {
        try {
            this.analytic.addSale(null);
        } catch (NullPointerException ex) {
            assertNotEquals("", ex.getMessage());
            assertNotEquals("Analytic -> addSale (insertion of the sale", ex.getMessage());
            assertEquals("Analytic -> addSale (insertion of the sale. The sale must not be null", ex.getMessage());
            System.out.println(ex.getMessage());

        }
    }

    @Test
    public void test2() {
        assertEquals(31, this.analytic.getQuantitySoldOf(p1));
    }

}
