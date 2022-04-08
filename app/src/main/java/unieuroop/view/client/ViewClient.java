package unieuroop.view.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import unieuroop.controller.client.ControllerClientImpl;
import unieuroop.model.person.Client;

public class ViewClient implements Initializable {

    @FXML
    private ListView<Client> listClients;
    @FXML 
    private TextField tbxName;
    @FXML 
    private TextField tbxSurname;
    @FXML 
    private DatePicker dtBirthday;
    @FXML 
    private Label lblCode;
    @FXML
    private Button btnAddClient;
    @FXML
    private Button btnEditClient;
    @FXML
    private Button btnDeleteClient;
    private final ControllerClientImpl controller;

    public ViewClient(final ControllerClientImpl controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listClients.getItems().addAll(this.controller.getRegisteredClients());

        btnAddClient.setOnMouseClicked((e) -> {
            try {
                this.controller.addClient(this.tbxName.getText(), this.tbxSurname.getText(), this.dtBirthday.getValue());
                this.listClients.getItems().clear();
                this.listClients.getItems().addAll(this.controller.getRegisteredClients());
            } catch (IllegalArgumentException illegalExc) {
                illegalExc.printStackTrace();
            }
            clearView();
        });

        btnEditClient.setOnMouseClicked((e) -> {
            try {
                final Client client = this.listClients.getSelectionModel().getSelectedItem();
                this.controller.editClient(this.tbxName.getText(), this.tbxSurname.getText(), this.dtBirthday.getValue());
                this.listClients.getItems().clear();
                this.controller.deleteClient(client);
                this.listClients.getItems().addAll(this.controller.getRegisteredClients());
            } catch (IllegalArgumentException illegalExc) {
                illegalExc.printStackTrace();
            }
            clearView();
        });

        btnDeleteClient.setOnMouseClicked((e) -> {
            final Client client = this.listClients.getSelectionModel().getSelectedItem();
            this.controller.deleteClient(client);
            this.listClients.getItems().clear();
            this.listClients.getItems().addAll(this.controller.getRegisteredClients());
            clearView();
        });

        listClients.setOnMouseClicked((e) -> {
            final Client client = this.listClients.getSelectionModel().getSelectedItem();
            this.tbxName.setText(client.getName());
            this.tbxSurname.setText(client.getSurname());
            this.dtBirthday.setValue(client.getBirthdayDate());
            this.lblCode.setText(Integer.toString(client.getClientCode()));
        });
    }

    private void clearView() {
        this.tbxName.setText("");
        this.tbxSurname.setText("");
        this.dtBirthday.setValue(null);
        this.lblCode.setText("");
    }
}
