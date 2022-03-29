package unieuroop.view.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import unieuroop.controller.shop.ControllerShopImpl;
import unieuroop.view.menu.ViewMainMenu;

public class ViewClient implements Initializable{

    @FXML
    private ListView<String> listClients;
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
    private final ControllerShopImpl controller;

    public ViewClient(final ViewMainMenu view, final ControllerShopImpl controller) {
        this.viewMenu = view;
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
