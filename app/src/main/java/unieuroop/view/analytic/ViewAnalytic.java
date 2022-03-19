package unieuroop.view.analytic;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import unieuroop.controller.analytic.ControllerAnalytic;
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

public class ViewAnalytic implements Initializable{
    @FXML
    private PieChart chart;
    private final ControllerAnalytic controller = new ControllerAnalytic(null);
    private static final String APPLE_PRODUCT = "APPLE"; /*Brand of products*/
    private static final int TOTAL_PRODUCT_SOLD = 7;  /*all the total product sold , not the quantity*/
    private static final LocalDate TIME_NOW = LocalDate.now();

    private final Supplier s1 = new SupplierImpl("nome", Map.of());
    /**
     * ALL THE PRODUCTS THAT WILL BE USED IN THIS TEST.
     */
    private final Product p1 = new ProductImpl(1, "iphone 13 pro", ViewAnalytic.APPLE_PRODUCT,  1200.00,  900.00, Optional.empty(), "best phone ever created", Category.SMARTPHONE, s1);
    private final Product p2 = new ProductImpl(2, "applewatch", ViewAnalytic.APPLE_PRODUCT, 500.00,  200.00, Optional.empty(), "best watch ever created", Category.SMARTWATCH, s1);
    private final Product p3 = new ProductImpl(3, "mac book pro 14 ", ViewAnalytic.APPLE_PRODUCT,  3000.00, 2000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p4 = new ProductImpl(4, "mac book pro 16", ViewAnalytic.APPLE_PRODUCT,  6000.00,  3000.00, Optional.empty(), "best mac book ever created", Category.PC, s1);
    private final Product p5 = new ProductImpl(5, "ipad Air ", ViewAnalytic.APPLE_PRODUCT,  700.00,  300.00, Optional.empty(), "best ipad ever created", Category.HOME, s1);
    private final Product p6 = new ProductImpl(6, "ipad Pro", ViewAnalytic.APPLE_PRODUCT, 1000.00, 500.00, Optional.empty(), "best ipad Pro ever created", Category.HOME, s1);
    private final Product p7 = new ProductImpl(7, "ipad Pro Max", ViewAnalytic.APPLE_PRODUCT, 1200.00,  900.00, Optional.empty(), "best ipad pro max ever created", Category.HOME, s1);
    private final Product p8 = new ProductImpl(8, "ipad Pro Max v2", ViewAnalytic.APPLE_PRODUCT, 1200.00, 900.00, Optional.empty(), "best ipad pro max ever created", Category.HOME, s1);
    /**
     * ALL THE SALES THAT WILL BE USED IN THIS TEST.
     */
    private final Sale sale1 = new SaleImpl(LocalDate.of(2010, 5, 20), Map.of(p1, 10, p2, 100, p5, 1), Optional.empty());
    private final Sale sale2 = new SaleImpl(LocalDate.of(2015, 3, 20), Map.of(p1, 10, p2, 100, p5, 1, p7, 10), Optional.empty());
    private final Sale sale3 = new SaleImpl(LocalDate.of(2017, 2, 10), Map.of(p5, 10, p2, 100, p6, 1), Optional.empty());
    private final Sale sale4 = new SaleImpl(LocalDate.of(2010, 9, 20), Map.of(p3, 10, p7, 100, p1, 1), Optional.empty());
    private final Sale sale5 = new SaleImpl(LocalDate.of(2011, 5, 20), Map.of(p1, 10, p4, 100, p3, 1), Optional.empty());

    private Analytic analytic;
    private final Shop shop = new ShopImpl("TEST");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.shop.addSale(sale1);
        this.shop.addSale(sale2);
        this.shop.addSale(sale3);
        this.shop.addSale(sale4);
        this.shop.addSale(sale5);
        this.shop.addBills(ViewAnalytic.TIME_NOW, 4);
        this.shop.addBills(ViewAnalytic.TIME_NOW, 10);
        this.shop.addBills(ViewAnalytic.TIME_NOW, 2);
        analytic = new AnalyticImpl(shop);
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(this.analytic.getTotalEarned((date) -> true).entrySet().stream().map(e-> new PieChart.Data(e.getKey().toString(), e.getValue())).collect(Collectors.toList()));
                /*FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
                */
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), "\n", data.pieValueProperty(), " €"
                        )
                )
        );
        chart.setData(pieChartData);
    }
}
