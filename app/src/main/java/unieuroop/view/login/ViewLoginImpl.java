package unieuroop.view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import unieuroop.controller.login.ControllerLoginImpl;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.shop.ControllerShopImpl;
import unieuroop.view.menu.ViewMainMenu;

public class ViewLoginImpl implements Initializable {
    private final ControllerLoginImpl controller;
    private final Stage primaryStage;
    @FXML 
    private TextField email;
    @FXML
    private PasswordField password;
    public ViewLoginImpl(final ControllerLoginImpl controller, final Stage primaryStage) {
        this.controller = controller;
        this.primaryStage = primaryStage;
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
    private void btnLoginHandler(final ActionEvent event) throws IOException {
        if (this.controller.checkPassword(this.email.getText(), this.password.getText())) {
            final var loader = new FXMLLoader(getClass().getResource("/pages/MainMenu.fxml"));
            loader.setController(new ViewMainMenu(primaryStage, new ControllerShopImpl(this.controller.getShop())));
            final Parent root = loader.load();
          final Scene scene = new Scene(root, 1000, 600);
          primaryStage.setTitle("unieurOOP");
          primaryStage.setScene(scene);
          primaryStage.setMinHeight(500);
          primaryStage.setMinWidth(1000);
          primaryStage.show();
        } else {
            final Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Wrong Password");
            alert.showAndWait();
        }
    }

}
