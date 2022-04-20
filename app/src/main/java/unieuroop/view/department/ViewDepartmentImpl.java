package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
import unieuroop.controller.department.ControllerDepartment;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.staff.ControllerStaff;
import unieuroop.controller.stock.ControllerStock;
import unieuroop.model.department.Department;
import unieuroop.model.person.StaffImpl;
import unieuroop.model.product.Product;
import unieuroop.view.loader.Loader;

public final class ViewDepartmentImpl implements Initializable, ViewDepartment {

    @FXML private ListView<Pane> listDepartments;
    @FXML private ListView<StaffImpl> listStaff;
    @FXML private ListView<Product> listProducts;
    @FXML private Button btnAddDepartment;
    @FXML private Button btnMergeDepartments;
    @FXML private Button btnDeleteDepartment;

    private final Map<Pane, Department> departmentPane = new HashMap<>();
    private final ControllerDepartment controllerDepartment;
    private final ControllerStaff controllerStaff;
    private final ControllerStock controllerStock;

    public ViewDepartmentImpl(final ControllerDepartment controllerDepartment, final ControllerStaff controllerStaff, final ControllerStock controllerStock) {
        this.controllerDepartment = controllerDepartment;
        this.controllerStaff = controllerStaff;
        this.controllerStock = controllerStock;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.populateList();
    }

    @Override
    @FXML
    public void listSelectDepartmentHandler(final MouseEvent event) {
        final var pane = this.listDepartments.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(pane)) {
            final Department selectedDepartment = this.departmentPane.get(pane);
            this.listProducts.getItems().clear();
            this.listStaff.getItems().clear();
            this.listProducts.getItems().addAll(this.controllerDepartment.getProductsOf(selectedDepartment));
            this.listStaff.getItems().addAll(this.controllerDepartment.getStaffOf(selectedDepartment));
        }
    }

    @Override
    @FXML
    public void buttonAddDepartmentHandler(final ActionEvent event) {
        try {
            final ViewAddDepartment controller = new ViewAddDepartmentImpl(this.controllerDepartment, this.controllerStaff, this.controllerStock);
            final Stage stage = Loader.loadStage(Pages.ADD_DEPARTMENTS.getPath(), "Add Department", controller, 500, 500);
            final Stage currentStage = (Stage) this.btnAddDepartment.getScene().getWindow();
            currentStage.hide();
            stage.setOnCloseRequest((closeEvent) -> {
                closeEvent.consume();
            });
            stage.showAndWait();
            currentStage.show();
            this.listDepartments.getItems().clear();
            this.populateList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @FXML
    public void buttonDeleteDepartmentHandler(final ActionEvent event) {
        final var controller = new ViewDeleteDepartmentImpl(this.controllerDepartment);
        try {
            final Stage stage = Loader.loadStage(Pages.DELETE_DEPARTMENTS.getPath(), "Delete Department", controller, 500, 500);
            final Stage currentStage = (Stage) this.btnDeleteDepartment.getScene().getWindow();
            currentStage.hide();
            stage.showAndWait();
            currentStage.show();
            this.listDepartments.getItems().clear();
            this.populateList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @FXML
    public void buttonMergeDepartmentsHandler(final ActionEvent event) {
        final var controller = new ViewMergeDepartmentImpl(this.controllerDepartment);
        try {
            final Stage stage = Loader.loadStage(Pages.MERGE_DEPARTMENTS.getPath(), "Merge Department", controller, 500, 500);
            final Stage currentStage = (Stage) this.btnDeleteDepartment.getScene().getWindow();
            currentStage.hide();
            stage.showAndWait();
            currentStage.show();
            this.listDepartments.getItems().clear();
            this.populateList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    private void populateList() {
        this.departmentPane.clear();
        for (final Department department : this.controllerDepartment.getDepartments()) {
            try {
                final Pane pane = Loader.<ViewLabelDepartment>loadPane(Pages.DEPARTMENTS_LABEL.getPath(), 
                        new ViewLabelDepartmentImpl(department, this.controllerStaff, this.controllerDepartment, this.controllerStock));
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
