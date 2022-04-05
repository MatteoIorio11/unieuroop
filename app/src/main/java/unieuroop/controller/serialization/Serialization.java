package unieuroop.controller.serialization;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentDeserializer;
import unieuroop.model.department.DepartmentSerializer;
import unieuroop.model.person.Staff;
import unieuroop.model.person.StaffDeserializer;
import unieuroop.model.person.StaffSerializer;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierDeserializer;
import unieuroop.model.supplier.SupplierImpl;
import unieuroop.model.supplier.SupplierSerializer;



public final class Serialization {
    private Serialization() { }

    public static <X> void serialize(final String filePath, final X obj) throws  IOException {
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        mapper.writeValue(new File(filePath), obj);
    }

    public static <X> X deserialize(final String filePath, final TypeReference<?> c) throws IOException {
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        return (X) mapper.readValue(new File(filePath), c);
    }

}
