package unieuroop.view.staff;

import java.net.URL;
import java.time.DayOfWeek;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

}
