package unieuroop.controller.login;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import unieuroop.model.person.Staff;
import unieuroop.model.shop.Shop;

public final class ControllerLoginImpl {
    private final Shop shop;
    public ControllerLoginImpl(final Shop shop) {
        this.shop = shop;
        this.shop.addStaff(new Staff("mario", "rossi", LocalDate.now(), 0, "prova@gmail.com", "1234".hashCode(), null));
    }
    
    public boolean checkPassword(final String email, final String password) {
        return this.shop.getStaffs().stream().anyMatch(s -> s.getEmail().equals(email) && s.getPassword().equals(password.hashCode()));
    }
    
    public void showMainMenu() {

    }
    
    public void loadData() {
        
    }
}
