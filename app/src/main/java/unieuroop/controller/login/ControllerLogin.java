package unieuroop.controller.login;

import java.io.IOException;

import unieuroop.model.shop.Shop;

public interface ControllerLogin {

    /**
     * Return true or false if the password is correct or not.
     * @param email
     * @param password
     * @return result.
     */
    boolean checkPassword(String email, String password);

    /**
     * Load all the file in the Shop.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void loadData() throws IOException, ClassNotFoundException;

    /**
     * Return the entire Shop.
     * @return shop.
     */
    Shop getShop();

}
