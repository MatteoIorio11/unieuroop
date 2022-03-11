package unieuroop;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import javafx.application.Application;

public class App {

    public static void main(final String[] args) throws JsonGenerationException, JsonMappingException, IOException {
        Application.launch(Login.class, args);
    }
}
