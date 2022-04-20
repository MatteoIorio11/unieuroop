package unieuroop.controller.client;

import java.time.LocalDate;
import java.util.Set;

import unieuroop.model.person.Client;
import unieuroop.model.person.ClientImpl;

public interface ControllerClient {

    /**
     * add new client.
     * @param name
     * @param surname
     * @param birthday
     */
    void addClient(String name, String surname, LocalDate birthday);

    /**
     * edit client.
     * @param name
     * @param surname
     * @param birthday
     * @param client
     */
    void editClient(String name, String surname, LocalDate birthday, Client client);

    /**
     * delete client.
     * @param client
     */
    void deleteClient(Client client);

    /**
     * 
     * @return all clients registered to the shop
     */
    Set<Client> getRegisteredClients();

}
