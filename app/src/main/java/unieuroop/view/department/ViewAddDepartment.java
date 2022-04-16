package unieuroop.view.department;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.model.person.Staff;

public class ViewAddDepartment {

    @FXML private ListView<Staff> listStaff;
    public ViewAddDepartment(final ControllerDepartmentImpl controllerDepartment, final ControllerStaffImpl controllerStaff,
            final ControllerStockImpl controllerStock) {
    }

}
