package unieuroop.test.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import javafx.util.Pair;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Client;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierImpl;

public class TestShop {

    private static final String APPLE_PRODUCT = "APPLE"; /*Brand of products*/
    private static final LocalDate TIME_NOW = LocalDate.now();
    private static final LocalTime TIME_START = LocalTime.now();
    private static final LocalTime TIME_FINISH = LocalTime.of(10, 10);
    private Department department1;
    private Department department2;
    private Department department3;
    private Shop shop01;
    private final Set<Department> departments = new HashSet<>();
    private Supplier s1;
    /**
     * ALL THE STAFF THAT WILL BE USED IN THIS TEST.
     */
    private final Staff staff1 = new Staff("Nome1", "Cognome1", TestShop.TIME_NOW,
            0, "email1@gmail.com", 111, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final Staff staff2 = new Staff("Nome2", "Cognome2", TestShop.TIME_NOW,
            0, "email2@gmail.csom", 222, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final Staff staff3 = new Staff("Nome3", "Cognome4", TestShop.TIME_NOW,
            0, "email3@gmail.com", 333, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final Staff staff4 = new Staff("Nome4", "Cognome4", TestShop.TIME_NOW,
            0, "email4@gmail.csom", 444, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    /**
     * ALL THE PRODUCTS THAT WILL BE USED IN THIS TEST.
     */
    private final Product p1 = new ProductImpl(1, "iphone 13 pro", TestShop.APPLE_PRODUCT,  1200.00,  900.00, Optional.empty(), "best phone ever created", Category.SMARTPHONE, s1);
    private final Product p2 = new ProductImpl(2, "applewatch", TestShop.APPLE_PRODUCT, 500.00,  200.00, Optional.empty(), "best watch ever created", Category.SMARTWATCH, s1);
    private final Product p3 = new ProductImpl(3, "mac book pro 14 ", TestShop.APPLE_PRODUCT,  3000.00, 2000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p4 = new ProductImpl(4, "mac book pro 16", TestShop.APPLE_PRODUCT,  6000.00,  3000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);

    @Before
    public void setUp() {
        this.s1 = new SupplierImpl("supp1", Map.of(p1, 900.00, p2, 200.00, p3, 2000.00, p4, 3000.00));
        this.shop01 = new ShopImpl("shop01");
        this.department1 = new DepartmentImpl("department1", Set.of(staff1, staff2, staff3, staff4), Map.of(p1, 5, p2, 1, p3, 2, p4, 2));
        this.department2 = new DepartmentImpl("department2", Set.of(staff1, staff2), Map.of(p1, 5, p4, 2));
        this.department3 = new DepartmentImpl("department3", Set.of(staff3, staff4), Map.of(p2, 1, p3, 2));
        this.shop01.addDepartment(department1);
        this.shop01.addDepartment(department2);
        this.shop01.addDepartment(department3);
        this.departments.add(department1);
        this.departments.add(department2);
        this.departments.add(department3);
    }
    /**
     * TESTING :  mergeDepartments(Set<Department> departments {@link Department}, String name) {@link Shop}.
     */
    @Test
    public void testMergeDepartments() {
        final var departmentTemp = this.shop01.mergeDepartments(departments, "finalDep");
        assertEquals("finalDep", departmentTemp.getDepartmentName());
        assertEquals(departmentTemp.getAllProducts(), Map.of(p1, 10, p2, 2, p3, 4, p4, 4));
        assertEquals(Set.of(staff1, staff2, staff3, staff4), departmentTemp.getStaff());
    }
    /**
     * Testing supplyDepartment(Department {@link Department}, Map<Product, Integer> products).  {@link Shop}
     */
    @Test
    public void testSupplyDepartment() {
        System.out.println(this.shop01.getName());
        this.shop01.supplyDepartment(department1, Map.of(p1,5, p2, 2, p3, 3, p4, 1));
        assertEquals(Map.of(p1, 10, p2, 3, p3, 5, p4, 3), this.department1.getAllProducts());
    }
    /**
     * TESTING : removeClient(Client {@link Client}) {@link Shop}.
     */
    @Test
    public void testRemoveClient1() {
        final Client client1 = new Client("Name1", "Surname1", LocalDate.now(), Optional.empty());
        final Client client2 = new Client("Name2", "Surname2", LocalDate.now(), Optional.empty());
        final Client client3 = new Client("Name3", "Surname3", LocalDate.now(), Optional.empty());
        this.shop01.registerClient(client1);
        this.shop01.registerClient(client2);

        try {
            this.shop01.removeClient(client3);
            fail("ERROR : exception must be throwned");
        } catch (NoSuchElementException e) {
            assertEquals("The input client does not exist", e.getMessage());
        }
    }

}
