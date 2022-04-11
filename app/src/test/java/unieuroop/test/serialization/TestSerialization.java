package unieuroop.test.serialization;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import javafx.util.Pair;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.stock.Stock;
import unieuroop.model.stock.StockImpl;

public class TestSerialization {

    private final Staff s = new Staff("nome", "cognome", LocalDate.now(), 1, "ffff", 111, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
    private final Staff s1 = new Staff("nome1", "cognome1", LocalDate.now(), 1, "AAAA", 222, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
    private final Staff s2 = new Staff("nome2", "cognome2", LocalDate.now(), 3, "BBB", 222, Map.of(DayOfWeek.of(2), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
    private final Product p = new ProductImpl(1, "prova", "provaBrand", 11.4, 84.0, "bla bla bla bla bla", Category.PC);
    private final Product p1 = new ProductImpl(2, "prova1", "provaBrand1", 19.4, 28.0, "bla bla bla bla bla", Category.HOME);
    private final Product p2 = new ProductImpl(3, "prova2", "provaBrand2", 22.0, 15.0, "bla bla bla bla bla", Category.TABLET);
    private final Product p3 = new ProductImpl(4, "prova3", "provaBrand1", 18.5, 92.0, "bla bla bla bla bla", Category.PC);
    private final Product p4 = new ProductImpl(5, "prova4", "provaBrand2", 102.4, 12.0, "bla bla bla bla bla", Category.PC);
    private final Product p5 = new ProductImpl(6, "prova5", "provaBrand2", 234.3, 125.0, "bla bla bla bla bla", Category.TABLET);
    private final Product p6 = new ProductImpl(7, "prova6", "provaBrand3", 123.9, 135.0, "bla bla bla bla bla", Category.PC);
    private final Product p7 = new ProductImpl(8, "prova7", "provaBrand2", 1293.4, 1534.0, "bla bla bla bla bla", Category.PC);
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
        assertEquals(stock, deserializedStock);
    }
    @Test
    public void testDepartments() throws IOException, ClassNotFoundException {
        final Department d1 = new DepartmentImpl("department1", Set.of(this.s, this.s1), Map.of(p, 10, p1, 10, p2, 2, p3, 32, p4, 32) );
        final Department d2 = new DepartmentImpl("department2", Set.of(this.s, this.s2), Map.of(p, 4, p1, 2, p5, 11, p6, 34) );
        final Department d3 = new DepartmentImpl("department3", Set.of(this.s1, this.s2), Map.of(p, 15, p7, 1, p2, 21, p5, 2, p4, 16) );
        final var departments = Set.of(d1, d2, d3);
        Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), departments);
        final Set<Department> deserializedStock = Serialization.<Set<Department>>deserialize(Files.DEPARTMENTS.getPath(), new TypeReference<Set<Department>>() { });
        assertEquals(departments, deserializedStock);
    }

}
