package unieuroop.model.stock;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import unieuroop.model.product.Product;

public final class StockSerializer extends JsonSerializer<Stock> {

    @Override
    public void serialize(final Stock value, final JsonGenerator gen, final SerializerProvider serializers) 
            throws IOException {
        final var m = value.getTotalStock();
        final var k = new ArrayList<Product>();
        final var v = new ArrayList<Integer>();
        m.entrySet().stream().forEach(e -> {
            k.add(e.getKey());
            v.add(e.getValue());
        });
        gen.writeStartObject();
        gen.writeArrayFieldStart("keys");
        for (final var el : k) {
          gen.writeObject(el);
        }
        gen.writeEndArray();

        gen.writeArrayFieldStart("values");
        for (final var el : v) {
          gen.writeObject(el);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }

}
