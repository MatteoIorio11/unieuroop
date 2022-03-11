package unieuroop.test.serialization;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import unieuroop.controller.serialization.Serialization;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;

public class TestSerialization {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() throws JsonGenerationException, JsonMappingException, IOException {
        //Product p = new ProductImpl(1, "prova","provaBrand",12.4, 15.0, Optional.empty(), "bla bla bla bla bla", Category.PC, null);
        final Staff s = new Staff("nome", "cognome", LocalDate.now(), 1, "ffff", "passw", LocalDate.now());
        Serialization.<Staff>serialize("test.json", s);
        final Staff pr = Serialization.<Staff>deserialize("test.json", Staff.class);
        System.out.print(pr);
    }

}
