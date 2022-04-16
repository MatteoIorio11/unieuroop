package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.model.department.Department;

public class ViewDepartmentProducts implements Initializable {

    public ViewDepartmentProducts(final Department department, final ControllerStaffImpl controllerStaff,
            final ControllerDepartmentImpl controllerDepartment) {
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        
    }

    @FXML
    public void chkboxProductBoughtHandler() {
        
    }

}
