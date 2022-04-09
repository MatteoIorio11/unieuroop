package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
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
    }

    @FXML
    public void listSelectDepartmentHandler(final MouseEvent event) {
        final Department selectedDepartment = this.listDepartments.getSelectionModel().getSelectedItem();
        this.listProducts.getItems().clear();
        this.listStaff.getItems().clear();
        this.listProducts.getItems().addAll(this.controllerDepartment.getProductsOf(selectedDepartment));
        this.listStaff.getItems().addAll(this.controllerDepartment.getStaffOf(selectedDepartment));
    }

    @FXML
    public void buttonAddDepartmentHandler(final ActionEvent event) {
    }

    @FXML
    public void buttonDeleteDepartmentHandler(final ActionEvent event) {
    }

    @FXML
    public void buttonMergeDepartmentsHandler(final ActionEvent event) {
    }
}
