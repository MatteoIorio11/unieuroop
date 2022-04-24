package unieuroop.test.department;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import javafx.util.Pair;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Staff;
import unieuroop.model.person.StaffImpl;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;

public class TestDepartment {

    private static final LocalDate TIME_NOW = LocalDate.now();
    private static final LocalTime TIME_START = LocalTime.now();
    private static final LocalTime TIME_FINISH = LocalTime.of(10, 10);
    private static final String APPLE_PRODUCT = "APPLE"; /*Brand of products*/
    private static final int QUANTITY_P1 = 10;
    private static final int FINAL_QUANTITY_P1 = 11;
    private static final int FINAL_QUANTITY_P2 = 200;

    private Department department;
    private final Product p1 = new ProductImpl(1, "iphone 13 pro", TestDepartment.APPLE_PRODUCT,  1200.00,  900.00, "best phone ever created", Category.SMARTPHONE);
    private final Product p2 = new ProductImpl(2, "applewatch", TestDepartment.APPLE_PRODUCT, 500.00,  200.00, "best watch ever created", Category.SMARTWATCH);
    private final Product p3 = new ProductImpl(3, "mac book pro 14 ", TestDepartment.APPLE_PRODUCT,  3000.00, 2000.00, "best mac book ever created", Category.PC);
    private final Product p4 = new ProductImpl(4, "mac book pro 16", TestDepartment.APPLE_PRODUCT,  6000.00,  3000.00, "best mac book ever created", Category.PC);
    private final Staff staff1 = new StaffImpl("Nome1", "Cognome1", TestDepartment.TIME_NOW,
            0, "email1@gmail.com", 12, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final Staff staff2 = new StaffImpl("Nome2", "Cognome2", TestDepartment.TIME_NOW,
            0, "email2@gmail.csom", 123, Map.of(DayOfWeek.of(2), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final Staff staff3 = new StaffImpl("NomeProva", "CognomeProva", TestDepartment.TIME_NOW,
            0, "email3@gmail.com", 12, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    @Before
    public void setUp() throws Exception {
        this.department = new DepartmentImpl("department1", Set.of(staff1, staff2), Map.of(p1, 10, p2, 100, p3, 3));
    }

    /**
     * TESTING : department.addStaff().
     */
    @Test
    public void testAddStaff1() {
        try {
            this.department.addStaff(staff1);
            fail("ERROR Excpetion must be detected.");
        } catch (UnsupportedOperationException e) {
            fail("ERROR wrong exception throwned.");
        } catch (IllegalArgumentException e1) {
            assertTrue(e1.getMessage().contains("staff"));
        }
    }
    /**
     * TESTING : department.addStaff().
     */
    @Test
    public void testAddStaff2() {
        try {
            this.department.addStaff(staff3);
        } catch (IllegalArgumentException e) {
            fail("ERROR this staff is not contained in the department so the Exception must not be throwned.");
        }
    }
    /**
     *  TESTING : department.removeStaff().
     */
    @Test
    public void testRemoveStaff1() {
        try {
            this.department.removeStaff(Set.of(staff1));
        } catch (IllegalArgumentException e) {
            fail("ERROR : this staff is present and the exception must not be throwned.");
        }
    }
    /**
     *  TESTING : department.removeStaff().
     */
    @Test
    public void testRemoveStaff2() {
        try {
            this.department.removeStaff(Set.of(staff3));
            fail("ERROR : the exception must be thowned because " + staff3.toString() + "does not exist in Department");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("staff"));

        }
    }
    /**
     * TESTING : department.productsByQuantity(...).
     */
    @Test
    public void testProductByQuantity() {
        final Map<Product, Integer> products = this.department.productsByQuantity((quantity) -> quantity <= 10);

        assertFalse(products.isEmpty());
        assertTrue(products.containsKey(p1));

        final int quantityP1 = products.get(p1);

        assertTrue(products.containsKey(p3));
        assertFalse(products.containsKey(p2));
        assertFalse(products.containsKey(p4));
        assertEquals(TestDepartment.QUANTITY_P1, quantityP1);
    }
    /**
     * TESTING : department.takeProductFromDepartment(...).
     */
    @Test
    public void testTakeProductFromDepartment1() {
        try {
            this.department.takeProductFromDepartment(Map.of(p1, 1, p3, 3));
        } catch (IllegalArgumentException e) {
            fail("ERROR : this operation can be done withouth exception");
        }
    }
    /**
     * TESTING : department.takeProductFromDepartment(...).
     */
    @Test
    public void testTakeProductFromDepartment2() {
        try {
            this.department.takeProductFromDepartment(Map.of(p3, 100));
            fail("ERROR : this operation can not be done, p30's quantity is less than 100");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Take products can not be done beacuse some products's quantity is less than the quantity in input"));
        }
    }
    /**
     * TESTING : department.addProducts(...).
     */
    @Test
    public void testAddProducts() {
        final Map<Product, Integer> products = Map.of(p1, 1, p2, 100, p3, 10);
        this.department.addProducts(products);
        final int quantityP1 = this.department.getAllProducts().get(p1);
        final int quantityP2 = this.department.getAllProducts().get(p2);

        assertEquals(TestDepartment.FINAL_QUANTITY_P1, quantityP1);
        assertEquals(TestDepartment.FINAL_QUANTITY_P2, quantityP2);
    }
}
