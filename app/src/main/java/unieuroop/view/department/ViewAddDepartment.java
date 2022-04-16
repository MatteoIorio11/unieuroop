package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.serialization.Pages;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.controller.stock.ControllerStockImpl;
import unieuroop.model.person.Staff;
import unieuroop.view.loader.Loader;

public class ViewAddDepartment implements Initializable {

    @FXML private ListView<Staff> listStaff;
    @FXML private ListView<Pane> listProducts;
    @FXML private TextField textName;
    @FXML private Button buttonExit;
    @FXML private Button buttonAdd;

    private final ControllerDepartmentImpl controllerDepartment;
    private final ControllerStaffImpl controllerStaff;
    private final ControllerStockImpl controllerStock;
    public ViewAddDepartment(final ControllerDepartmentImpl controllerDepartment, final ControllerStaffImpl controllerStaff,
            final ControllerStockImpl controllerStock) {
        this.controllerDepartment = controllerDepartment;
        this.controllerStaff = controllerStaff;
        this.controllerStock = controllerStock;
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.listStaff.getItems().addAll(this.controllerStaff.getUnsignedStaff());
        this.loadProducts();
    }
    private void loadProducts() {
        for (final var product : this.controllerStock.getProductsStocked().entrySet()) {
            final Pane pane = Loader.loadPane(Pages.LABEL_PRODUCT, new ViewDepartmentLabelProduct);
        }
    }

}
