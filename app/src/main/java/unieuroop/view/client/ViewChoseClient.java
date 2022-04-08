package unieuroop.view.client;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import unieuroop.controller.client.ControllerClientImpl;
import unieuroop.controller.sale.ControllerSaleImpl;
import unieuroop.model.person.Client;

public final class ViewChoseClient extends Stage implements Initializable {
    @FXML
    private ListView<Client> listClients;
    @FXML
    private TextField textName;
    @FXML
    private Button btnSelect;
    @FXML
    private Button btnQuit;
    private Optional<Client> selectedClient;
    private final Stage window;
    private final ControllerSaleImpl controllerSale;
    private final ControllerClientImpl controllerClient;
    public ViewChoseClient(final ControllerSaleImpl controller, final ControllerClientImpl controllerClient, final Stage window) {
        this.controllerSale = controller;
        this.controllerClient = controllerClient;
        this.window = window;
        this.selectedClient = Optional.empty();
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listClients.getItems().addAll(this.controllerClient.getRegisteredClients());

        this.textName.textProperty().addListener((observable, oldValue, newValue) -> {
            this.listClients.getItems().clear();
            if (Objects.isNull(newValue)) {
                this.listClients.getItems().addAll(this.controllerClient.getRegisteredClients());
            } else {
                this.listClients.getItems().addAll(this.controllerClient.getRegisteredClients().stream()
                        .filter((client) -> client.getName().contains(newValue)).collect(Collectors.toList()));
            }
        });
        this.listClients.getSelectionModel().selectedItemProperty().addListener((evenet) -> {
            this.selectedClient = Optional.of(this.listClients.getSelectionModel().getSelectedItem());
        });
        this.btnSelect.setOnMouseClicked((e) -> {
            this.controllerSale.closeSale(selectedClient);
            this.window.close();
        });
        this.btnQuit.setOnMouseClicked((event) -> {
            this.controllerSale.closeSale(Optional.empty());
            this.window.close();
        });
    }
}
