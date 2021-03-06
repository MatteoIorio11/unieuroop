package unieuroop.test.serialization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import javafx.util.Pair;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Client;
import unieuroop.model.person.ClientImpl;
import unieuroop.model.person.Staff;
import unieuroop.model.person.StaffImpl;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.sale.Sale;
import unieuroop.model.sale.SaleImpl;
import unieuroop.model.stock.Stock;
import unieuroop.model.stock.StockImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierImpl;

public class TestSerialization {

    private static final String DESCRIPTION = "Description test";
    private static final int SLEEP = 800;

    private final Staff s = new StaffImpl("name", "surname", LocalDate.now(), "a", "ffff", 111, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
    private final Staff s1 = new StaffImpl("name1", "surname1", LocalDate.now(), "b", "AAAA", 222, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
    private final Staff s2 = new StaffImpl("name2", "surname2", LocalDate.now(), "c", "BBB", 222, Map.of(DayOfWeek.of(2), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
    private final Product p = new ProductImpl(1, "try", "Brand", 11.4, 84.0, TestSerialization.DESCRIPTION, Category.PC);
    private final Product p1 = new ProductImpl(2, "try1", "Brand1", 19.4, 28.0, TestSerialization.DESCRIPTION, Category.HOME);
    private final Product p2 = new ProductImpl(3, "try2", "Brand4", 22.0, 15.0, TestSerialization.DESCRIPTION, Category.TABLET);
    private final Product p3 = new ProductImpl(4, "try3", "Brand1", 18.5, 92.0, TestSerialization.DESCRIPTION, Category.PC);
    private final Product p4 = new ProductImpl(5, "try4", "Brand2", 102.4, 12.0, TestSerialization.DESCRIPTION, Category.PC);
    private final Product p5 = new ProductImpl(6, "try5", "Brand2", 234.3, 125.0, TestSerialization.DESCRIPTION, Category.TABLET);
    private final Product p6 = new ProductImpl(7, "try6", "Brand3", 123.9, 135.0, TestSerialization.DESCRIPTION, Category.PC);
    private final Product p7 = new ProductImpl(8, "try7", "Brand2", 1293.4, 1534.0, TestSerialization.DESCRIPTION, Category.PC);
 
    private final Department d1 = new DepartmentImpl("department1", Set.of(this.s, this.s1), Map.of(p, 10, p1, 10, p2, 2, p3, 32, p4, 32));
    private final Department d2 = new DepartmentImpl("department2", Set.of(this.s, this.s2), Map.of(p, 4, p1, 2, p5, 11, p6, 34));
    private final Department d3 = new DepartmentImpl("department3", Set.of(this.s1, this.s2), Map.of(p, 15, p7, 1, p2, 21, p5, 2, p4, 16));

    private final Client c1 = new ClientImpl("name1", "surname1", LocalDate.now(), "a");
    private final Client c2 = new ClientImpl("name2", "surname2", LocalDate.now(), "b");
    private final Client c3 = new ClientImpl("name3", "surname3", LocalDate.now(), "c");
    private final Client c4 = new ClientImpl("name4", "surname4", LocalDate.now(), "d");
    private final Client c5 = new ClientImpl("name5", "surname5", LocalDate.now(), "e");

    private final Sale sale1 = new SaleImpl(LocalDate.now(), Map.of(p, 10, p1, 13), Optional.of(c1));
    private final Sale sale2 = new SaleImpl(LocalDate.of(2010, 3, 22), Map.of(p2, 140, p3, 82), Optional.of(c2));
    private final Sale sale3 = new SaleImpl(LocalDate.of(2020, 10, 17), Map.of(p, 23, p5, 72, p4, 1), Optional.empty());
    private final Sale sale4 = new SaleImpl(LocalDate.of(2022, 9, 1), Map.of(p, 10, p7, 13, p6, 72), Optional.of(c3));
    private final Sale sale5 = new SaleImpl(LocalDate.of(2017, 12, 17), Map.of(p3, 23), Optional.of(c4));
    private final Sale sale6 = new SaleImpl(LocalDate.of(2012, 3, 12), Map.of(p4, 3, p5, 3, p6, 1), Optional.of(c5));

    private final Supplier supp1 = new SupplierImpl("name1", Map.of(p1, 100.0, p2, 120.0, p3, 620.0));
    private final Supplier supp2 = new SupplierImpl("name2", Map.of(p3, 52.0, p6, 732.0, p1, 152.0, p5, 261.0));
    private final Supplier supp3 = new SupplierImpl("name3", Map.of(p2, 100.0, p5, 823.0, p1, 220.5));

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testStaff() throws IOException, ClassNotFoundException, InterruptedException {
        final Set<Staff> staff = new HashSet<>();
        staff.add(s);
        staff.add(s1);
        Serialization.<Set<Staff>>serialize(Files.STAFFS.getPath(), staff);
        Thread.sleep(SLEEP);
        final Set<Staff> deserializedStaff = Serialization.<Set<Staff>>deserialize(Files.STAFFS.getPath(), new TypeReference<Set<Staff>>() { });
        assertEquals(staff, deserializedStaff);
    }
    @Test
    public void testStock() throws IOException, ClassNotFoundException, InterruptedException {
        final Stock stock = new StockImpl();
        stock.addProducts(Map.of(p, 10, p1, 10, p2, 2, p3, 32, p4, 32));
        Serialization.<Stock>serialize(Files.STOCK.getPath(), stock);
        Thread.sleep(SLEEP);

        final Stock deserializedStock = Serialization.<Stock>deserialize(Files.STOCK.getPath(), new TypeReference<Stock>() { });
        assertTrue(deserializedStock.getTotalStock().entrySet().stream()
                .allMatch((entry) -> stock.getTotalStock().entrySet().stream()
                        .anyMatch((entry1) -> entry1.getKey().equals(entry.getKey()) && entry1.getValue().equals(entry.getValue()))));
    }
    @Test
    public void testDepartments() throws IOException, ClassNotFoundException, InterruptedException {
        final var departments = Set.of(d1, d2, d3);
        Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), departments);
        Thread.sleep(SLEEP);
        final Set<Department> deserializedDepartments = Serialization.<Set<Department>>deserialize(Files.DEPARTMENTS.getPath(), new TypeReference<Set<Department>>() { });
        assertEquals(departments, deserializedDepartments);
    }
    @Test
    public void testClients() throws IOException, ClassNotFoundException, InterruptedException {
        final var clients = Set.of(c1, c2, c3, c4, c5);
        Serialization.<Set<Client>>serialize(Files.CLIENTS.getPath(), clients);
        Thread.sleep(SLEEP);
        final Set<Client> deserializedClients = Serialization.<Set<Client>>deserialize(Files.CLIENTS.getPath(), new TypeReference<Set<Client>>() { });
        assertEquals(clients, deserializedClients);
    }
    @Test
    public void testBills() throws IOException, ClassNotFoundException, InterruptedException {
        final var bills = Map.of(LocalDate.now(), 10.3, LocalDate.ofYearDay(2019, 22), 
                183.9, LocalDate.ofYearDay(2022, 35), 104.2, LocalDate.ofYearDay(2012, 273), 37.7);
        Serialization.<Map<LocalDate, Double>>serialize(Files.BILLS.getPath(), bills);
        Thread.sleep(SLEEP);
        final var deserializedBills = Serialization.<Map<LocalDate, Double>>deserialize(Files.BILLS.getPath(), new TypeReference<Map<LocalDate, Double>>() { });
        assertEquals(deserializedBills, bills);
    }
    @Test
    public void testSales() throws IOException, ClassNotFoundException, InterruptedException {
        final Set<Sale> sales = Set.of(sale1, sale2, sale3, sale4, sale5, sale6);
        Serialization.<Set<Sale>>serialize(Files.SALES.getPath(), sales);
        Thread.sleep(SLEEP);
        final Set<Sale> deserializedSales = Serialization.<Set<Sale>>deserialize(Files.SALES.getPath(), new TypeReference<Set<Sale>>() { });
        assertTrue(sales.stream().allMatch((sale) -> deserializedSales.stream().anyMatch((saleI) -> sale.getDate().isEqual(saleI.getDate()))));
        assertTrue(sales.stream().allMatch((sale) -> deserializedSales.stream().anyMatch((saleI) -> sale.getClient().equals(saleI.getClient()))));
        assertTrue(sales.stream()
                .allMatch(sale1 -> deserializedSales.stream().anyMatch((sale2) -> sale1.getProducts().stream()
                        .anyMatch((product) -> sale1.getProducts().contains(product)))));
    }
    @Test
    public void testShopName() throws IOException, ClassNotFoundException, InterruptedException {
        final String shopName = "unieurOOP";
        Serialization.<String>serialize(Files.SHOPNAME.getPath(), shopName);
        Thread.sleep(SLEEP);
        final String deserializedShopName = Serialization.<String>deserialize(Files.SHOPNAME.getPath(), new TypeReference<String>() { });
        System.out.println(shopName + " " + deserializedShopName);
        assertEquals(shopName, deserializedShopName);
    }
    @Test
    public void testSuppliers() throws IOException, ClassNotFoundException, InterruptedException {
        final Set<Supplier> suppliers = Set.of(supp1, supp2, supp3);
        Serialization.<Set<Supplier>>serialize(Files.SUPPLIERS.getPath(), suppliers);
        Thread.sleep(SLEEP);
        final Set<Supplier> deserializedSuppliers = Serialization.<Set<Supplier>>deserialize(Files.SUPPLIERS.getPath(), new TypeReference<Set<Supplier>>() { });
        assertTrue(suppliers.stream().allMatch((suppl) -> deserializedSuppliers.stream().anyMatch((supplD) -> suppl.getName().equals(supplD.getName()))));
        assertTrue(suppliers.stream()
                .allMatch(suppl1 -> deserializedSuppliers.stream().anyMatch((suppl2) -> suppl1.getCatalog().entrySet().stream()
                        .anyMatch((product) -> suppl1.getCatalog().entrySet().contains(product)))));
    }
}
