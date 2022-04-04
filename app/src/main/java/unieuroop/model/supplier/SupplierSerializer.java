package unieuroop.model.supplier;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import unieuroop.model.product.Product;

public class SupplierSerializer extends JsonSerializer<Supplier>{

    @Override
    public void serialize(Supplier value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        var m = value.getCatalog();
        var k = new ArrayList<Product>();
        var v = new ArrayList<Double>();
        m.entrySet().stream().forEach(e -> {
            k.add(e.getKey());
            v.add(e.getValue());
        });
        gen.writeStartObject();
        gen.writeStringField("name", value.getName());
        
        
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
