package unieuroop.view.client;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import unieuroop.controller.client.ControllerClient;
import unieuroop.model.person.Client;
import unieuroop.model.person.ClientImpl;

public final class ViewClientImpl implements Initializable, ViewClient {

    @FXML private ListView<Client> listClients;
    @FXML private TextField tbxName;
    @FXML private TextField tbxSurname;
    @FXML private DatePicker dtBirthday;
    @FXML private Label lblCode;
    @FXML private Button btnAddClient;
    @FXML private Button btnEditClient;
    @FXML private Button btnDeleteClient;
    private final ControllerClient controller;

    public ViewClientImpl(final ControllerClient controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listClients.getItems().addAll(this.controller.getRegisteredClients());
    }

    @Override
    @FXML
    public void btnAddClientHandler() {
        try {
            this.controller.addClient(this.tbxName.getText(), this.tbxSurname.getText(), this.dtBirthday.getValue());
            this.listClients.getItems().clear();
            this.listClients.getItems().addAll(this.controller.getRegisteredClients());
        } catch (IllegalArgumentException illegalExc) {
            setAlert();
            illegalExc.printStackTrace();
        }
        clearView();
    }

    @Override
    @FXML
    public void btnEditClientHandler() {
        try {
            final Client client = this.listClients.getSelectionModel().getSelectedItem();
            this.controller.editClient(this.tbxName.getText(), this.tbxSurname.getText(), this.dtBirthday.getValue(), client);
            listClients.refresh();
        } catch (IllegalArgumentException illegalExc) {
            setAlert();
            illegalExc.printStackTrace();
        }
        clearView();
    }

    @Override
    @FXML
    public void listClientHandler() {
        final Client client = this.listClients.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(client)) {
            this.tbxName.setText(client.getPerson().getName());
            this.tbxSurname.setText(client.getPerson().getSurname());
            this.dtBirthday.setValue(client.getPerson().getBirthdayDate());
            this.lblCode.setText(Integer.toString(client.getPerson().getCode()));
        }
    }

    @Override
    @FXML 
    public void btnDeleteClientHandler() {
        final Client client = this.listClients.getSelectionModel().getSelectedItem();
        this.controller.deleteClient(client);
        this.listClients.getItems().clear();
        this.listClients.getItems().addAll(this.controller.getRegisteredClients());
        clearView();
    }

    private void setAlert() {
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Client error: ");
        alert.setContentText("Impossible because one of the parameters are null");
        alert.showAndWait();
    }

    private void clearView() {
        this.tbxName.setText("");
        this.tbxSurname.setText("");
        this.dtBirthday.setValue(null);
        this.lblCode.setText("");
    }
}
