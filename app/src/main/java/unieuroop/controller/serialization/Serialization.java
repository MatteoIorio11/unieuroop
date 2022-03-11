package unieuroop.controller.serialization;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl; 

public final class Serialization {
    private Serialization() { }
    
    public static <X> void serialize(final String filePath, final X obj) throws JsonGenerationException, JsonMappingException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), obj);
    }
    
    public static Product deserialize(final String filePath) throws JsonParseException, JsonMappingException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), ProductImpl.class);
    }
}
