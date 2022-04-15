package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.model.department.Department;

public class ViewLabelDepartment implements Initializable {

    @FXML
    private Button btnEditProducts;
    @FXML
    private Button btnEditStaff;
    @FXML
    private Label lblDepartment;

    private final Department department;
    private final ControllerStaffImpl controllerStaff;
    private final ControllerDepartmentImpl controllerDepartment;

    public ViewLabelDepartment(final Department department, final ControllerStaffImpl controllerStaff, final ControllerDepartmentImpl controllerDepartment) {
        this.department = department;
        this.controllerStaff = controllerStaff;
        this.controllerDepartment = controllerDepartment;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.lblDepartment.setText(this.department.toString());
    }

    @FXML
    public void buttonEditStaffHandler(final ActionEvent event) {
        
    }

    @FXML
    public void buttonEditProductsHandler(final ActionEvent event) {
        
    }
}
