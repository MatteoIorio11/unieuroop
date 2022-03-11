package unieuroop;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import javafx.application.Application;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;

public class App {

    public static void main(final String[] args) throws JsonGenerationException, JsonMappingException, IOException {
        Product p = new ProductImpl(1, "prova","provaBrand",12.4, 15.0, Optional.empty(), "bla bla bla bla bla", Category.PC, null);
        Staff s = new Staff("nome", "cognome", LocalDate.now(), 1, "ffff", "passw", LocalDate.now());
        Serialization.<Product>serialize("test.json", p);
        final Product pr = Serialization.deserialize("test.json");
        System.out.print(pr);
        Application.launch(Login.class, args);
    }
}
