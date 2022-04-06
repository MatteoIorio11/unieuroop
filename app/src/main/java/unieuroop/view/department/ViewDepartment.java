package unieuroop.view.department;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Pair;
import unieuroop.controller.department.ControllerDepartmentImpl;
import unieuroop.controller.staff.ControllerStaffImpl;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierImpl;

public final class ViewDepartment implements Initializable{
    @FXML
    private ListView<Department> listDepartments;
    @FXML
    private ListView<Staff> listStaff;
    @FXML
    private ListView<Product> listProducts;

    private final ControllerDepartmentImpl controllerDepartment;
    private final ControllerStaffImpl controllerStaff;
    private final Stage stage;
    public ViewDepartment(final ControllerDepartmentImpl controllerDepartment, final ControllerStaffImpl controllerStaff,
            final Stage primaryStage) {
        this.controllerDepartment = controllerDepartment;
        this.controllerStaff = controllerStaff;
        this.stage = primaryStage;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
       this.listDepartments.getItems().addAll(this.controllerDepartment.getDepartments());
       this.listStaff.getItems().addAll(this.controllerStaff.getStaff());

       this.listDepartments.getSelectionModel().selectedItemProperty().addListener((e) -> {
           this.listProducts.getItems().addAll(this.controllerDepartment.getProductsOf(this.listDepartments.getSelectionModel()
                   .getSelectedItem()));
       });
    }

}
