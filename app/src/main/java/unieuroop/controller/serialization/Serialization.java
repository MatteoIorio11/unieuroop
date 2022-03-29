package unieuroop.controller.serialization;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;



public final class Serialization {
    private Serialization() { }

    public static <X> void serialize(final String filePath, final X obj) throws  IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(new File(filePath), obj);
    }

    public static <X> X deserialize(final String filePath, final TypeReference<?> c) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return (X) mapper.readValue(new File(filePath), c);
    }

}
