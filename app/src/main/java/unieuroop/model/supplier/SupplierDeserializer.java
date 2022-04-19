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
        final var keys = node.get("keys");
        final var values = node.get("values");
        final var products = new ArrayList<Product>();
        final var arrayValues = new ArrayList<Double>();
        final ObjectMapper mapper = new ObjectMapper();
        for (final var el : keys) {
            final Product product = mapper.treeToValue(el, ProductImpl.class);
            products.add(product);
        }
        for (final var el : values) {
            final var price = el.asDouble();
            arrayValues.add(price);
        }
        final var map = new HashMap<Product, Double>();
        for (int i = 0; i < products.size(); i++) {
            map.put(products.get(i), arrayValues.get(i));
        }
        return new SupplierImpl(name, map);
    }

}
