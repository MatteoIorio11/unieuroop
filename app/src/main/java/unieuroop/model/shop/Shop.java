package unieuroop.model.shop;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import unieuroop.model.department.Department;
import unieuroop.model.person.Client;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.sale.Sale;
import unieuroop.model.stock.Stock;
import unieuroop.model.supplier.Supplier;

public interface Shop {

    /**
     * 
     * @return the name of the shop
     */
    String getName();

    /**
     * 
     * @return the map of all bills
     */
    Map<LocalDate, Double> getBills();

    /**
     * 
     * @return a set containing all the departments of the shop
     */
    Set<Department> getDepartments();

    /**
     * 
     * @return a set containing all the staffs of the shop
     */
    Set<Staff> getStaffs();

    /**
     * 
     * @return a set containing all the suppliers of the shop 
     */
    Set<Supplier> getSuppliers();

    /**
     * 
     * @return a set containing all the sales the shop made so far
     */
    Set<Sale> getSales();

    /**
     * 
     * @param predicate
     * @return a set containing all the sales the shop made so far and that comply with the predicate
     */
    Set<Sale> getSales(Predicate<Sale> predicate);

    /**
     * 
     * @return a set containing all the registered clients of the shop
     */
    Set<Client> getRegisteredClients();

    /**
     * 
     * @return the stock of the shop
     */
    Stock getStock();

    /**
     * Allows to change the shop name.
     * @param name
     */
    void setName(String name);

    /**
     * Add a new bill inside the Map of all bills.
     * @param date
     * @param spent
     */
    void addBills(LocalDate date, double spent);

    /**
     * Adds a new department to the shop.
     * @param department
     */
    void addDepartment(Department department);
    /**
     * Add new staff inside the department.
     * @param departmentInput
     * @param staff
     */
    void addStaffIn(Department departmentInput, Set<Staff> staff);
    /**
     * Remove staff from the department.
     * @param departmentInput
     * @param staff
     */
    void removeStaffFrom(Department departmentInput, Set<Staff> staff);
    /**
     * Edit the selected staff.
     * @param name
     * @param surname
     * @param birthday
     * @param email
     * @param password
     * @param hoursStartWork
     * @param minutesStartWork
     * @param hoursEndWork
     * @param minutesEndWork
     * @param staff
     */
    void editStaff(String name, String surname, LocalDate birthday, String email, String password, 
            String hoursStartWork, String minutesStartWork, String hoursEndWork, String minutesEndWork, Staff staff);
    /**
     * Edit the selected client.
     * @param name
     * @param surname
     * @param birthday
     * @param client
     */
    void editClient(String name, String surname, LocalDate birthday, Client client);

    /**
     * Adds a new person to the staff of the shop.
     * @param staff
     */
    void addStaff(Staff staff);

    /**
     * Adds a new supplier to the shop.
     * @param supplier
     */
    void addSupplier(Supplier supplier);

    /**
     * Adds a new sale made by the shop.
     * @param sale
     */
    void addSale(Sale sale);

    /**
     * Registers a new client.
     * @param client
     */
    void registerClient(Client client);

    /**
     * Removes an existing department.
     * @param department
     */
    void removeDepartment(Department department);

    /**
     * Removes a person from the staff of the shop.
     * @param staff
     */
    void removeStaff(Staff staff);

    /**
     * Removes a supplier of the shop.
     * @param supplier
     */
    void removeSupplier(Supplier supplier);

    /**
     * Removes an existing sale.
     * @param sale
     */
    void removeSale(Sale sale);

    /**
     * Removes a registered client.
     * @param client
     */
    void removeClient(Client client);

    /**
     * Takes the requested quantity of the specified products from the stock and add them in the specified department.
     * @param department
     * @param requestedProduct
     */
    void supplyDepartment(Department department, Map<Product, Integer> requestedProduct);

    /**
     * 
     * @param department
     * @param requestedProducts
     */
    void putProductsBackInStock(Department department, Map<Product, Integer> requestedProducts);

    /**
     * 
     * @param departments
     * @param newName
     * @return The new merged department, created by the previously selected departments.
     */
    Department mergeDepartments(Set<Department> departments, String newName);

    /**
     * Returns all the categories present in the Shop.
     * @return allCategories.
     */
    Set<Category> getAllCategories();
}
