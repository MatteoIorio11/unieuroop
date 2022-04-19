package unieuroop.view.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public interface ViewDashboard {

    /**
     * initialize new ViewDashboard.
     * @param location
     * @param resources
     */
    void initialize(URL location, ResourceBundle resources);

    /**
     * handler for the selection of the selected sale.
     * @param event
     */
    void lstSalesSelectClientHandler(MouseEvent event);

}
