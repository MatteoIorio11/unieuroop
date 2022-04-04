package unieuroop.model.department;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import unieuroop.model.person.Staff;
import unieuroop.model.person.StaffDeserializer;
import unieuroop.model.person.StaffSerializer;
import unieuroop.model.product.Product;

public final class DepartmentSerializer extends JsonSerializer<Department> {

    @Override
    public void serialize(final Department value, final JsonGenerator gen, final SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        final var products = value.getAllProducts();
        final var k = new ArrayList<Product>();
        final var v = new ArrayList<Integer>();
        products.entrySet().stream().forEach(e -> {
            k.add(e.getKey());
            v.add(e.getValue());
        });
        gen.writeStartObject();
        gen.writeStringField("departmentName", value.getDepartmentName());
        final ObjectMapper mapper = (ObjectMapper) gen.getCodec();
        final SimpleModule module = new SimpleModule();
        module.addSerializer(Staff.class, new StaffSerializer());
        mapper.registerModule(module);
        gen.writeFieldName("staff");
        final String stringValue = mapper.writeValueAsString(value.getStaff());
        gen.writeRawValue(stringValue);

        gen.writeArrayFieldStart("products");
        for (final var el : k) {
          gen.writeObject(el);
        }
        gen.writeEndArray();
        gen.writeArrayFieldStart("values");
        for (final var el : v) {
          gen.writeObject(el);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
