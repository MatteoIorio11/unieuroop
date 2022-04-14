package unieuroop.view.staff;

import java.net.URL;
import java.time.DayOfWeek;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.model.person.Staff;

public final class ViewStaffImpl implements Initializable {

    @FXML
    private ListView<Staff> listStaffs;
    @FXML 
    private TextField tbxName;
    @FXML 
    private TextField tbxSurname;
    @FXML 
    private DatePicker dtBirthday;
    @FXML
    private TextField tbxEmail;
    @FXML
    private TextField tbxPassword;
    @FXML
    private ComboBox<DayOfWeek> cbxDayOfWeek;
    @FXML
    private CheckBox chxNotWorked;
    @FXML
    private TextField tbxStartTime;
    @FXML
    private TextField tbxEndTime;
    @FXML
    private Button btnAddStaff;
    @FXML
    private Button btnEditStaff;
    @FXML
    private Button btnDeleteStaff;
    private final ControllerStaffImpl controller;

    public ViewStaffImpl(final ControllerStaffImpl controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listStaffs.getItems().addAll(this.controller.getStaff());

        btnAddStaff.setOnMouseClicked(e -> {
            
        });

        btnAddStaff.setOnMouseClicked(e -> {
            
        });

        btnAddStaff.setOnMouseClicked(e -> {
            
        });
    }

    private void setAlert() {
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Staff error: ");
        alert.setContentText("Impossible because one of the parameters are null");
        alert.showAndWait();
    }

    private void clearView() {
        this.tbxName.setText("");
        this.tbxSurname.setText("");
        this.dtBirthday.setValue(null);
        this.tbxEmail.setText("");
        this.tbxPassword.setText("");
        this.chxNotWorked.setSelected(false);
        this.tbxStartTime.setText("");
        this.tbxEndTime.setText("");
    }
}
