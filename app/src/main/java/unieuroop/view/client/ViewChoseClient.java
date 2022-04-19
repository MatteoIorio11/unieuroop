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
     * 
     * @param event
     */
    void listSelectClientHandler(MouseEvent event);

    /**
     * 
     */
    void buttonSelectHandler(ActionEvent event);

    /**
     * 
     * @param event
     */
    void buttonCancelHandler(ActionEvent event);

    /**
     * 
     * @param event
     */
    void buttonEmptyHandler(ActionEvent event);

}
