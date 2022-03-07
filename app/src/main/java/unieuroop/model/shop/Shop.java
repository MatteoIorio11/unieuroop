package unieuroop.model.shop;

import java.util.Set;

import unieuroop.model.department.Department;
import unieuroop.model.person.Client;
import unieuroop.model.person.Staff;
import unieuroop.model.sale.Sale;
import unieuroop.model.stock.Stock;
import unieuroop.model.supplier.Supplier;

public interface Shop {
    String getName();

    Set<Department> getDepartments();

    Set<Staff> getStaffs();

    Set<Supplier> getSuppliers();

    Set<Sale> getSales();

    Set<Client> getRegisteredClients();

    Stock getStock();

    void addDepartment(Department department);

    void addStaff(Staff staff);

    void addSupplier(Supplier supplier);

    void addSale(Sale sale);

    void registerClient(Client client);

    void removeDepartment(Department department);

    void removeStaff(Staff staff);

    void removeSupplier(Supplier supplier);

    void removeSale(Sale sale);

    void removeClient(Client client);
}
