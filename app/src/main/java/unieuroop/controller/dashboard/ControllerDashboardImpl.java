package unieuroop.controller.dashboard;

import java.util.Set;

import unieuroop.model.analytic.Analytic;
import unieuroop.model.analytic.AnalyticImpl;
import unieuroop.model.sale.Sale;
import unieuroop.model.shop.Shop;

public final class ControllerDashboardImpl implements ControllerDashboard {
    private final Shop shop;
    private final Analytic analytic;
    public ControllerDashboardImpl(final Shop shop) {
        this.shop = shop;
        this.analytic = new AnalyticImpl(shop);
    }

    @Override
    public int getStaff() {
        return this.shop.getStaffs().size();
    }

    @Override
    public int getClients() {
        return this.shop.getRegisteredClients().size();
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
    public Double getStockPrice() {
        return this.analytic.getTotalStockPrice();
    }

    @Override
    public Double getShopEarnings() {
        return this.analytic.getTotalShopEarned();
    }

    @Override
    public Double getTotalSpent() {
        return this.analytic.getTotalAmountSpent();
    }

    @Override
    public boolean isEarning() {
        return this.getTotalSpent() < this.getShopEarnings();
    }

    @Override
    public Set<Sale> getSales() {
        return this.shop.getSales();
    }
}
