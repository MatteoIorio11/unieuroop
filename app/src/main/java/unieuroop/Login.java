package unieuroop;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public final class Login extends Application {
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("/pages/MainMenu.fxml"));
        final Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("unieurOOP");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(1200);
        primaryStage.show();
        
        
    }

}
