package unieuroop.view.staff;

import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import unieuroop.controller.staff.ControllerStaff;
import unieuroop.model.person.Staff;

public final class ViewStaffImpl implements Initializable, ViewStaff {

    @FXML private ListView<Staff> listStaffs;
    @FXML private TextField tbxName;
    @FXML private TextField tbxSurname;
    @FXML private DatePicker dtBirthday;
    @FXML private Label lblId;
    @FXML private TextField tbxEmail;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tbxHoursStartTime;
    @FXML private TextField tbxMinutesStartTime;
    @FXML private TextField tbxHoursEndTime;
    @FXML private TextField tbxMinutesEndTime;
    @FXML private Button btnAddStaff;
    @FXML private Button btnEditStaff;
    @FXML private Button btnDeleteStaff;
    private final ControllerStaff controller;

    public ViewStaffImpl(final ControllerStaff controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listStaffs.getItems().addAll(this.controller.getStaff());
    }

    @Override
    @FXML
    public void buttonAddHandler(final ActionEvent event) {
        try {
            this.controller.addStaff(this.tbxName.getText(), this.tbxSurname.getText(), this.dtBirthday.getValue(), this.tbxEmail.getText(), this.pfPassword.getText(), 
                    this.tbxHoursStartTime.getText(), this.tbxMinutesStartTime.getText(), this.tbxHoursEndTime.getText(), this.tbxMinutesEndTime.getText());
            this.listStaffs.getItems().clear();
            this.listStaffs.getItems().addAll(this.controller.getStaff());
        } catch (IllegalArgumentException illegalExc) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(illegalExc.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        this.clearView();
    }

    @Override
    @FXML
    public void buttonEditHandler(final ActionEvent event) {
        try {
            final Staff staff = this.listStaffs.getSelectionModel().getSelectedItem();
            this.controller.editStaff(this.tbxName.getText(), this.tbxSurname.getText(), this.dtBirthday.getValue(), this.tbxEmail.getText(), this.pfPassword.getText(),
                    this.tbxHoursStartTime.getText(), this.tbxMinutesStartTime.getText(), this.tbxHoursEndTime.getText(), this.tbxMinutesEndTime.getText(), staff);
            this.listStaffs.refresh();
        } catch (IllegalArgumentException illegalExc) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(illegalExc.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        this.clearView();
    }

    @Override
    @FXML
    public void buttonDeleteHandler(final ActionEvent event) {
        try {
            final Staff staff = this.listStaffs.getSelectionModel().getSelectedItem();
            this.controller.deleteStaff(staff);
            this.listStaffs.getItems().clear();
            this.listStaffs.getItems().addAll(this.controller.getStaff());
            this.clearView();
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @Override
    @FXML
    public void listSelectHandler(final MouseEvent event) {
        final Staff staff = this.listStaffs.getSelectionModel().getSelectedItem();
        this.tbxName.setText(staff.getName());
        this.tbxSurname.setText(staff.getSurname());
        this.dtBirthday.setValue(staff.getBirthdayDate());
        this.lblId.setText(staff.getId().toString());
        this.tbxEmail.setText(staff.getEmail());
        this.pfPassword.setText(staff.getPassword().toString());
        this.tbxHoursStartTime.setText(Integer.toString(staff.getWorkTime(DayOfWeek.MONDAY).getKey().getHour()));
        this.tbxMinutesStartTime.setText(Integer.toString(staff.getWorkTime(DayOfWeek.MONDAY).getKey().getMinute()));
        this.tbxHoursEndTime.setText(Integer.toString(staff.getWorkTime(DayOfWeek.MONDAY).getValue().getHour()));
        this.tbxMinutesEndTime.setText(Integer.toString(staff.getWorkTime(DayOfWeek.MONDAY).getValue().getMinute()));
    }

    private void clearView() {
        this.tbxName.setText("");
        this.tbxSurname.setText("");
        this.dtBirthday.setValue(null);
        this.lblId.setText("");
        this.tbxEmail.setText("");
        this.pfPassword.setText("");
        this.tbxHoursStartTime.setText("");
        this.tbxMinutesStartTime.setText("");
        this.tbxHoursEndTime.setText("");
        this.tbxMinutesEndTime.setText("");
    }
}
