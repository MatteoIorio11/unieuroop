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

public final class StockDeserializer extends JsonDeserializer<Stock> {

    @Override
    public Stock deserialize(final JsonParser parser, final DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode node = parser.getCodec().readTree(parser);
        final var keys = node.get("keys");
        final var values = node.get("values");
        final var productsArray = new ArrayList<Product>();
        final var valuesArray = new ArrayList<Integer>();
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        for (final var el : keys) {
            final Product product = mapper.treeToValue(el, ProductImpl.class);
            productsArray.add(product);
        }
        for (final var el : values) {
            final var price = el.asInt();
            valuesArray.add(price);
        }
        final var m = new HashMap<Product, Integer>();
        for (int i = 0; i < productsArray.size(); i++) {
            m.put(productsArray.get(i), valuesArray.get(i));
        }
        final var stock = new StockImpl();
        stock.addProducts(m);
        return stock;
    }

}
