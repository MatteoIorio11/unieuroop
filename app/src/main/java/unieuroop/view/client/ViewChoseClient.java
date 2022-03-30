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
import unieuroop.view.menu.ViewMainMenu;

public final class ViewChoseClient extends Stage implements Initializable {
    private final ControllerShopImpl controller;
    private final ViewMainMenu viewMenu;
    @FXML
    private ListView<Client> listClients;
    @FXML
    private TextField textName;
    @FXML
    private Button btnQuit;
    private Optional<Client> selectedClient = Optional.empty();
    private final Stage window;
    public ViewChoseClient(final ControllerShopImpl controller, final ViewMainMenu viewMenu, final Stage window) {
        this.controller = controller;
        this.viewMenu = viewMenu;
        this.window = window;
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
            this.window.close();
        });
        this.btnQuit.setOnMouseClicked((e) -> {
            this.window.close();
        });
    }

    public Optional<Client> getSelectedClient() {
        return this.selectedClient;
    }

}
