package unieuroop.view.client;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import unieuroop.controller.client.ControllerClientImpl;
import unieuroop.model.person.Client;
import unieuroop.view.menu.ViewMainMenu;

public class ViewClient implements Initializable {

    @FXML
    private ListView<Client> listClients;
    @FXML 
    private TextField name;
    @FXML 
    private TextField surname;
    @FXML 
    private TextField birthday;
    @FXML 
    private TextField code;
    @FXML
    private Button btnAddClient;
    @FXML
    private Button btnEditClient;
    @FXML
    private Button btnDeleteClient;
    private final ViewMainMenu viewMenu;
    private final ControllerClientImpl controller;
    private final Client c1 = new Client("Mario", "Rossi", LocalDate.of(2000, 1, 10), Optional.of(10001));
    private final Client c2 = new Client("Luigi", "Verdi", LocalDate.of(1999, 2, 15), Optional.of(10002));
    private final Client c3 = new Client("Marco", "Bianchi", LocalDate.of(2002, 3, 16), Optional.empty());

    public ViewClient(final ViewMainMenu view, final ControllerClientImpl controller) {
        this.viewMenu = view;
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    @FXML
    private void btnAddHandler(final ActionEvent event) {
        
    }

    private void btnEditClientHandler(final ActionEvent event) {
        
    }

    private void btnDeleteClient(final ActionEvent event) {
        
    }
}
