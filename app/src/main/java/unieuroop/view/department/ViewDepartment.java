package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.model.department.Department;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;

public final class ViewDepartment implements Initializable {
    @FXML
    private ListView<Department> listDepartments;
    @FXML
    private ListView<Staff> listStaff;
    @FXML
    private ListView<Product> listProducts;

    private final ControllerDepartmentImpl controllerDepartment;
    private final ControllerStaffImpl controllerStaff;
    public ViewDepartment(final ControllerDepartmentImpl controllerDepartment, final ControllerStaffImpl controllerStaff) {
        this.controllerDepartment = controllerDepartment;
        this.controllerStaff = controllerStaff;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
       this.listDepartments.getItems().addAll(this.controllerDepartment.getDepartments());
       this.listStaff.getItems().addAll(this.controllerStaff.getStaff());

       this.listDepartments.getSelectionModel().selectedItemProperty().addListener((e) -> {
           this.listProducts.getItems().addAll(this.controllerDepartment.getProductsOf(this.listDepartments.getSelectionModel()
                   .getSelectedItem()));
       });
    }

}
