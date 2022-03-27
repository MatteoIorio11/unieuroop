package unieuroop.view.sale;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.util.Pair;
import unieuroop.controller.shop.ControllerShopImpl;
import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Staff;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierImpl;

public final class ViewSale implements Initializable {

    private static final String APPLE_PRODUCT="apple";
    private static final LocalTime TIME_START = LocalTime.of(1, 1);
    private static final LocalTime TIME_FINISH = LocalTime.of(1, 1);
    private static final LocalDate TIME_NOW = LocalDate.now();
    @FXML
    private ListView<Product> listProducts;
    @FXML
    private ListView<Product> listSelectedProducts;
    @FXML
    private Button btnCompleteSale;
    @FXML
    private Button btnAddToSale;
    @FXML
    private ComboBox<Department> comboDepartments;
    private final ControllerShopImpl controller = new ControllerShopImpl();
    private Supplier s1;

    private Department department1;
    private Department department2;
    private Department department3;
    private final Product p1 = new ProductImpl(1, "iphone 13 pro", this.APPLE_PRODUCT,  1200.00,  900.00, Optional.empty(), "best phone ever created", Category.SMARTPHONE, s1);
    private final Product p2 = new ProductImpl(2, "applewatch", this.APPLE_PRODUCT, 500.00,  200.00, Optional.empty(), "best watch ever created", Category.SMARTWATCH, s1);
    private final Product p3 = new ProductImpl(3, "mac book pro 14 ", this.APPLE_PRODUCT,  3000.00, 2000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p4 = new ProductImpl(4, "mac book pro 16", this.APPLE_PRODUCT,  6000.00,  3000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Staff staff1 = new Staff("Nome1", "Cognome1", this.TIME_NOW,
            0, "email1@gmail.com", 111, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final Staff staff2 = new Staff("Nome2", "Cognome2", this.TIME_NOW,
            0, "email2@gmail.csom", 222, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final Staff staff3 = new Staff("Nome3", "Cognome4", this.TIME_NOW,
            0, "email3@gmail.com", 333, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    private final Staff staff4 = new Staff("Nome4", "Cognome4", this.TIME_NOW,
            0, "email4@gmail.csom", 444, Map.of(DayOfWeek.of(1), new Pair<LocalTime, LocalTime>(TIME_START, TIME_FINISH)));
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.s1 = new SupplierImpl("supp1", Map.of(p1, 900.00, p2, 200.00, p3, 2000.00, p4, 3000.00));
        this.department1 = new DepartmentImpl("department1", Set.of(staff1, staff2, staff3, staff4), Map.of(p1, 5, p2, 1, p3, 2, p4, 2));
        this.department2 = new DepartmentImpl("department2", Set.of(staff1, staff2), Map.of(p1, 5, p4, 2));
        this.department3 = new DepartmentImpl("department3", Set.of(staff3, staff4), Map.of(p2, 1, p3, 2));
        this.controller.addDepartment(department1);
        this.controller.addDepartment(department2);
        this.controller.addDepartment(department3);
        this.comboDepartments.getItems().addAll(this.controller.getDepartments());
    }

}
