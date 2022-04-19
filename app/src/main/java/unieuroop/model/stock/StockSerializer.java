package unieuroop.model.stock;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import unieuroop.model.product.Product;

public final class StockSerializer extends JsonSerializer<Stock> {

    @Override
    public void serialize(final Stock value, final JsonGenerator generator, final SerializerProvider serializers) 
            throws IOException {
        final var map = value.getTotalStock();
        final var keys = new ArrayList<Product>();
        final var values = new ArrayList<Integer>();
        map.entrySet().stream().forEach(e -> {
            keys.add(e.getKey());
            values.add(e.getValue());
        });
        generator.writeStartObject();
        generator.writeArrayFieldStart("keys");
        for (final var el : keys) {
          generator.writeObject(el);
        }
        generator.writeEndArray();

        generator.writeArrayFieldStart("values");
        for (final var el : values) {
          generator.writeObject(el);
        }
        generator.writeEndArray();
        generator.writeEndObject();
    }

}
