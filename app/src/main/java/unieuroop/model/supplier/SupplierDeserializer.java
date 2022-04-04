package unieuroop.model.supplier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;

public class SupplierDeserializer extends JsonDeserializer<Supplier>{

    @Override
    public Supplier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("name").asText();
        var k = node.get("keys");
        var v = node.get("values");
        var prod = new ArrayList<Product>();
        var val = new ArrayList<Double>();
        ObjectMapper mapper = new ObjectMapper();
        for (var el : k) {
            Product product = mapper.treeToValue(el, ProductImpl.class);
            prod.add(product);
        }
        for (var el : v) {
            var price = el.asDouble();
            val.add(price);
        }
        var m = new HashMap<Product, Double>();
        for (int i = 0; i < prod.size(); i++) {
            m.put(prod.get(i), val.get(i));
        }
        Supplier supplier = new SupplierImpl(name, m);
        return supplier;
    }

}
