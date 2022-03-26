package unieuroop.test.serialization;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import javafx.util.Pair;
import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.person.Staff;

public class TestSerialization {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testStaff() throws JsonGenerationException, JsonMappingException, IOException {
        //Product p = new ProductImpl(1, "prova","provaBrand",12.4, 15.0, Optional.empty(), "bla bla bla bla bla", Category.PC, null);
        final Staff s = new Staff("nome", "cognome", LocalDate.now(), 1, "ffff", 111, Map.of(DayOfWeek.of(0), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
        final Staff s1 = new Staff("nome1", "cognome1", LocalDate.now(), 1, "AAAA", 222, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(LocalTime.now(), LocalTime.now())));
        final Set<Staff> staff = new HashSet<>();
        staff.add(s);
        staff.add(s1);
        System.out.println(Files.STAFFS.getPath());
        Serialization.<Set<Staff>>serialize(Files.STAFFS.getPath(), staff);

        final Set<Staff> pr = Serialization.<Set<Staff>>deserialize(Files.STAFFS.getPath(), new TypeReference<Set<Staff>>() { });

        pr.forEach(a -> System.out.println(a));
    }

}
