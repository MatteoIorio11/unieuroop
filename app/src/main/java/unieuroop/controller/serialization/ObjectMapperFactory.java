package unieuroop.controller.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentDeserializer;
import unieuroop.model.department.DepartmentSerializer;
import unieuroop.model.person.Staff;
import unieuroop.model.person.StaffDeserializer;
import unieuroop.model.person.StaffSerializer;
import unieuroop.model.sale.Sale;
import unieuroop.model.sale.SaleDeserializer;
import unieuroop.model.sale.SaleSerializer;
import unieuroop.model.stock.Stock;
import unieuroop.model.stock.StockDeserializer;
import unieuroop.model.stock.StockSerializer;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierDeserializer;
import unieuroop.model.supplier.SupplierImpl;
import unieuroop.model.supplier.SupplierSerializer;

public class ObjectMapperFactory {
    private ObjectMapperFactory() {
    }
    public static ObjectMapper getMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        final SimpleModule module = new SimpleModule();
        module.addSerializer(SupplierImpl.class, new SupplierSerializer());
        module.addSerializer(Department.class, new DepartmentSerializer());
        module.addSerializer(Staff.class, new StaffSerializer());
        module.addSerializer(Sale.class, new SaleSerializer());
        module.addSerializer(Stock.class, new StockSerializer());
        module.addDeserializer(Stock.class, new StockDeserializer());
        module.addDeserializer(Sale.class, new SaleDeserializer());
        module.addDeserializer(Supplier.class, new SupplierDeserializer());
        module.addDeserializer(Staff.class, new StaffDeserializer());
        module.addDeserializer(Department.class, new DepartmentDeserializer());
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(module);
        return mapper;
    }
}
