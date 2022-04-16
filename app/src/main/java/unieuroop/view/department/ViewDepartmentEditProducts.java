package unieuroop.view.department;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.model.department.Department;
import unieuroop.model.product.Product;

public class ViewDepartmentEditProducts implements Initializable {

    @FXML
    private Label lblDepartmentName;
    @FXML
    private ListView<Product> listProductsDepartment;
    @FXML
    private TextArea txtAreaInfoProducts;
    @FXML
    private Button btnAddProducts;
    @FXML
    private Button btnRemoveProducts;

    private final Department department;
    private final ControllerStaffImpl controllerStaff;
    private final ControllerDepartmentImpl controllerDepartment;

    public ViewDepartmentEditProducts(final Department currentDepartment, final ControllerStaffImpl controllerStaff, final ControllerDepartmentImpl controllerDepartment) {
        this.department = currentDepartment;
        this.controllerStaff = controllerStaff;
        this.controllerDepartment = controllerDepartment;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    @FXML
    public void listProductsDepartmentHandler() {
        
    }

    @FXML
    public void btnAddProductsHandler() {
        
    }

    @FXML
    public void btnRemoveProductsHandler() {
        
    }

}
