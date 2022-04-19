package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import unieuroop.controller.department.ControllerDepartment;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.staff.ControllerStaff;
import unieuroop.controller.stock.ControllerStock;
import unieuroop.model.department.Department;
import unieuroop.view.loader.Loader;
import unieuroop.view.staff.ViewEditStaff;
import unieuroop.view.staff.ViewEditStaffImpl;

public final class ViewLabelDepartmentImpl implements Initializable, ViewLabelDepartment {

    @FXML private Button btnEditProducts;
    @FXML private Button btnEditStaff;
    @FXML private Label lblDepartment;

    private final Department department;
    private final ControllerStaff controllerStaff;
    private final ControllerDepartment controllerDepartment;
    private final ControllerStock controllerStock;

    public ViewLabelDepartmentImpl(final Department department, final ControllerStaff controllerStaff,
            final ControllerDepartment controllerDepartment, final ControllerStock controllerStock) {
        this.department = department;
        this.controllerStaff = controllerStaff;
        this.controllerDepartment = controllerDepartment;
        this.controllerStock = controllerStock;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.lblDepartment.setText(this.department.toString());
    }

    @Override
    @FXML
    public void buttonEditStaffHandler(final ActionEvent event) {
        try {
            final ViewEditStaff controller = new ViewEditStaffImpl(this.controllerStaff, this.controllerDepartment, this.department);
            final Stage stage = Loader.loadStage(Pages.EDIT_STAFF_DEPARTMENTS.getPath(), "Edit Staff", controller, 500, 500);
            final Stage currentStage = (Stage) this.btnEditStaff.getScene().getWindow();
            currentStage.hide();
            stage.showAndWait();
            currentStage.show();
        } catch (IOException e) {
            final Alert alertError = new Alert(AlertType.ERROR);
            alertError.setContentText(e.getMessage());
            alertError.showAndWait();
        }
    }

    @Override
    @FXML
    public void buttonEditProductsHandler(final ActionEvent event) {
        try {
            final var controller = new ViewDepartmentEditProductsImpl(this.department, this.controllerDepartment, this.controllerStock);
            final Stage stage = Loader.loadStage(Pages.EDIT_PRODUCTS_DEPARTMENTS.getPath(), "Edit Product", controller, 500, 500);
            final Stage currentStage = (Stage) this.btnEditStaff.getScene().getWindow();
            currentStage.hide();
            stage.showAndWait();
            currentStage.show();
        } catch (IOException e) {
            final Alert alertError = new Alert(AlertType.ERROR);
            alertError.setContentText(e.getMessage());
            alertError.showAndWait();
        }
    }
}
