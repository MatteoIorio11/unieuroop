package unieuroop.controller.login;

import java.io.IOException;

import unieuroop.model.shop.Shop;

public interface ControllerLogin {

    /**
     * 
     * @param email
     * @param password
     * @return
     */
    boolean checkPassword(String email, String password);

    /**
     * 
     */
    void showMainMenu();

    /**
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void loadData() throws IOException, ClassNotFoundException;

    /**
     * 
     * @return
     */
    Shop getShop();

    /**
     * 
     */
    void run();
}
