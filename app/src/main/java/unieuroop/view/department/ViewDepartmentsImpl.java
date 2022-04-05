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
import javafx.util.Pair;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierImpl;

public class ViewDepartmentsImpl implements Initializable{
    @FXML
    ListView lstView_departments;
    @FXML
    ListView lstView_staff;
    @FXML
    ListView lstView_products;
    
    private static final LocalDate TIME_NOW = LocalDate.now();
    private static final LocalTime TIME_START = LocalTime.now();
    private static final LocalTime TIME_FINISH = LocalTime.of(10, 10);
    private Department department; 
    private final Staff staff1 = new Staff("Nome1", "Cognome1", ViewDepartmentsImpl.TIME_NOW,
            0, "email1@gmail.com", 1, Map.of(DayOfWeek.of(2), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final Staff staff2 = new Staff("Nome2", "Cognome2", ViewDepartmentsImpl.TIME_NOW,
            0, "email2@gmail.csom", 1, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.department = new DepartmentImpl("department1", Set.of(staff1, staff2), Map.of());        
        lstView_departments.getItems().add(this.department);
        lstView_products.getItems().addAll(this.department.getAllProducts());
       
        lstView_staff.getItems().addAll(this.department.getStaff());
    }

}
