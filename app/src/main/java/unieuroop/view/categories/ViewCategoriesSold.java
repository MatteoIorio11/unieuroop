package unieuroop.view.categories;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Multiset.Entry;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import unieuroop.controller.analytic.ControllerAnalyticImpl;
import unieuroop.model.analytic.Analytic;
import unieuroop.model.analytic.AnalyticImpl;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;
import unieuroop.model.product.ProductImpl;
import unieuroop.model.sale.Sale;
import unieuroop.model.sale.SaleImpl;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;
import unieuroop.model.supplier.Supplier;
import unieuroop.model.supplier.SupplierImpl;

public class ViewCategoriesSold implements Initializable{

    @FXML
    private BarChart<String, Integer> barCategories;
    @FXML
    private ComboBox<Category> comboCategories;

    private final Set<Category> selectedCategories = new HashSet<>();

    @FXML
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();

    private ControllerAnalyticImpl controller;
    private static final String APPLE_PRODUCT = "APPLE"; /*Brand of products*/
    private static final int TOTAL_PRODUCT_SOLD = 7;  /*all the total product sold , not the quantity*/
    private static final LocalDate TIME_NOW = LocalDate.now();

    private final Supplier s1 = new SupplierImpl("nome", Map.of());
    /**
     * ALL THE PRODUCTS THAT WILL BE USED IN THIS TEST.
     */
    private final Product p1 = new ProductImpl(1, "iphone 13 pro", this.APPLE_PRODUCT,  1200.00,  900.00, Optional.empty(), "best phone ever created", Category.SMARTPHONE, s1);
    private final Product p2 = new ProductImpl(2, "applewatch", this.APPLE_PRODUCT, 500.00,  200.00, Optional.empty(), "best watch ever created", Category.SMARTWATCH, s1);
    private final Product p3 = new ProductImpl(3, "mac book pro 14 ", this.APPLE_PRODUCT,  3000.00, 2000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p4 = new ProductImpl(4, "mac book pro 16", this.APPLE_PRODUCT,  600.00,  3000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p5 = new ProductImpl(5, "ipad Air ", this.APPLE_PRODUCT,  700.00,  300.00, Optional.empty(), "best ipad ever created", Category.HOME, s1);
    private final Product p6 = new ProductImpl(6, "ipad Pro", this.APPLE_PRODUCT, 1000.00, 500.00, Optional.empty(), "best ipad Pro ever created", Category.HOME, s1);
    private final Product p7 = new ProductImpl(7, "ipad Pro Max", this.APPLE_PRODUCT, 1200.00,  900.00, Optional.empty(), "best ipad pro max ever created", Category.HOME, s1);
    private final Product p8 = new ProductImpl(8, "ipad Pro Max v2", this.APPLE_PRODUCT, 1200.00, 900.00, Optional.empty(), "best ipad pro max ever created", Category.HOME, s1);
    /**
     * ALL THE SALES THAT WILL BE USED IN THIS TEST.
     */
    private final Sale sale1 = new SaleImpl(LocalDate.of(2010, 5, 20), Map.of(p1, 10, p2, 100, p5, 1), Optional.empty());
    private final Sale sale2 = new SaleImpl(LocalDate.of(2015, 3, 20), Map.of(p1, 10, p2, 100, p5, 1, p7, 10), Optional.empty());
    private final Sale sale3 = new SaleImpl(LocalDate.of(2017, 2, 10), Map.of(p5, 10, p2, 100, p6, 1), Optional.empty());
    private final Sale sale4 = new SaleImpl(LocalDate.of(2010, 9, 20), Map.of(p3, 10, p7, 100, p1, 1), Optional.empty());
    private final Sale sale5 = new SaleImpl(LocalDate.of(2011, 5, 20), Map.of(p1, 10, p4, 100, p3, 1), Optional.empty());
    private final Sale sale6 = new SaleImpl(LocalDate.of(2013, 1, 10), Map.of(p5, 10, p2, 100, p6, 1), Optional.empty());
    private final Sale sale7 = new SaleImpl(LocalDate.of(2022, 6, 20), Map.of(p3, 10, p7, 100, p1, 1), Optional.empty());
    private final Sale sale8 = new SaleImpl(LocalDate.of(2020, 2, 20), Map.of(p1, 10, p4, 100, p3, 1), Optional.empty());
    private final Sale sale9 = new SaleImpl(LocalDate.of(2010, 3, 20), Map.of(p1, 10, p2, 100, p5, 1), Optional.empty());
    private final Sale sale10 = new SaleImpl(LocalDate.of(2002, 2, 20), Map.of(p1, 10, p2, 100, p5, 1, p7, 10), Optional.empty());
    private final Sale sale11 = new SaleImpl(LocalDate.of(2000, 10, 10), Map.of(p5, 10, p2, 100, p6, 1), Optional.empty());
    private final Sale sale12 = new SaleImpl(LocalDate.of(2001, 4, 20), Map.of(p3, 10, p7, 100, p1, 1), Optional.empty());
    private final Sale sale13 = new SaleImpl(LocalDate.of(2022, 2, 20), Map.of(p1, 10, p4, 100, p3, 1), Optional.empty());
    private final Sale sale14 = new SaleImpl(LocalDate.of(2022, 5, 10), Map.of(p5, 10, p2, 100, p6, 1), Optional.empty());
    private final Sale sale15 = new SaleImpl(LocalDate.of(2012, 12, 20), Map.of(p3, 10, p7, 100, p1, 1), Optional.empty());
    private final Sale sale16 = new SaleImpl(LocalDate.of(2022, 11, 20), Map.of(p1, 10, p4, 100, p3, 1), Optional.empty());

    private Analytic analytic;
    private final Shop shop = new ShopImpl("TEST");

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.shop.addSale(sale1);
        this.shop.addSale(sale2);
        this.shop.addSale(sale3);
        this.shop.addSale(sale4);
        this.shop.addSale(sale5);
        this.shop.addSale(sale6);
        this.shop.addSale(sale7);
        this.shop.addSale(sale8);
        this.shop.addSale(sale9);
        this.shop.addSale(sale10);
        this.shop.addSale(sale11);
        this.shop.addSale(sale12);
        this.shop.addSale(sale13);
        this.shop.addSale(sale14);
        this.shop.addSale(sale15);
        this.shop.addSale(sale16);
        this.shop.addBills(LocalDate.of(2022, 1, 20), 46310);
        this.shop.addBills(LocalDate.of(2022, 5, 20), 10000);
        this.shop.addBills(LocalDate.now(), 14232);
        this.shop.addBills(LocalDate.of(2015, 3, 20), 2123);
        this.shop.addBills(LocalDate.of(2002, 1, 20), 10231);
        this.shop.addBills(LocalDate.of(2017, 2, 20), 1000);
        this.shop.addBills(LocalDate.now(), 14232);
        this.shop.addBills(LocalDate.of(2013, 4, 20), 2123);
        analytic = new AnalyticImpl(shop);
        this.controller = new ControllerAnalyticImpl(analytic);
        /*TENIAMO DA QUI*/
        this.comboCategories.getItems().addAll(Category.HOME, Category.PC);
        final XYChart.Series<String, Integer> serie = new XYChart.Series<>();
        serie.setName("Category");
        this.controller.getCategoriesSold().entrySet().forEach((entry) ->  serie.getData().add(new XYChart.Data<String, Integer>(entry.getKey().toString(), entry.getValue().size())));
        this.barCategories.getData().add(serie);

        this.comboCategories.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            this.selectedCategories.add(this.comboCategories.getValue());
            this.displayChart();

         });
    }

    private void displayChart() {

    }

}
