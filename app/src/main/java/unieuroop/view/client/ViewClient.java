package unieuroop.view.client;

import java.net.URL;
import java.time.LocalDate;
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
    private final Client c1 = new Client("Mario", "Rossi", LocalDate.of(2000, 1, 10), 11);
    private final Client c2 = new Client("Luigi", "Verdi", LocalDate.of(1999, 2, 15), 12);
    private final Client c3 = new Client("Marco", "Bianchi", LocalDate.of(2002, 3, 16), 13);

    public ViewClient(final ControllerClientImpl controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listClients.getItems().addAll(this.controller.getRegisteredClients());
        this.listClients.getItems().add(this.c1);
        this.listClients.getItems().add(this.c2);
        this.listClients.getItems().add(this.c3);

        btnAddClient.setOnMouseClicked((e) -> {
            try {
                this.controller.addClient(this.tbxName.getText(), this.tbxSurname.getText(), this.dtBirthday.getValue());
                this.listClients.getItems().clear();
                this.listClients.getItems().addAll(this.controller.getRegisteredClients());
            } catch (IllegalArgumentException illegalExc) {
                illegalExc.printStackTrace();
            }
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
        });

        btnDeleteClient.setOnMouseClicked((e) -> {
            final Client client = this.listClients.getSelectionModel().getSelectedItem();
            this.controller.deleteClient(client);
            this.listClients.getItems().clear();
            this.listClients.getItems().addAll(this.controller.getRegisteredClients());
        });

        listClients.setOnMouseClicked((e) -> {
            final Client client = this.listClients.getSelectionModel().getSelectedItem();
            this.tbxName.setText(client.getName());
            this.tbxSurname.setText(client.getSurname());
            this.dtBirthday.setValue(client.getBirthdayDate());
            this.lblCode.setText(Integer.toString(client.getClientCode()));
        });
    }
}
