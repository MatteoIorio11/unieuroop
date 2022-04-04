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
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        SimpleModule module = new SimpleModule();
        module.addSerializer(SupplierImpl.class, new SupplierSerializer());
        module.addSerializer(Department.class, new DepartmentSerializer());
        module.addSerializer(Staff.class, new StaffSerializer());
        mapper.registerModule(module);
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(new File(filePath), obj);
    }

    public static <X> X deserialize(final String filePath, final TypeReference<?> c) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(Supplier.class, new SupplierDeserializer());
        module.addDeserializer(Staff.class, new StaffDeserializer());
        module.addDeserializer(Department.class, new DepartmentDeserializer());
        mapper.registerModule(module);
        mapper.registerModule(new JavaTimeModule());
        return (X) mapper.readValue(new File(filePath), c);
    }

}
