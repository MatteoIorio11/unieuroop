package unieuroop.model.sale;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import unieuroop.controller.serialization.ObjectMapperFactory;
import unieuroop.model.product.Product;

public class SaleSerializer extends JsonSerializer<Sale> {

    @Override
    public void serialize(final Sale value, final JsonGenerator gen, final SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        gen.writeStartObject();
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        gen.writeFieldName("date");
        gen.writeRawValue(mapper.writeValueAsString(value.getDate()));
        gen.writeArrayFieldStart("products");
        for (final var el : value.getProducts()) {
            gen.writeObject(el);
        }
        gen.writeEndArray();
        gen.writeArrayFieldStart("quantity");
        for (final var el : value.getProducts()) {
            gen.writeObject(value.getQuantityOf(el));
        }
        gen.writeEndArray();
        gen.writeObjectField("client", value.getClient());
        gen.writeEndObject();
    }
}
