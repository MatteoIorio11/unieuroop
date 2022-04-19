package unieuroop.controller.client;

import java.time.LocalDate;
import java.util.Set;
import unieuroop.model.person.Client;

public interface ControllerClient {

    /**
     * 
     * @param name
     * @param surname
     * @param birthday
     */
    void addClient(String name, String surname, LocalDate birthday);

    /**
     * 
     * @param name
     * @param surname
     * @param birthday
     * @param client
     */
    void editClient(String name, String surname, LocalDate birthday, Client client);

    /**
     * 
     * @param client
     */
    void deleteClient(Client client);

    /**
     * 
     * @return all clients registered to the shop
     */
    Set<Client> getRegisteredClients();

}
