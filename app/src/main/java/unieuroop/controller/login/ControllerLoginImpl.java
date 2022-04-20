package unieuroop.controller.login;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.core.type.TypeReference;
import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.department.Department;
import unieuroop.model.person.ClientImpl;
import unieuroop.model.person.StaffImpl;
import unieuroop.model.sale.Sale;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;
import unieuroop.model.stock.Stock;
import unieuroop.model.supplier.Supplier;

public final class ControllerLoginImpl implements ControllerLogin {
    private Shop shop;

    @Override
    public boolean checkPassword(final String email, final String password) {
        return this.shop.getStaffs().stream().anyMatch(s -> s.getEmail().equals(email) && s.getPassword().equals(password.hashCode()));
    }

    @Override
    public void loadData() throws  IOException, ClassNotFoundException {
        final var shopName = Serialization.<String>deserialize(Files.SHOPNAME.getPath(), new TypeReference<String>() { });
        final var departments = Serialization.<Set<Department>>deserialize(Files.DEPARTMENTS.getPath(), new TypeReference<Set<Department>>() { });
        final var staff = Serialization.<Set<StaffImpl>>deserialize(Files.STAFFS.getPath(), new TypeReference<Set<StaffImpl>>() { });
        final var suppliers = Serialization.<Set<Supplier>>deserialize(Files.SUPPLIERS.getPath(), new TypeReference<Set<Supplier>>() { });
        final var sales = Serialization.<Set<Sale>>deserialize(Files.SALES.getPath(), new TypeReference<Set<Sale>>() { });
        final var clients = Serialization.<Set<ClientImpl>>deserialize(Files.CLIENTS.getPath(), new TypeReference<Set<ClientImpl>>() { });
        final var stock = Serialization.<Stock>deserialize(Files.STOCK.getPath(), new TypeReference<Stock>() { });
        final var bills = Serialization.<Map<LocalDate, Double>>deserialize(Files.BILLS.getPath(), new TypeReference<Map<LocalDate, Double>>() { });
        this.shop = new ShopImpl(shopName, departments, staff, suppliers, sales, clients, stock, bills);
    }

    @Override
    public Shop getShop() {
        return this.shop;
    }
}
