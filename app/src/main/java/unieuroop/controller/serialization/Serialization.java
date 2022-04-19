package unieuroop.controller.serialization;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public final class Serialization {
    private Serialization() { }

    public static <X> void serialize(final String filePath, final X object) throws  IOException {
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        mapper.writeValue(new File(filePath), object);
    }

    public static <X> X deserialize(final String filePath, final TypeReference<? extends X> typeReference) throws IOException {
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        return (X) mapper.readValue(new File(filePath), typeReference);
    }

}
