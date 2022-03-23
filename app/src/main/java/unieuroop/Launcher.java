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
import unieuroop.view.login.ViewLogin;

public final class Launcher extends Application {
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final var loader = new FXMLLoader(getClass().getResource("/pages/Login.fxml"));
        loader.setController(new ViewLogin(new ControllerLoginImpl(new ShopImpl("UnieurOOP"))));
        final Parent root = loader.load();
        final Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("unieurOOP");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(100);
        primaryStage.setMinWidth(400);
        primaryStage.show();
//        final Parent root = FXMLLoader.load(getClass().getResource("/pages/MainMenu.fxml"));
//        final Scene scene = new Scene(root, 1000, 600);
//        primaryStage.setTitle("unieurOOP");
//        primaryStage.setScene(scene);
//        primaryStage.setMinHeight(500);
//        primaryStage.setMinWidth(1000);
//        primaryStage.show();
        
        
    }

}
