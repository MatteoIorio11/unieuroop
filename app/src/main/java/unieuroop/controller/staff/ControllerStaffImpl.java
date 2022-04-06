package unieuroop.controller.staff;

import java.util.Set;

import unieuroop.model.person.Staff;
import unieuroop.model.shop.Shop;

public final class ControllerStaffImpl {
    private final Shop shop;

    public ControllerStaffImpl(final Shop shop) {
        this.shop = shop;
    }

    public Set<Staff> getStaff() {
        return this.shop.getStaffs();
    }
}
