package unieuroop.controller.login;

import java.io.IOException;

import unieuroop.model.shop.Shop;

public interface ControllerLogin {

    boolean checkPassword(String email, String password);

    void showMainMenu();

    void loadData() throws IOException, ClassNotFoundException;

    Shop getShop();

    void run();
}