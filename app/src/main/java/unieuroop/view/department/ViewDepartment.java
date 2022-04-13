package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.model.department.Department;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;
import unieuroop.view.loader.Loader;

public final class ViewDepartment implements Initializable {
    @FXML private ListView<Pane> listDepartments;
    @FXML private ListView<Staff> listStaff;
    @FXML private ListView<Product> listProducts;

    private final Map<Pane, Department> departmentPane = new HashMap<>();
    private final ControllerDepartmentImpl controllerDepartment;
    private final ControllerStaffImpl controllerStaff;
    public ViewDepartment(final ControllerDepartmentImpl controllerDepartment, final ControllerStaffImpl controllerStaff) {
        this.controllerDepartment = controllerDepartment;
        this.controllerStaff = controllerStaff;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.populateList();
    }

    @FXML
    public void listSelectDepartmentHandler(final MouseEvent event) {
        final Pane pane = this.listDepartments.getSelectionModel().getSelectedItem();
        final Department selectedDepartment = this.departmentPane.get(pane);
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

    private void populateList() {
        for (final Department department : this.controllerDepartment.getDepartments()) {
            try {
                final Pane pane = Loader.<ViewLabelDepartment>loadPane(Pages.PROTOTYPE_LABEL.getPath(), 
                        new ViewLabelDepartment(department, this.controllerStaff, this.controllerDepartment));
                this.listDepartments.getItems().add(pane);
                this.departmentPane.put(pane, department);
            } catch (IOException e) {
                final Alert errorMessage = new Alert(AlertType.ERROR);
                errorMessage.setContentText(e.getMessage());
                errorMessage.showAndWait();
            }
        }
    }

}
