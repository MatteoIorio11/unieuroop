package unieuroop.view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public interface ViewLogin {

    void initialize(URL location, ResourceBundle resources);

    void btnLoginHandler(ActionEvent event) throws IOException;

}