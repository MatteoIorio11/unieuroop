package unieuroop.controller.login;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import unieuroop.model.person.Staff;
import unieuroop.model.shop.Shop;

public class ControllerLoginImpl {
    private final Shop shop;
    public ControllerLoginImpl(Shop shop) {
        this.shop = shop;
        this.shop.addStaff(new Staff("mario","rossi",LocalDate.now(),0, "fabio.vincenzi2001@gmail.com", "1234".hashCode(),null));
    }
    public boolean CheckPassword(String email, String password) {
        //return false;
        System.out.println(password.hashCode());
        return this.shop.getStaffs().stream().anyMatch(s -> s.getEmail().equals(email) && s.getPassword().equals(password.hashCode()));
    }
}
