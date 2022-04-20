package unieuroop.model.department;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import unieuroop.controller.serialization.ObjectMapperFactory;
import unieuroop.model.person.Staff;
import unieuroop.model.person.StaffImpl;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;

public final class DepartmentDeserializer extends JsonDeserializer<Department> {

    @Override
    public Department deserialize(final JsonParser p, final DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);
        final String name = node.get("departmentName").asText();
        final var products = new ArrayList<Product>();
        final var quantity = new ArrayList<Integer>();
        final var staff = new HashSet<Staff>();
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();

        for (final var el : node.get("staff")) {
            staff.add(mapper.treeToValue(el, StaffImpl.class));
        }
        for (final var el : node.get("products")) {
            products.add(mapper.treeToValue(el, ProductImpl.class));
        }
        for (final var el : node.get("values")) {
            quantity.add(el.asInt());
        }
        final var m = new HashMap<Product, Integer>();
        for (int i = 0; i < products.size(); i++) {
            m.put(products.get(i), quantity.get(i));
        }
        return new DepartmentImpl(name, staff, m);
    }

}
