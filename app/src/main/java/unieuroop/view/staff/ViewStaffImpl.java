package unieuroop.view.staff;

import java.awt.Button;
import java.awt.Checkbox;
import java.net.URL;
import java.time.DayOfWeek;
import java.util.ResourceBundle;

import com.google.errorprone.annotations.FormatMethod;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private Checkbox chxNotWorked;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

}
