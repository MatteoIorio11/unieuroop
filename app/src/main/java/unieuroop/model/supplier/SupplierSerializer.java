package unieuroop.model.supplier;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import unieuroop.model.product.Product;

public final class SupplierSerializer extends JsonSerializer<Supplier> {

    @Override
    public void serialize(final Supplier value, final JsonGenerator gen, final SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        final var map = value.getCatalog();
        final var keys = new ArrayList<Product>();
        final var values = new ArrayList<Double>();
        map.entrySet().stream().forEach(e -> {
            keys.add(e.getKey());
            values.add(e.getValue());
        });
        gen.writeStartObject();
        gen.writeStringField("name", value.getName());
        gen.writeArrayFieldStart("keys");
        for (final var el : keys) {
          gen.writeObject(el);
        }
        gen.writeEndArray();

        gen.writeArrayFieldStart("values");
        for (final var el : values) {
          gen.writeObject(el);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }

}
