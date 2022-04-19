package unieuroop.model.sale;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import unieuroop.controller.serialization.ObjectMapperFactory;

public final class SaleSerializer extends JsonSerializer<Sale> {

    @Override
    public void serialize(final Sale value, final JsonGenerator generator, final SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        generator.writeStartObject();
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        generator.writeFieldName("date");
        generator.writeRawValue(mapper.writeValueAsString(value.getDate()));
        generator.writeArrayFieldStart("products");
        for (final var el : value.getProducts()) {
            generator.writeObject(el);
        }
        generator.writeEndArray();
        generator.writeArrayFieldStart("quantity");
        for (final var el : value.getProducts()) {
            generator.writeObject(value.getQuantityOf(el));
        }
        generator.writeEndArray();
        generator.writeObjectField("client", value.getClient());
        generator.writeEndObject();
    }
}
