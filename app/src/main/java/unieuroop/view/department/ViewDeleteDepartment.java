package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.model.department.Department;

public final class ViewDeleteDepartment implements Initializable {

    @FXML private ListView<Department> listDepartments;

    private final ControllerDepartmentImpl controllerDepartment;
    public ViewDeleteDepartment(final ControllerDepartmentImpl controllerDepartment) {
        this.controllerDepartment = controllerDepartment;
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listDepartments.getItems().addAll(this.controllerDepartment.getDepartments());
    }

    @FXML
    public void listDeleteDepartment(final MouseEvent event) {
        final var department = this.listDepartments.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(department)) {
            final Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Are you sure to delete the selected Department?");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                try {
                    this.controllerDepartment.removeDepartment(department);
                    final Stage stage = (Stage) this.listDepartments.getScene().getWindow();
                    stage.close();
                } catch (IOException e) {
                    final Alert alertError = new Alert(AlertType.ERROR);
                    alertError.setContentText(e.getMessage());
                    alertError.showAndWait();
                }
            }
        }
    }
}
