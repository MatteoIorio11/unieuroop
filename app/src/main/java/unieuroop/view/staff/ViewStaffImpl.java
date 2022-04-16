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
    private TextField tbxId;
    @FXML
    private TextField tbxEmail;
    @FXML
    private TextField tbxPassword;
    @FXML
    private TextField tbxHoursStartTime;
    @FXML
    private TextField tbxMinutesStartTime;
    @FXML
    private TextField tbxHoursEndTime;
    @FXML
    private TextField tbxMinutesEndTime;
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
            try {
                this.controller.addStaff(this.tbxName.getText(), this.tbxSurname.getText(), this.dtBirthday.getValue(), this.tbxId.getText(), this.tbxEmail.getText(), this.tbxPassword.getText(), 
                        this.tbxHoursStartTime.getText(), this.tbxMinutesStartTime.getText(), this.tbxHoursEndTime.getText(), this.tbxMinutesEndTime.getText());
                this.listStaffs.getItems().clear();
                this.listStaffs.getItems().addAll(this.controller.getStaff());
            } catch (IllegalArgumentException illegalExc) {
                setAlert();
                illegalExc.printStackTrace();
            }
            clearView();
        });

        btnEditStaff.setOnMouseClicked(e -> {
            try {
                final Staff staff = this.listStaffs.getSelectionModel().getSelectedItem();
                this.controller.editStaff(this.tbxName.getText(), this.tbxSurname.getText(), this.dtBirthday.getValue(), this.tbxId.getText(), this.tbxEmail.getText(), this.tbxPassword.getText(),
                        this.tbxHoursStartTime.getText(), this.tbxMinutesStartTime.getText(), this.tbxHoursEndTime.getText(), this.tbxMinutesEndTime.getText(), staff);
                listStaffs.refresh();
            } catch (IllegalArgumentException illegalExc) {
                setAlert();
                illegalExc.printStackTrace();
            }
            clearView();
        });

        btnDeleteStaff.setOnMouseClicked(e -> {
            final Staff staff = this.listStaffs.getSelectionModel().getSelectedItem();
            this.controller.deleteStaff(staff);
            this.listStaffs.getItems().clear();
            this.listStaffs.getItems().addAll(this.controller.getStaff());
            clearView();
        });

        listStaffs.setOnMouseClicked(e -> {
            final Staff staff = this.listStaffs.getSelectionModel().getSelectedItem();
            this.tbxName.setText(staff.getName());
            this.tbxSurname.setText(staff.getSurname());
            this.dtBirthday.setValue(staff.getBirthdayDate());
            this.tbxId.setText(staff.getId().toString());
            this.tbxEmail.setText(staff.getEmail());
            this.tbxPassword.setText(staff.getPassword().toString());
            this.tbxHoursStartTime.setText("");
            this.tbxMinutesStartTime.setText("");
            this.tbxHoursEndTime.setText("");
            this.tbxMinutesEndTime.setText("");
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
        this.tbxId.setText("");
        this.tbxEmail.setText("");
        this.tbxPassword.setText("");
        this.tbxHoursStartTime.setText("");
        this.tbxMinutesStartTime.setText("");
        this.tbxHoursEndTime.setText("");
        this.tbxMinutesEndTime.setText("");
    }
}
