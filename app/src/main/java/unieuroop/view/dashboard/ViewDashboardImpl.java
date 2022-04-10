package unieuroop.view.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import unieuroop.controller.dashboard.ControllerDashboard;
import unieuroop.controller.serialization.Pages;
import unieuroop.model.sale.Sale;
import unieuroop.view.loader.Loader;

public final class ViewDashboardImpl implements Initializable {
    @FXML
    private Label lblStaff;
    @FXML
    private Label lblSuppliers;
    @FXML private Label lblDepartments;
    @FXML
    private Label lblStockPrice;
    @FXML
    private Label lblShopEarnings;
    @FXML
    private Label lblTotalSpent;
    @FXML
    private ListView<Sale> lstViewSales;
    @FXML
    private GridPane cardShopEarnings;
    private final ControllerDashboard controller;

    public ViewDashboardImpl(final ControllerDashboard controller) {
        this.controller = controller;
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.lblDepartments.setText(String.valueOf(this.controller.getDepartments()));
        this.lblShopEarnings.setText(String.valueOf(this.controller.getShopEarnings()));
        this.lblStaff.setText(String.valueOf(this.controller.getStaff()));
        this.lblStockPrice.setText(String.valueOf(this.controller.getStockPrice()));
        this.lblSuppliers.setText(String.valueOf(this.controller.getSuppliers()));
        this.lblTotalSpent.setText(String.valueOf(this.controller.getTotalSpent()));
        this.lstViewSales.getItems().addAll(this.controller.getSales());
        if (this.controller.isEarning()) {
            this.cardShopEarnings.setStyle("-fx-background-color: #15cf00; ");
        } else {
            this.cardShopEarnings.setStyle("-fx-background-color: #ff0000; ");
        }
    }
    @FXML
    public void lstSalesSelectClientHandler(final MouseEvent event) {
        final Optional<Sale> selected = Optional.of(this.lstViewSales.getSelectionModel().getSelectedItem());
        if (selected.isPresent()) {
            try {
                Loader.loadStage(Pages.SALE_PRODUCTS.getPath(), "Products", new ViewSaleProductsImpl(selected.get()), 300, 300).showAndWait();
            } catch (IOException e) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

}
