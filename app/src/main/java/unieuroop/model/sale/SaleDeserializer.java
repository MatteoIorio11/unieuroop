package unieuroop.model.sale;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

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
        final var prod = new ArrayList<Product>();
        final var qta = new ArrayList<Integer>();
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        final LocalDate date = mapper.treeToValue(node.get("date"), LocalDate.class);
        for (final var el : node.get("products")) {
            prod.add(mapper.treeToValue(el, ProductImpl.class));
        }
        for (final var el : node.get("quantity")) {
            qta.add(el.asInt());
        }
        final var m = new HashMap<Product, Integer>();
        for (int i = 0; i < prod.size(); i++) {
            m.put(prod.get(i), qta.get(i));
        }
        final Optional<Client> client = mapper.treeToValue(node.get("client"), Optional.class);
        return new SaleImpl(date, m, client);
    }

}
