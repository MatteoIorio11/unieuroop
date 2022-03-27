package unieuroop.controller.serialization;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thoughtworks.xstream.XStream;

public final class Serialization {
    private Serialization() { }

    public static <X> void serialize(final String filePath, final X obj) throws IOException {
        final XStream xstream = new XStream();
        final String xml = xstream.toXML(obj);
        final BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(xml);
        writer.close();
    }

    public static <X> X deserialize(final String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String xml = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        final XStream xstream = new XStream();
        return (X)xstream.fromXML(xml);
    }
    
//    public static <X> void serialize(final String filePath, final X obj) throws JsonGenerationException, JsonMappingException, IOException {
//        final ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        mapper.writeValue(new File(filePath), obj);
//    }
//
//    public static <X> X deserialize(final String filePath, final TypeReference<?> c) throws JsonParseException, JsonMappingException, IOException {
//        final ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        return mapper.readValue(new File(filePath), c);
//    }
//    public static <X> X deserializeByString(final String json, final TypeReference<?> c) throws JsonParseException, JsonMappingException, IOException {
//        final ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        return mapper.readValue(json, c);
//    }
}
