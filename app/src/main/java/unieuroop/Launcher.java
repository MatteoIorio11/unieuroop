package unieuroop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import unieuroop.controller.login.ControllerLoginImpl;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.serialization.Save;
import unieuroop.view.loader.Loader;
import unieuroop.view.login.ViewLoginImpl;

public final class Launcher extends Application {

    private static final int MIN_HEIGHT = 100;
    private static final int MIN_WIDTH = 400;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Save.createDirectory();
        final Pane pane = Loader.loadPane(Pages.LOGIN.getPath(), new ViewLoginImpl(new ControllerLoginImpl()));
        final Scene scene = new Scene(pane, 700, 500);
        primaryStage.setTitle("unieurOOP");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(Launcher.MIN_HEIGHT);
        primaryStage.setMinWidth(Launcher.MIN_WIDTH);
        primaryStage.show();
    }

}
