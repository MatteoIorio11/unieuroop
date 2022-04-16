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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.model.department.Department;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Product;
import unieuroop.view.loader.Loader;

public final class ViewDepartment implements Initializable {

    @FXML private ListView<Pane> listDepartments;
    @FXML private ListView<Staff> listStaff;
    @FXML private ListView<Product> listProducts;
    @FXML private Button btnAddDepartment;
    @FXML private Button btnMergeDepartments;
    @FXML private Button btnDeleteDepartment;

    private final Map<Pane, Department> departmentPane = new HashMap<>();
    private final ControllerDepartmentImpl controllerDepartment;
    private final ControllerStaffImpl controllerStaff;
    private final ControllerStockImpl controllerStock;

    public ViewDepartment(final ControllerDepartmentImpl controllerDepartment, final ControllerStaffImpl controllerStaff, final ControllerStockImpl controllerStock) {
        this.controllerDepartment = controllerDepartment;
        this.controllerStaff = controllerStaff;
        this.controllerStock = controllerStock;
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
        try {
            final var controller = new ViewAddDepartment(this.controllerDepartment, this.controllerStaff, this.controllerStock);
            final Stage stage = Loader.loadStage(Pages.ADD_DEPARTMENTS.getPath(), "Add Department", controller, 500, 500);
            final Stage currentStage = (Stage) this.btnAddDepartment.getScene().getWindow();
            currentStage.hide();
            stage.setOnCloseRequest((closeEvent) -> {
                closeEvent.consume();
            });
            stage.showAndWait();
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                final Pane pane = Loader.<ViewLabelDepartment>loadPane(Pages.DEPARTMENTS_LABEL.getPath(), 
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
