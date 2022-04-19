package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import unieuroop.controller.department.ControllerDepartment;
import unieuroop.controller.serialization.Pages;
import unieuroop.model.department.Department;
import unieuroop.view.loader.Loader;

public final class ViewMergeDepartmentImpl implements Initializable, ViewMergeDepartment {
    @FXML private ListView<Pane> listDepartments;
    @FXML private TextField textName;
    @FXML private Button buttonMergeDepartment;

    private final Map<Pane, ViewMergeLabelImpl> paneLabel = new HashMap<>();
    private final ControllerDepartment controllerDepartment;

    public ViewMergeDepartmentImpl(final ControllerDepartment controllerDepartment) {
        this.controllerDepartment = controllerDepartment;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.controllerDepartment.getDepartments().stream().forEach(d -> {
            try {
                final var view = new ViewMergeLabelImpl(d);
                final var pane = Loader.<ViewMergeLabel>loadPane(Pages.LABEL_MERGE_DEPARTMENT.getPath(), view);
                this.paneLabel.put(pane,  view);
                this.listDepartments.getItems().add(pane);
            } catch (IOException e) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });
    }

    @Override
    @FXML
    public void buttonMergeDepartmentHandler(final ActionEvent event) {
        try {
            final Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Are you sure to merge the selected department" + "\n" 
            + this.getSelectedDepartments());
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                this.controllerDepartment.mergeDepartments(this.getSelectedDepartments(), this.textName.getText());
                final Stage stage = (Stage) this.buttonMergeDepartment.getScene().getWindow();
                stage.close();
            }
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

    /**
     * 
     * @return
     */
    private Set<Department> getSelectedDepartments() {
        return this.listDepartments.getItems().stream()
        .map(d -> this.paneLabel.get(d))
        .filter(d -> d.isSelected())
        .map(l -> l.getDepartment())
        .collect(Collectors.toSet());
    }
}
