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
import unieuroop.model.person.ClientImpl;
import unieuroop.model.person.StaffImpl;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.sale.Sale;
import unieuroop.model.sale.SaleImpl;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierImpl;

public class TestShop {

    private static final String ERROR_MESSAGE = "ERROR : exception must be throwned";
    private static final String APPLE_PRODUCT = "APPLE"; /*Brand of products*/
    private static final LocalDate DATE_NOW = LocalDate.now();
    private static final LocalTime TIME_START = LocalTime.now();
    private static final LocalTime TIME_FINISH = LocalTime.of(10, 10);
    private Department department1;
    private Department department2;
    private Department department3;
    private Shop shop01;
    private final Set<Department> departments = new HashSet<>();
    /**
     * ALL THE STAFF THAT WILL BE USED IN THIS TEST.
     */
    private final StaffImpl staff1 = new StaffImpl("Nome1", "Cognome1", TestShop.DATE_NOW,
            0, "email1@gmail.com", 111, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final StaffImpl staff2 = new StaffImpl("Nome2", "Cognome2", TestShop.DATE_NOW,
            0, "email2@gmail.csom", 222, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final StaffImpl staff3 = new StaffImpl("Nome3", "Cognome4", TestShop.DATE_NOW,
            0, "email3@gmail.com", 333, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final StaffImpl staff4 = new StaffImpl("Nome4", "Cognome4", TestShop.DATE_NOW,
            0, "email4@gmail.csom", 444, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    /**
     * ALL THE PRODUCTS THAT WILL BE USED IN THIS TEST.
     */
    private final Product p1 = new ProductImpl(1, "iphone 13 pro", TestShop.APPLE_PRODUCT,  1200.00,  900.00, "best phone ever created", Category.SMARTPHONE);
    private final Product p2 = new ProductImpl(2, "applewatch", TestShop.APPLE_PRODUCT, 500.00,  200.00, "best watch ever created", Category.SMARTWATCH);
    private final Product p3 = new ProductImpl(3, "mac book pro 14 ", TestShop.APPLE_PRODUCT,  3000.00, 2000.00, "best mac book ever created", Category.PC);
    private final Product p4 = new ProductImpl(4, "mac book pro 16", TestShop.APPLE_PRODUCT,  6000.00,  3000.00, "best mac book ever created", Category.PC);

    @Before
    public void setUp() {
        this.shop01 = new ShopImpl("shop01");
        this.department1 = new DepartmentImpl("department1", Set.of(staff1, staff2, staff3, staff4), Map.of(p1, 2, p2, 1, p3, 2, p4, 2));
        this.department2 = new DepartmentImpl("department2", Set.of(staff1, staff2), Map.of(p1, 2, p4, 2));
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
        assertEquals(departmentTemp.getAllProducts(), Map.of(p1, 4, p2, 2, p3, 4, p4, 4));
        assertEquals(Set.of(staff1, staff2, staff3, staff4), departmentTemp.getStaff());
    }
    /**
     * Testing supplyDepartment(Department {@link Department}, Map<Product, Integer> products).  {@link Shop}
     */
    @Test
    public void testSupplyDepartment() {
        this.shop01.getStock().addProducts(Map.of(p1, 10, p2, 10, p3, 10, p4, 10));
        this.shop01.supplyDepartment(department1, Map.of(p1, 2, p2, 2));
        this.shop01.supplyDepartment(department3, Map.of(p4, 1));
        assertEquals(Map.of(p1, 4, p2, 3, p3, 2, p4, 2), this.department1.getAllProducts());
        assertEquals(Map.of(p2, 1, p3, 2, p4, 1), this.department3.getAllProducts());
    }
    /**
     * TESTING : removeClient(Client {@link ClientImpl}) {@link Shop}.
     */
    @Test
    public void testRemoveClient1() {
        final ClientImpl client1 = new ClientImpl("Name1", "Surname1", TestShop.DATE_NOW, 1);
        final ClientImpl client2 = new ClientImpl("Name2", "Surname2", TestShop.DATE_NOW, 2);
        final Client client3 = new ClientImpl("Name3", "Surname3", TestShop.DATE_NOW, 3);
        this.shop01.registerClient(client1);
        this.shop01.registerClient(client2);

        try {
            this.shop01.removeClient(client3);
            fail(TestShop.ERROR_MESSAGE);
        } catch (NoSuchElementException e) {
            assertEquals("The input client does not exist", e.getMessage());
        }
    }
    /**
     * TESTING : removeClient(Client {@link ClientImpl}) {@link Shop}.
     */
    @Test
    public void testRemoveClient2() {
        final ClientImpl client1 = new ClientImpl("Name1", "Surname1", TestShop.DATE_NOW, 1);
        final ClientImpl client2 = new ClientImpl("Name2", "Surname2", TestShop.DATE_NOW, 2);
        this.shop01.registerClient(client1);
        this.shop01.registerClient(client2);

        try {
            this.shop01.removeClient(client1);
            this.shop01.removeClient(client2);
        } catch (NoSuchElementException e) {
            fail("ERROR : input clients exist in the Shop");
        }
    }
    /**
     * TESTING : removeSale(Sale {@link Sale}) {@link Shop}.
     */
    @Test
    public void testRemoveSale1() {
        final Sale sale1 = new SaleImpl(LocalDate.now(), Map.of(p1, 10, p2, 100), Optional.empty());
        final Sale sale2 = new SaleImpl(LocalDate.now(), Map.of(p1, 1), Optional.empty());
        final Sale sale3 = new SaleImpl(LocalDate.now(), Map.of(p2, 1), Optional.empty());

        this.shop01.addSale(sale1);
        this.shop01.addSale(sale2);

        try {
            this.shop01.removeSale(sale3);
            fail("ERROR : exception must be catched");
        } catch (NoSuchElementException e) {
            assertEquals("The input sale does not exist", e.getMessage());
        }
    }
    /**
     * TESTING : removeSale(Sale {@link Sale}) {@link Shop}.
     */
    @Test
    public void testRemoveSale2() {
        final Sale sale1 = new SaleImpl(LocalDate.now(), Map.of(p1, 10, p2, 100), Optional.empty());
        final Sale sale2 = new SaleImpl(LocalDate.now(), Map.of(p1, 1), Optional.empty());

        this.shop01.addSale(sale1);
        this.shop01.addSale(sale2);

        try {
            this.shop01.removeSale(sale1);
        } catch (NoSuchElementException e) {
            fail("ERROR : exception must not be catched");
        }
    }
    /**
     * TESTING : removeSupplier(Supplier {@link Supplier} ) {@link Shop}.
     */
    @Test
    public void testRemoveSupplier1() {
        final Supplier supp1 = new SupplierImpl("Supplier1", Map.of(p1, 10.0, p2, 40.0, p3, 1.0));
        final Supplier supp2 = new SupplierImpl("Supplier2", Map.of(p1, 5.0, p2, 10.0));
        final Supplier supp3 = new SupplierImpl("Supplier2", Map.of(p1, 1.0));

        this.shop01.addSupplier(supp1);
        this.shop01.addSupplier(supp2);

        try {
            this.shop01.removeSupplier(supp3);
            fail(TestShop.ERROR_MESSAGE);
        } catch (NoSuchElementException e) {
            assertEquals("The input supplier does not exist", e.getMessage());
        }
    }
    /**
     * TESTING : removeSupplier(Supplier {@link Supplier} ) {@link Shop}.
     */
    @Test
    public void testRemoveSupplier2() {
        final Supplier supp1 = new SupplierImpl("Supplier1", Map.of(p1, 10.0, p2, 40.0, p3, 1.0));
        final Supplier supp2 = new SupplierImpl("Supplier2", Map.of(p1, 5.0, p2, 10.0));

        this.shop01.addSupplier(supp1);
        this.shop01.addSupplier(supp2);

        try {
            this.shop01.removeSupplier(supp1);
        } catch (NoSuchElementException e) {
            fail("ERROR : exception must not be throwned");
        }
    }
    /**
     * TESTING : removeStaff(Staff {@link StaffImpl} ) {@link Shop}.
     */
    @Test
    public void testRemoveStaff1() {
        this.shop01.addStaff(staff1);
        this.shop01.addStaff(staff2);
        this.shop01.addStaff(staff3);

        try {
            this.shop01.removeStaff(staff4);
            fail(TestShop.ERROR_MESSAGE);
        } catch (NoSuchElementException e) {
            assertEquals("The input staff does not exist", e.getMessage());
        }
    }
    /**
     * TESTING : removeStaff(Staff {@link StaffImpl} ) {@link Shop}.
     */
    @Test
    public void testRemoveStaff2() {
        this.shop01.addStaff(staff1);
        this.shop01.addStaff(staff2);
        this.shop01.addStaff(staff3);

        try {
            this.shop01.removeStaff(staff3);
        } catch (NoSuchElementException e) {
            fail(TestShop.ERROR_MESSAGE);
        }
    }
    /**
     * TESING : removeDepartment (Department {@link Department} ) {@link Shop}.
     */
    @Test
    public void testRemoveDepartment1() {
        try {
            this.shop01.removeDepartment(department2);
        } catch (NoSuchElementException e) {
            fail("ERROR : The input department exist");
        }
    }
    /**
     * TESING : removeDepartment (Department {@link Department} ) {@link Shop}.
     */
    @Test
    public void testRemoveDepartment2() {
        final Department departmentTemp = new DepartmentImpl("departmentTemp", Set.of(staff1, staff2), Map.of(p1, 5));

        try {
            this.shop01.removeDepartment(departmentTemp);
            fail(TestShop.ERROR_MESSAGE);
        } catch (NoSuchElementException e) {
            assertEquals("The input department does not exist", e.getMessage());
        }
    }

}
