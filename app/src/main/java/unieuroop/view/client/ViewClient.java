package unieuroop.view.client;

import java.net.URL;
import java.util.ResourceBundle;

public interface ViewClient {

    /**
     * initialize new ViewClient.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * handler for add client.
     */
    void btnAddClientHandler();

    /**
     * handler for edit client.
     */
    void btnEditClientHandler();

    /**
     * handler for list clients.
     */
    void listClientHandler();

    /**
     * handler for delete client.
     */
    void btnDeleteClientHandler();

}
