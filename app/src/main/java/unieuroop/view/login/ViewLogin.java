package unieuroop.view.login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import unieuroop.controller.login.ControllerLoginImpl;
import unieuroop.controller.serialization.Pages;

public class ViewLogin implements Initializable {
    private final ControllerLoginImpl controller;
    @FXML 
    private TextField email;
    @FXML
    private PasswordField password;
    public ViewLogin(final ControllerLoginImpl controller) {
        this.controller = controller;
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
    }
    @FXML
    private void btn_login_action(final ActionEvent event) {
        if(this.controller.CheckPassword(this.email.getText(), this.password.getText())) {
            //load page
        }else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Wrong Password");
            alert.showAndWait();
        }
    }

}
