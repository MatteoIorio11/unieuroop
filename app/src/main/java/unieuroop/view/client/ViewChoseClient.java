package unieuroop.view.client;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import unieuroop.controller.shop.ControllerShopImpl;
import unieuroop.model.person.Client;

public final class ViewChoseClient extends Stage implements Initializable {
    private final ControllerShopImpl controller;
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
    public ViewChoseClient(final ControllerShopImpl controller, final Stage window) {
        this.controller = controller;
        this.window = window;
        this.selectedClient = Optional.empty();
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listClients.getItems().addAll(this.controller.getClients());
        this.textName.textProperty().addListener((observable, oldValue, newValue) -> {
            this.listClients.getItems().clear();
            if (newValue.isEmpty()) {
                this.listClients.getItems().addAll(this.controller.getClients());
            } else {
                this.listClients.getItems().addAll(this.controller.getClients().stream()
                        .filter((client) -> client.getName().contains(newValue)).collect(Collectors.toList()));
            }
        });
        this.listClients.getSelectionModel().selectedItemProperty().addListener((e) -> {
            this.selectedClient = Optional.of(this.listClients.getSelectionModel().getSelectedItem());
        });
        this.btnSelect.setOnMouseClicked((e) -> {
            System.out.println("AO");
            this.controller.closeSale(selectedClient);
//            this.clearListClient();
            System.out.println("AO");
            this.window.close();
        });
        this.btnQuit.setOnMouseClicked((event) -> {
            this.controller.closeSale(Optional.empty());
//            this.clearListClient();
            this.window.close();
        });
    }
}
