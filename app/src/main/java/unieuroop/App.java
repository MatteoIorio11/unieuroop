package unieuroop;

import java.util.Optional;

import javafx.application.Application;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;

public class App {

    public static void main(final String[] args) {
        Product p = new ProductImpl(1, "prova","provaBrand",12.4, 15.0, Optional.empty(), "bla bla bla bla bla", Category.PC, null);
        Serialization.<Product>serialize("test.json", p);
        Application.launch(Login.class, args);
    }
}
