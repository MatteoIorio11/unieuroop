package unieuroop.model.shop;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Client;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;
import unieuroop.model.sale.Sale;
import unieuroop.model.stock.Stock;
import unieuroop.model.stock.StockImpl;
import unieuroop.model.supplier.Supplier;

public final class ShopImpl implements Shop {
    private String name;
    private final Set<Department> departments;
    private final Set<Staff> staffs;
    private final Set<Supplier> suppliers;
    private final Set<Sale> sales;
    private final Set<Client> registeredClients;
    private final Stock stock;
    private final Map<LocalDate, Double> bills;

    public ShopImpl(final String name) {
        this(name, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new StockImpl(), new HashMap<>());
    }
    public ShopImpl(final String name, final Set<Department> departments, 
            final Set<Staff> staffs, final Set<Supplier> suppliers, 
            final Set<Sale> sales, final Set<Client> registeredClients, 
            final Stock stock, final Map<LocalDate, Double> bills) {
        this.name = name;
        this.departments = departments;
        this.staffs = staffs;
        this.suppliers = suppliers;
        this.sales = sales;
        this.registeredClients = registeredClients;
        this.stock = stock;
        this.bills = bills;
    }
   
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Map<LocalDate, Double> getBills() {
        return Map.copyOf(this.bills);
    }

    @Override
    public Set<Department> getDepartments() {
        return Set.copyOf(this.departments);
    }

    @Override
    public Set<Staff> getStaffs() {
        return Set.copyOf(this.staffs);
    }

    @Override
    public Set<Supplier> getSuppliers() {
        return Set.copyOf(this.suppliers);
    }

    @Override
    public Set<Sale> getSales() {
        return Set.copyOf(this.sales);
    }

    @Override
    public Set<Sale> getSales(final Predicate<Sale> predicate) {
        return this.sales.stream().filter(s -> predicate.test(s)).collect(Collectors.toSet());
    }

    @Override
    public Set<Client> getRegisteredClients() {
        return Set.copyOf(this.registeredClients);
    }

    @Override
    public Stock getStock() {
        return this.stock;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void addBills(final LocalDate date, final double spent) {
        this.bills.merge(date, spent, (total, bill) -> total + bill);
    }

    @Override
    public void addDepartment(final Department department) {
        this.departments.add(department);
    }

    @Override
    public void addStaff(final Staff staff) {
        this.staffs.add(staff);
    }

    @Override
    public void addSupplier(final Supplier supplier) {
        this.suppliers.add(supplier);
    }

    @Override
    public void addSale(final Sale sale) {
        this.sales.add(sale);
    }

    @Override
    public void registerClient(final Client client) {
        this.registeredClients.add(client);
    }

    @Override
    public void removeDepartment(final Department department) {
        if (!this.departments.remove(department)) {
            throw new NoSuchElementException("The input department does not exist");
        }
    }

    @Override
    public void removeStaff(final Staff staff) {
        if (!this.staffs.remove(staff)) {
            throw new NoSuchElementException("The input staff does not exist");
        }
    }

    @Override
    public void removeSupplier(final Supplier supplier) {
        if (!this.suppliers.remove(supplier)) {
            throw new NoSuchElementException("The input supplier does not exist");
        }
    }

    @Override
    public void removeSale(final Sale sale) {
        if (!this.sales.remove(sale)) {
            throw new NoSuchElementException("The input sale does not exist");
        }
    }

    @Override
    public void removeClient(final Client client) {
        if (!this.registeredClients.remove(client)) {
            throw new NoSuchElementException("The input client does not exist");
        }
    }

    @Override
    public Department mergeDepartments(final Set<Department> departments, final String newName) {
        //get all products from the departments i want to merge
        final Map<Product, Integer> products = departments.stream()
                .map(d -> d.getAllProducts())
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
        //get all staff from the department i want to merge
        final Set<Staff> staff = departments.stream()
                .flatMap(d -> d.getStaff().stream())
                .distinct()
                .collect(Collectors.toSet());
        //removing all departments
        departments.stream().forEach(d -> this.removeDepartment(d));
        //creating new department
        final var dep = new DepartmentImpl(newName, staff, products);
        this.addDepartment(dep);
        return dep;
    }

    @Override
    public void supplyDepartment(final Department department, final Map<Product, Integer> requestedProducts) {
        final var products = this.stock.takeFromStock(requestedProducts);
        department.addProducts(products);
    }

}
