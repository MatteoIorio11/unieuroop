package unieuroop.view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import unieuroop.controller.login.ControllerLoginImpl;
import unieuroop.controller.shop.ControllerShopImpl;
import unieuroop.view.loader.Loader;
import unieuroop.view.menu.ViewMainMenu;

public final class ViewLoginImpl implements Initializable {
    private final ControllerLoginImpl controller;
    @FXML 
    private TextField email;
    @FXML
    private PasswordField password;
    public ViewLoginImpl(final ControllerLoginImpl controller) {
        this.controller = controller;
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        try {
            this.controller.loadData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void btnLoginHandler(final ActionEvent event) throws IOException {
        if (this.controller.checkPassword(this.email.getText(), this.password.getText())) {
            Loader.loadStage("/pages/MainMenu.fxml", "unieurOOP", new ViewMainMenu(new ControllerShopImpl(this.controller.getShop())), 600, 1000).show();
            final Stage stage = (Stage) this.email.getScene().getWindow();
            stage.close();
        } else {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Wrong Password");
            alert.showAndWait();
        }
    }

}
