package unieuroop.model.department;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import unieuroop.controller.serialization.ObjectMapperFactory;
import unieuroop.model.product.Product;

public final class DepartmentSerializer extends JsonSerializer<Department> {

    @Override
    public void serialize(final Department value, final JsonGenerator generator, final SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        final var products = value.getAllProducts();
        final var keys = new ArrayList<Product>();
        final var values = new ArrayList<Integer>();
        products.entrySet().stream().forEach(e -> {
            keys.add(e.getKey());
            values.add(e.getValue());
        });
        generator.writeStartObject();
        generator.writeStringField("departmentName", value.getDepartmentName());
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        generator.writeFieldName("staff");
        generator.writeRawValue(mapper.writeValueAsString(value.getStaff()));

        generator.writeArrayFieldStart("products");
        for (final var el : keys) {
          generator.writeObject(el);
        }
        generator.writeEndArray();
        generator.writeArrayFieldStart("values");
        for (final var el : values) {
          generator.writeObject(el);
        }
        generator.writeEndArray();
        generator.writeEndObject();
    }
}
