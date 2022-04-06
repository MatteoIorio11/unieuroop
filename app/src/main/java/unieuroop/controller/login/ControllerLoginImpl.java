package unieuroop.controller.login;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;
import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.department.Department;
import unieuroop.model.person.Client;
import unieuroop.model.person.Staff;
import unieuroop.model.sale.Sale;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;
import unieuroop.model.stock.Stock;
import unieuroop.model.supplier.Supplier;

public final class ControllerLoginImpl {
    private Shop shop;

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
        final var stock = Serialization.<Stock>deserialize(Files.STOCK.getPath(), new TypeReference<Stock>() { });
        final var m = Serialization.<Map<String, Double>>deserialize(Files.BILLS.getPath(), new TypeReference<Map<String, Double>>() { });
        final var bills = m.keySet().stream().collect(Collectors.toMap(a -> LocalDate.parse(a), a -> m.get(a)));

        this.shop = new ShopImpl(shopName, departments, staff, suppliers, sales, clients, stock, bills);
    }

    public Shop getShop() {
        return this.shop;
    }
}
