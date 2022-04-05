package unieuroop.controller.login;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javafx.util.Pair;
import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Client;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.sale.Sale;
import unieuroop.model.sale.SaleImpl;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;
import unieuroop.model.stock.Stock;
import unieuroop.model.stock.StockImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierImpl;
import unieuroop.view.analytic.ViewAnalytic;

public final class ControllerLoginImpl {
    private Shop shop;
    private Supplier s1 = new SupplierImpl("nome", Map.of());
    private Staff st1 = new Staff("mario", "rossi", LocalDate.now(), 0, "prova@gmail.com", "1234".hashCode(), Map.of(DayOfWeek.of(1), new Pair<>(LocalTime.now(), LocalTime.now())));

    private Staff st2 = new Staff("filippo", "verdi", LocalDate.now(), 0, "prova@gmail.com", "1234".hashCode(),  Map.of(DayOfWeek.of(1), new Pair<>(LocalTime.now(), LocalTime.now())));
    /**
     * ALL THE PRODUCTS THAT WILL BE USED IN THIS TEST.
     */
    private final Product p1 = new ProductImpl(1, "iphone 13 pro", "apple",  1200.00,  900.00, "best phone ever created", Category.SMARTPHONE);
    private final Product p2 = new ProductImpl(2, "applewatch", "apple", 500.00,  200.00, "best watch ever created", Category.SMARTWATCH);
    private final Product p3 = new ProductImpl(3, "mac book pro 14 ", "apple",  3000.00, 2000.00, "best mac book ever created", Category.PC);
    private final Product p4 = new ProductImpl(4, "mac book pro 16", "apple",  6000.00,  3000.00, "best mac book ever created", Category.PC);
   
    private final Sale sale1 = new SaleImpl(LocalDate.of(2010, 5, 20), Map.of(p1, 10, p2, 100, p3, 1), Optional.empty());
    private final Sale sale2 = new SaleImpl(LocalDate.of(2015, 3, 20), Map.of(p1, 10, p2, 100, p4, 1, p3, 10), Optional.empty());
    private final Sale sale3 = new SaleImpl(LocalDate.of(2017, 2, 10), Map.of(p3, 10, p2, 100, p1, 1), Optional.empty());
    private final Sale sale4 = new SaleImpl(LocalDate.of(2010, 9, 20), Map.of(p3, 10, p4, 100, p1, 1), Optional.empty());
    private final Sale sale5 = new SaleImpl(LocalDate.of(2011, 5, 20), Map.of(p1, 10, p4, 100, p3, 1), Optional.empty());
    private final Sale sale6 = new SaleImpl(LocalDate.of(2013, 1, 10), Map.of(p1, 10, p2, 100, p4, 1), Optional.empty());
    
    public ControllerLoginImpl() throws IOException {
        this.shop = new ShopImpl("UnieurOOP");
        this.s1 = new SupplierImpl("nome", Map.of(p1, 10.0, p2, 10.0, p3, 3.0));
        this.shop.addStaff(st1);
        this.shop.addStaff(st2);
        this.shop.getStock().addProducts(Map.of(p1, 10, p2, 10, p3, 3));
        this.shop.addSupplier(s1);
        this.shop.addSale(sale1);
        this.shop.addSale(sale2);
        this.shop.addSale(sale3);
        this.shop.addSale(sale4);
        this.shop.addSale(sale5);
        this.shop.addSale(sale6);
        this.shop.addBills(LocalDate.of(2022, 1, 20), 46310);
        this.shop.addBills(LocalDate.of(2022, 5, 20), 10000);
        this.shop.addBills(LocalDate.now(), 14232);
        this.shop.addBills(LocalDate.of(2015, 3, 20), 2123);
        this.shop.addBills(LocalDate.of(2002, 1, 20), 10231);
        this.shop.addBills(LocalDate.of(2017, 2, 20), 1000);
        this.shop.addBills(LocalDate.now(), 14232);
        this.shop.addBills(LocalDate.of(2013, 4, 20), 2123);
        this.shop.addDepartment(new DepartmentImpl("depart1", Set.of(st1,st2),Map.of(p1, 10, p2, 100, p3, 30)));
        this.shop.registerClient(new Client("matteo", "iorio", LocalDate.now(),Optional.of(13)));
        
        Serialization.<String>serialize(Files.SHOPNAME.getPath(), this.shop.getName());
        Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
        Serialization.<Set<Staff>>serialize(Files.STAFFS.getPath(), this.shop.getStaffs());
        Serialization.<Set<Supplier>>serialize(Files.SUPPLIERS.getPath(), this.shop.getSuppliers());
        Serialization.<Set<Sale>>serialize(Files.SALES.getPath(), this.shop.getSales());
        Serialization.<Set<Client>>serialize(Files.CLIENTS.getPath(), this.shop.getRegisteredClients());
        Serialization.<Stock>serialize(Files.STOCK.getPath(), this.shop.getStock());
        Serialization.<Map<LocalDate, Double>>serialize(Files.BILLS.getPath(), this.shop.getBills());

    }

    public boolean checkPassword(final String email, final String password) {
        return this.shop.getStaffs().stream().anyMatch(s -> s.getEmail().equals(email) && s.getPassword().equals(password.hashCode()));
    }

    public void showMainMenu() {

    }
    
    public void loadData() throws  IOException, ClassNotFoundException {
        final var shopName = Serialization.<String>deserialize(Files.SHOPNAME.getPath(), new TypeReference<String>() { });
        final var departments = Serialization.<Set<Department>>deserialize(Files.DEPARTMENTS.getPath(), new TypeReference<Set<Department>>() { });
        final var staff = Serialization.<Set<Staff>>deserialize(Files.STAFFS.getPath(), new TypeReference<Set<Staff>>() { });
        final var suppliers = Serialization.<Set<Supplier>>deserialize(Files.SUPPLIERS.getPath(), new TypeReference<Set<Supplier>>() { });
        final var sales = Serialization.<Set<Sale>>deserialize(Files.SALES.getPath(), new TypeReference<Set<Sale>>() { });
        final var clients = Serialization.<Set<Client>>deserialize(Files.CLIENTS.getPath(), new TypeReference<Set<Client>>() { });
        departments.forEach(a-> System.out.println(a.getDepartmentName()));
        final var stock = Serialization.<Stock>deserialize(Files.STOCK.getPath(), new TypeReference<Stock>() { });
        final var m = Serialization.<Map<String, Double>>deserialize(Files.BILLS.getPath(), new TypeReference<Map<String, Double>>() { });
        final var bills = m.keySet().stream().collect(Collectors.toMap(a -> LocalDate.parse(a), a -> m.get(a)));
//
//        this.shop = new ShopImpl(shopName, departments, staff, suppliers, sales, clients, stock, bills);
//                this.shop.getStaffs().forEach(a->System.out.println(a));
    }
}
