package unieuroop.view.department;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import unieuroop.controller.department.ControllerDepartment;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.staff.ControllerStaff;
import unieuroop.controller.stock.ControllerStock;
import unieuroop.model.person.Staff;
import unieuroop.view.loader.Loader;

public final class ViewAddDepartmentImpl implements Initializable, ViewAddDepartment {

    @FXML private ListView<Staff> listStaff;
    @FXML private ListView<Pane> listProducts;
    @FXML private TextField textName;
    @FXML private Button buttonExit;
    @FXML private Button buttonAdd;

    private final ControllerDepartment controllerDepartment;
    private final ControllerStaff controllerStaff;
    private final ControllerStock controllerStock;
    private final Set<Staff> selectedStaff = new HashSet<>();

    public ViewAddDepartmentImpl(final ControllerDepartment controllerDepartment, final ControllerStaff controllerStaff,
            final ControllerStock controllerStock) {
        this.controllerDepartment = controllerDepartment;
        this.controllerStaff = controllerStaff;
        this.controllerStock = controllerStock;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listStaff.getItems().addAll(this.controllerStaff.getUnsignedStaff());
        this.loadProducts();
    }

    @Override
    @FXML
    public void buttonAddHandler(final ActionEvent event) {
        try {
            final Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Resume of the new Department : \n"
            + this.controllerDepartment.getReservedProducts() + "\n" 
            + this.selectedStaff + "\n"
            + "Are you done on add elements in the staff?");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                this.controllerDepartment.addDepartment(this.textName.getText(), this.selectedStaff, this.controllerDepartment.getReservedProducts());
                this.controllerDepartment.closeAddProducts();
                final Stage stage = (Stage) this.buttonAdd.getScene().getWindow();
                stage.close();
            }
        } catch (IllegalArgumentException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    @FXML
    public void buttonExitHandler(final ActionEvent event) {
        this.controllerDepartment.removeAllReservedProducts();
        final Stage stage = (Stage) this.buttonExit.getScene().getWindow();
        stage.close();
    }

    @Override
    @FXML
    public void listSelectStaffHandler(final MouseEvent event) {
        this.selectedStaff.add(this.listStaff.getSelectionModel().getSelectedItem());
    }

    /**
     * 
     */
    private void loadProducts() {
        for (final var product : this.controllerStock.getProductsStocked().entrySet()) {
            try {
                final ViewDepartmentLabelProduct controller = new ViewDepartmentLabelProductImpl(this.controllerDepartment, product.getKey(), product.getValue());
                final Pane pane = Loader.loadPane(Pages.LABEL_PRODUCT.getPath(), controller);
                this.listProducts.getItems().add(pane);
            } catch (IOException e) {
                final Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
