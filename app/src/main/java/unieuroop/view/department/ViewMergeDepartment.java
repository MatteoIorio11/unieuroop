package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.model.department.Department;

public final class ViewMergeDepartment implements Initializable {
    @FXML private ListView<Department> listDepartments;
    @FXML private TextField textName;
    @FXML private Button buttonMergeDepartment;

    private final ControllerDepartmentImpl controllerDepartment;
    private final Set<Department> selectedDepartments = new HashSet<>();
    public ViewMergeDepartment(final ControllerDepartmentImpl controllerDepartment) {
        this.controllerDepartment = controllerDepartment;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listDepartments.getItems().addAll(this.controllerDepartment.getDepartments());
    }

    @FXML
    public void listSelectDepartmentHandler(final MouseEvent event) {
        this.selectedDepartments.add(this.listDepartments.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void buttonMergeDepartmentHandler(final ActionEvent event) {
        try {
            this.controllerDepartment.mergeDepartments(this.selectedDepartments, this.textName.getText());
            final Stage stage = (Stage) this.buttonMergeDepartment.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (IllegalArgumentException ex) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}
