package unieuroop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Login extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("/pages/MainMenu.fxml"));
        final Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("unieurOOP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
