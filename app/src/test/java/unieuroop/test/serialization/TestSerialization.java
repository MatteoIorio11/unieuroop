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
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.stock.Stock;
import unieuroop.model.stock.StockImpl;

public class TestSerialization {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testStaff() throws IOException, ClassNotFoundException {
        Product p = new ProductImpl(1, "prova", "provaBrand", 12.4, 15.0, "bla bla bla bla bla", Category.PC);
        final Staff s = new Staff("nome", "cognome", LocalDate.now(), 1, "ffff", 111, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
        final Staff s1 = new Staff("nome1", "cognome1", LocalDate.now(), 1, "AAAA", 222, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
        final Set<Staff> staff = new HashSet<>();
        staff.add(s);
        staff.add(s1);
        Serialization.<Set<Staff>>serialize(Files.STAFFS.getPath(), staff);
        final Set<Staff> deserializedStaff = Serialization.<Set<Staff>>deserialize(Files.STAFFS.getPath(), new TypeReference<Set<Staff>>() { });
        assertEquals(staff, deserializedStaff);
    }
    @Test
    public void testStock() throws IOException, ClassNotFoundException {
        final Product p = new ProductImpl(1, "prova", "provaBrand", 12.4, 15.0, "bla bla bla bla bla", Category.PC);
        final Product p1 = new ProductImpl(2, "prova1", "provaBrand1", 12.4, 15.0, "bla bla bla bla bla", Category.HOME);
        final Product p2 = new ProductImpl(3, "prova2", "provaBrand2", 12.4, 15.0, "bla bla bla bla bla", Category.TABLET);
        final Product p3 = new ProductImpl(4, "prova3", "provaBrand1", 12.4, 15.0, "bla bla bla bla bla", Category.PC);
        final Product p4 = new ProductImpl(5, "prova4", "provaBrand2", 12.4, 15.0, "bla bla bla bla bla", Category.PC);
        final Stock stock = new StockImpl();
        stock.addProducts(Map.of(p, 10, p1, 10, p2, 2, p3, 32, p4, 32));
        Serialization.<Stock>serialize(Files.STOCK.getPath(), stock);
        final Stock deserializedStock = Serialization.<Stock>deserialize(Files.STOCK.getPath(), new TypeReference<Stock>() { });
        assertEquals(stock, deserializedStock);
    }

}
