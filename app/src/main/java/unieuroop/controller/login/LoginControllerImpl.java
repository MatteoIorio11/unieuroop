package unieuroop.controller.login;

import java.util.HashMap;
import java.util.Map;

import unieuroop.model.person.Staff;
import unieuroop.model.shop.Shop;

public class LoginControllerImpl {
    private final Shop shop;
    public LoginControllerImpl(Shop shop) {
        this.shop = shop;
    }
    public boolean CheckPassword(String email, String password) {
        return false;
        //return this.shop.getStaffs().stream().anyMatch(s -> s.getEmail() == email && s.getPassword() == password.hashCode());
    }
}
