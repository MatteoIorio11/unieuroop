package unieuroop.model.stock;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import unieuroop.model.product.Product;

public class StockSerializer extends JsonSerializer<Stock> {

    @Override
    public void serialize(Stock value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        var m = value.getTotalStock();
        var k = new ArrayList<Product>();
        var v = new ArrayList<Integer>();
        m.entrySet().stream().forEach(e -> {
            k.add(e.getKey());
            v.add(e.getValue());
        });
        gen.writeStartObject();        
        gen.writeArrayFieldStart("keys");
        for (var el : k) {
          gen.writeObject(el);
        }
        gen.writeEndArray();

        gen.writeArrayFieldStart("values");
        for (var el : v) {
          gen.writeObject(el);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }

}
