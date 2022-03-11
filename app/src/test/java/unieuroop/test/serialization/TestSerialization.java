package unieuroop.test.serialization;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.person.Staff;

public class TestSerialization {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() throws JsonGenerationException, JsonMappingException, IOException {
        //Product p = new ProductImpl(1, "prova","provaBrand",12.4, 15.0, Optional.empty(), "bla bla bla bla bla", Category.PC, null);
        final Staff s = new Staff("nome", "cognome", LocalDate.now(), 1, "ffff", "passw", LocalDate.now());
        final Staff s1 = new Staff("nome1", "cognome1", LocalDate.now(), 1, "AAAA", "askjda", LocalDate.now());
        final Set<Staff> staff = new HashSet<>();
        staff.add(s);
        staff.add(s1);

        Serialization.<Set<Staff>>serialize("test.json", staff);

        final Set<Staff> pr = Serialization.<Set<Staff>>deserialize("test.json", new TypeReference<Set<Staff>>() { });

        pr.forEach(a -> System.out.println(a));
    }

}
