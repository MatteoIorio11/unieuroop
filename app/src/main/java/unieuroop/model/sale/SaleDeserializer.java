package unieuroop.model.sale;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.IntStream;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import unieuroop.controller.serialization.ObjectMapperFactory;
import unieuroop.model.person.Client;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;

public final class SaleDeserializer extends JsonDeserializer<Sale> {

    @Override
    public Sale deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);
        final var product = new ArrayList<Product>();
        final var quantity = new ArrayList<Integer>();
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        final LocalDate date = mapper.treeToValue(node.get("date"), LocalDate.class);
        for (final var element : node.get("products")) {
            product.add(mapper.treeToValue(element, ProductImpl.class));
        }
        for (final var element : node.get("quantity")) {
            quantity.add(element.asInt());
        }
        final var map = new HashMap<Product, Integer>();
        IntStream.iterate(0, (i) -> i + 1).limit(product.size()).peek((i) -> map.put(product.get(i), quantity.get(i)));
//        for (int i = 0; i < product.size(); i++) {
//            map.put(product.get(i), quantity.get(i));
//        }
        final var client = Optional.ofNullable(mapper.treeToValue(node.get("client"), Client.class));
        return new SaleImpl(date, map, client);
    }

}
