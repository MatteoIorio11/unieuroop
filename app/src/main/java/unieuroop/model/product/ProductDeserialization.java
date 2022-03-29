package unieuroop.model.product;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.supplier.Supplier;

public class ProductDeserialization extends KeyDeserializer{
    final ObjectMapper mapper = new ObjectMapper();
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(key, ProductImpl.class);
    }

}
