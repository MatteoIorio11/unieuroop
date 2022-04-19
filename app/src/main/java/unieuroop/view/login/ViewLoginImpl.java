package unieuroop.view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import unieuroop.controller.login.ControllerLogin;
import unieuroop.controller.shop.ControllerShopImpl;
import unieuroop.view.loader.Loader;
import unieuroop.view.menu.ViewMainMenuImpl;

public final class ViewLoginImpl implements Initializable, ViewLogin {
    @FXML  private TextField email;
    @FXML private PasswordField password;
    private final ControllerLogin controller;
    private static final double MIN_HEIGHT = 600;
    private static final double MIN_WIDTH = 1000;
    public ViewLoginImpl(final ControllerLogin controller) {
        this.controller = controller;
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final long startTime = System.nanoTime();
        try {
            this.controller.loadData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        final long endTime = System.nanoTime();
        final long duration = (endTime - startTime);
        System.out.println((double) duration/1000000 );
    }
    @Override
    @FXML
    public void btnLoginHandler(final ActionEvent event) throws IOException {
        if (this.controller.checkPassword(this.email.getText(), this.password.getText())) {
            Loader.loadStage("/pages/MainMenu.fxml", "unieurOOP", new ViewMainMenuImpl(new ControllerShopImpl(this.controller.getShop())), 
                    ViewLoginImpl.MIN_HEIGHT, ViewLoginImpl.MIN_WIDTH).show();
            final Stage stage = (Stage) this.email.getScene().getWindow();
            stage.close();
        } else {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Wrong Password");
            alert.showAndWait();
        }
    }


}
