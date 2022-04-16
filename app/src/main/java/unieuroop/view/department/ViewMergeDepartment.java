package unieuroop.view.department;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import unieuroop.model.department.Department;

public class ViewMergeDepartment {
    @FXML private ListView<Department> listLeftDepartment;
    @FXML private ListView<Department> listRightDepartment;
    @FXML private TextField textName;
    @FXML private Button buttonMergeDepartment;

}
