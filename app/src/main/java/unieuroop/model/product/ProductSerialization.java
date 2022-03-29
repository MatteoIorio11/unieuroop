package unieuroop.model.product;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import unieuroop.model.supplier.SupplierImpl;

public class ProductSerialization extends JsonSerializer<Product>{
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public void serialize(Product value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        gen.writeFieldName(writer.toString());
    }
}
