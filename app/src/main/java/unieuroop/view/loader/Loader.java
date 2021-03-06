package unieuroop.view.loader;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public final class Loader {

    private Loader() {

    }

    /**
     * 
     * @param <X>
     * @param path
     * @param controller
     * @return To DO.
     * @throws IOException
     */
    public static <X> Pane loadPane(final String path, final X controller) throws IOException {
        final Pane pane;
        final var loader = new FXMLLoader(Loader.class.getResource(path));
        loader.setController(controller);
        pane = loader.load();
        return pane;
    }

    /**
     * 
     * @param <X>
     * @param path
     * @param title
     * @param controller
     * @param minHeight
     * @param minWidth
     * @return TO Do.
     * @throws IOException
     */
    public static <X> Stage loadStage(final String path, final String title, final X controller, final double minHeight, final double minWidth) throws IOException {
        final Pane pane;
        final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        final double xSize =  screenBounds.getMaxX() / 2;
        final double ySize = screenBounds.getMaxY() / 2;
        final Stage newWindow = new Stage();
        newWindow.setMinHeight(minHeight);
        newWindow.setMinWidth(minWidth);
        final var loader = new FXMLLoader(Loader.class.getResource(path));
        loader.setController(controller);

        pane = loader.load();

        final Scene secondScene = new Scene(pane, xSize, ySize);
        newWindow.setTitle(title);
        newWindow.setScene(secondScene);
        return newWindow;
    }

}
