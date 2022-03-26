package unieuroop;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import unieuroop.controller.login.ControllerLoginImpl;
import unieuroop.model.shop.ShopImpl;
import unieuroop.view.login.ViewLoginImpl;

public final class Launcher extends Application {
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final var loader = new FXMLLoader(getClass().getResource("/pages/Login.fxml"));
        loader.setController(new ViewLoginImpl(new ControllerLoginImpl(), primaryStage));
        final Parent root = loader.load();
        final Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("unieurOOP");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(100);
        primaryStage.setMinWidth(400);
        primaryStage.show();
    }

}
