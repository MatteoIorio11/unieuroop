package unieuroop.test.serialization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import javafx.util.Pair;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Client;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.stock.Stock;
import unieuroop.model.stock.StockImpl;

public class TestSerialization {

    private final Staff s = new Staff("name", "surname", LocalDate.now(), 1, "ffff", 111, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
    private final Staff s1 = new Staff("name1", "surname1", LocalDate.now(), 1, "AAAA", 222, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
    private final Staff s2 = new Staff("name2", "surname2", LocalDate.now(), 3, "BBB", 222, Map.of(DayOfWeek.of(2), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
    private final Product p = new ProductImpl(1, "try", "Brand", 11.4, 84.0, "bla bla bla bla bla", Category.PC);
    private final Product p1 = new ProductImpl(2, "try1", "Brand1", 19.4, 28.0, "bla bla bla bla bla", Category.HOME);
    private final Product p2 = new ProductImpl(3, "try2", "Brand4", 22.0, 15.0, "bla bla bla bla bla", Category.TABLET);
    private final Product p3 = new ProductImpl(4, "try3", "Brand1", 18.5, 92.0, "bla bla bla bla bla", Category.PC);
    private final Product p4 = new ProductImpl(5, "try4", "Brand2", 102.4, 12.0, "bla bla bla bla bla", Category.PC);
    private final Product p5 = new ProductImpl(6, "try5", "Brand2", 234.3, 125.0, "bla bla bla bla bla", Category.TABLET);
    private final Product p6 = new ProductImpl(7, "try6", "Brand3", 123.9, 135.0, "bla bla bla bla bla", Category.PC);
    private final Product p7 = new ProductImpl(8, "try7", "Brand2", 1293.4, 1534.0, "bla bla bla bla bla", Category.PC);
 
    private final Department d1 = new DepartmentImpl("department1", Set.of(this.s, this.s1), Map.of(p, 10, p1, 10, p2, 2, p3, 32, p4, 32));
    private final Department d2 = new DepartmentImpl("department2", Set.of(this.s, this.s2), Map.of(p, 4, p1, 2, p5, 11, p6, 34));
    private final Department d3 = new DepartmentImpl("department3", Set.of(this.s1, this.s2), Map.of(p, 15, p7, 1, p2, 21, p5, 2, p4, 16));
   
    private final Client c1 = new Client("name1", "surname1", LocalDate.now(), 0);
    private final Client c2 = new Client("name2", "surname2", LocalDate.now(), 1);
    private final Client c3 = new Client("name3", "surname3", LocalDate.now(), 2);
    private final Client c4 = new Client("name4", "surname4", LocalDate.now(), 3);
    private final Client c5 = new Client("name5", "surname5", LocalDate.now(), 4);
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testStaff() throws IOException, ClassNotFoundException {
        final Set<Staff> staff = new HashSet<>();
        staff.add(s);
        staff.add(s1);
        Serialization.<Set<Staff>>serialize(Files.STAFFS.getPath(), staff);
        final Set<Staff> deserializedStaff = Serialization.<Set<Staff>>deserialize(Files.STAFFS.getPath(), new TypeReference<Set<Staff>>() { });
        assertEquals(staff, deserializedStaff);
    }
    @Test
    public void testStock() throws IOException, ClassNotFoundException {
        final Stock stock = new StockImpl();
        stock.addProducts(Map.of(p, 10, p1, 10, p2, 2, p3, 32, p4, 32));
        Serialization.<Stock>serialize(Files.STOCK.getPath(), stock);
        final Stock deserializedStock = Serialization.<Stock>deserialize(Files.STOCK.getPath(), new TypeReference<Stock>() { });
        assertTrue(deserializedStock.getTotalStock().entrySet().stream()
                .allMatch((entry) -> stock.getTotalStock().entrySet().stream()
                        .anyMatch((entry1) -> entry1.getKey().equals(entry.getKey()) && entry1.getValue() == entry.getValue())));
    }
    @Test
    public void testDepartments() throws IOException, ClassNotFoundException {
        final var departments = Set.of(d1, d2, d3);
        Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), departments);
        final Set<Department> deserializedDepartments = Serialization.<Set<Department>>deserialize(Files.DEPARTMENTS.getPath(), new TypeReference<Set<Department>>() { });
        assertEquals(departments, deserializedDepartments);
    }
    @Test
    public void testClients() throws IOException, ClassNotFoundException {
        final var clients = Set.of(c1, c2, c3, c4, c5);
        Serialization.<Set<Client>>serialize(Files.CLIENTS.getPath(), clients);
        final Set<Client> deserializedClients = Serialization.<Set<Client>>deserialize(Files.CLIENTS.getPath(), new TypeReference<Set<Client>>() { });
        assertEquals(clients, deserializedClients);
    }
    @Test
    public void testBills() throws IOException, ClassNotFoundException {
        final var bills = Map.of(LocalDate.now(), 10.3, LocalDate.ofYearDay(2019, 22), 
                183.9, LocalDate.ofYearDay(2022, 35), 104.2, LocalDate.ofYearDay(2012, 273), 37.7);
        Serialization.<Map<LocalDate, Double>>serialize(Files.BILLS.getPath(), bills);
        final var deserializedBills = Serialization.<Map<LocalDate, Double>>deserialize(Files.BILLS.getPath(), new TypeReference<Map<LocalDate, Double>>() { });
        assertEquals(deserializedBills, bills);
    }

}
