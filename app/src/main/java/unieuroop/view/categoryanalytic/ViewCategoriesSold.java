package unieuroop.view.categoryanalytic;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import unieuroop.controller.analytic.ControllerAnalytic;
import unieuroop.model.product.Category;

public final class ViewCategoriesSold implements Initializable {

    @FXML private BarChart<String, Integer> barCategories;
    @FXML private BarChart<String, Integer> barProductsSold;
    @FXML private ComboBox<Category> comboCategories;
    @FXML private final CategoryAxis xAxis = new CategoryAxis();
    @FXML private final NumberAxis yAxis = new NumberAxis();
    @FXML private ListView<String> listLegend;
    @FXML private ListView<String> listSelectedCategories;
    private final Set<Category> selectedCategories = new HashSet<>();
    private final ControllerAnalytic controller;
    public ViewCategoriesSold(final ControllerAnalytic controller) {
        this.controller = controller;
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.barCategories.setLegendVisible(false);
        this.barProductsSold.setLegendVisible(false);

        this.comboCategories.getItems().addAll(this.controller.getCategoriesSold().keySet());

        final XYChart.Series<String, Integer> serie = new XYChart.Series<>();
        this.controller.getCategoriesSold().entrySet().stream()
            .sorted((entry1, entry2) -> entry1.getKey().getName().compareTo(entry2.getKey().getName()))
            .forEach((entry) ->  serie.getData().add(new XYChart.Data<String, Integer>(entry.getKey().toString(), entry.getValue())));
        this.barCategories.getData().add(serie);
    }

    @FXML
    public void buttonClearEvent(final ActionEvent event) {
        this.selectedCategories.clear();
        this.barProductsSold.getData().clear();
        this.listLegend.getItems().clear();
        this.listSelectedCategories.getItems().clear();
    }

    @FXML
    public void comboSelectCategoryHandler(final ActionEvent event) {
        if (!this.selectedCategories.contains(this.comboCategories.getValue())) {
            this.selectedCategories.add(this.comboCategories.getValue());
            this.displayChart();
        }
    }

    @FXML
    public void listSelectValueHandler(final MouseEvent event) {
        final var string = this.listLegend.getSelectionModel().getSelectedItem();
        if (string != null) {
            final var code = string.split(":")[1].split("\n")[0].split(" ")[1];
            final var d = this.barProductsSold.getData().stream().flatMap((s) -> s.getData().stream().filter((data) -> data.getXValue().equals(code))).findAny();
                    d.get().getNode().setStyle("-fx-bar-fill: blue;");
        }
    }
    private void displayChart() {
        this.barProductsSold.getData().clear();
        final XYChart.Series<String, Integer> serie = new XYChart.Series<>();
        serie.setName("Products");
        final var categoriesSold =  this.controller.getProductsSoldByCategory(selectedCategories);
        this.barProductsSold.getData().clear();
        categoriesSold.entrySet().forEach((entry) ->
            serie.getData().add(new XYChart.Data<String, Integer>(String.valueOf(entry.getKey().getProductCode()), entry.getValue())));
        this.listLegend.getItems().clear();
        this.listSelectedCategories.getItems().clear();
        final var out = categoriesSold.entrySet().stream().map((entry) -> "Code : " + entry.getKey().getProductCode() + "\n" 
                + "Name : " + entry.getKey().getName() + "\n"
                + "Category : " + entry.getKey().getCategory())
                   .collect(Collectors.toList());
        this.listLegend.getItems().addAll(out);

        this.listSelectedCategories.getItems().addAll(this.selectedCategories.stream()
                .map((cat) -> cat.toString()).collect(Collectors.toList()));
        this.barProductsSold.getData().add(serie);
    }
}
