package unieuroop.controller.serialization;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl; 

public final class Serialization {
    private Serialization() { }
    
    public static <X> void serialize(final String filePath, final X obj) throws JsonGenerationException, JsonMappingException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(filePath), obj);
    }
    
    public static <X> X deserialize(final String filePath, Class c) throws JsonParseException, JsonMappingException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return (X) mapper.readValue(new File(filePath), c);
    }
}
