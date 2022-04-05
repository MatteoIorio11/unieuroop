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

import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;

public final class SupplierDeserializer extends JsonDeserializer<Supplier> {

    @Override
    public Supplier deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);
        final String name = node.get("name").asText();
        final var k = node.get("keys");
        final var v = node.get("values");
        final var prod = new ArrayList<Product>();
        final var val = new ArrayList<Double>();
        final ObjectMapper mapper = new ObjectMapper();
        for (final var el : k) {
            final Product product = mapper.treeToValue(el, ProductImpl.class);
            prod.add(product);
        }
        for (final var el : v) {
            final var price = el.asDouble();
            val.add(price);
        }
        final var m = new HashMap<Product, Double>();
        for (int i = 0; i < prod.size(); i++) {
            m.put(prod.get(i), val.get(i));
        }
        return new SupplierImpl(name, m);
    }

}
