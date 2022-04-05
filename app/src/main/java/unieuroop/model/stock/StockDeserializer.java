package unieuroop.model.stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import unieuroop.controller.serialization.ObjectMapperFactory;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;

public class StockDeserializer extends JsonDeserializer<Stock>{

    @Override
    public Stock deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JacksonException {
        final JsonNode node = p.getCodec().readTree(p);
        final var k = node.get("keys");
        final var v = node.get("values");
        final var prod = new ArrayList<Product>();
        final var val = new ArrayList<Integer>();
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        for (final var el : k) {
            final Product product = mapper.treeToValue(el, ProductImpl.class);
            prod.add(product);
        }
        for (final var el : v) {
            final var price = el.asInt();
            val.add(price);
        }
        final var m = new HashMap<Product, Integer>();
        for (int i = 0; i < prod.size(); i++) {
            m.put(prod.get(i), val.get(i));
        }
        final var stock = new StockImpl();
        stock.addProducts(m);
        return stock;
    }

}
