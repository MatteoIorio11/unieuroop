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
import com.fasterxml.jackson.databind.module.SimpleModule;

import unieuroop.model.person.Staff;
import unieuroop.model.person.StaffDeserializer;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;

public final class DepartmentDeserializer extends JsonDeserializer<Department> {

    @Override
    public Department deserialize(final JsonParser p, final DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);
        final String name = node.get("departmentName").asText();
        final var prod = new ArrayList<Product>();
        final var qta = new ArrayList<Integer>();
        final var staff = new HashSet<Staff>();
        final ObjectMapper mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(Staff.class, new StaffDeserializer());
        mapper.registerModule(module);

        for (final var el : node.get("staff")) {
            staff.add(mapper.treeToValue(el, Staff.class));
        }
        for (final var el : node.get("products")) {
            prod.add(mapper.treeToValue(el, ProductImpl.class));
        }
        for (final var el : node.get("values")) {
            qta.add(el.asInt());
        }
        final var m = new HashMap<Product, Integer>();
        for (int i = 0; i < prod.size(); i++) {
            m.put(prod.get(i), qta.get(i));
        }
        return new DepartmentImpl(name, staff, m);
    }

}
