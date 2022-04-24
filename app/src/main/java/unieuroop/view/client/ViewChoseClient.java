package unieuroop.view.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface ViewChoseClient {

    /**
     * initialize new ViewChoseClient.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * handler for the selection of the client.
     * @param event
     */
    void listSelectClientHandler(MouseEvent event);

    /**
     * handler to confirm of the selected client.
     * @param event
     */
    void buttonSelectHandler(ActionEvent event);

    /**
     * handler to cancel the total sale.
     * @param event
     */
    void buttonCancelHandler(ActionEvent event);

    /**
     * handler for a selection of an empty client.
     * @param event
     */
    void buttonEmptyHandler(ActionEvent event);

}
