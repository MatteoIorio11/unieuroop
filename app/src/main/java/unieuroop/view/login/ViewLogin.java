package unieuroop.view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public interface ViewLogin {

    /**
     * initialize new ViewLogin.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * handler for login in the application.
     * @param event
     * @throws IOException
     */
    void btnLoginHandler(ActionEvent event) throws IOException;

}
