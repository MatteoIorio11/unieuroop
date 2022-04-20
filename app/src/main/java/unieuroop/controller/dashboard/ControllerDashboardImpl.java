package unieuroop.controller.dashboard;

import java.util.List;
import java.util.stream.Collectors;
import unieuroop.model.sale.Sale;
import unieuroop.model.shop.Shop;

public final class ControllerDashboardImpl implements ControllerDashboard {
    private final Shop shop;

    public ControllerDashboardImpl(final Shop shop) {
        this.shop = shop;
    }

    @Override
    public int getStaff() {
        return this.shop.getStaffs().size();
    }

    @Override
    public int getSuppliers() {
        return this.shop.getSuppliers().size();
    }

    @Override
    public int getDepartments() {
        return this.shop.getDepartments().size();
    }

    @Override
    public List<Sale> getSales() {
        return this.shop.getSales().stream()
                .sorted((sale1, sale2) -> sale2.getDate().compareTo(sale1.getDate()))
                .collect(Collectors.toList());
    }
}
