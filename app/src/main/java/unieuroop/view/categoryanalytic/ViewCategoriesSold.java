package unieuroop.view.categoryanalytic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface ViewCategoriesSold {

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

    /**
     * handler for clearing view.
     * @param event
     */
    void buttonClearEvent(ActionEvent event);

    /**
     * handler for selection of the categories to display in the view.
     * @param event
     */
    void comboSelectCategoryHandler(ActionEvent event);

    /**
     * handler for product selection to make it evident in the chart.
     * @param event
     */
    void listSelectValueHandler(MouseEvent event);

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
            final var dataInput = this.barProductsSold.getData().stream().flatMap((s) -> s.getData().stream().filter((data) -> data.getXValue().equals(code))).findAny();
            dataInput.get().getNode().setStyle("-fx-bar-fill: blue;");
        }
    }

    /**
     * 
     */
    private void displayChart() {
        this.barProductsSold.getData().clear();
        final XYChart.Series<String, Integer> productSerie = new XYChart.Series<>();
        productSerie.setName("Products");
        final var categoriesSold =  this.controller.getProductsSoldByCategory(selectedCategories);
        this.barProductsSold.getData().clear();
        categoriesSold.entrySet().forEach((entry) ->
            productSerie.getData().add(new XYChart.Data<String, Integer>(String.valueOf(entry.getKey().getProductCode()), entry.getValue())));
        this.listLegend.getItems().clear();
        this.listSelectedCategories.getItems().clear();
        final var out = categoriesSold.entrySet().stream().map((entry) -> "Code : " + entry.getKey().getProductCode() + "\n" 
                + "Name : " + entry.getKey().getName() + "\n"
                + "Category : " + entry.getKey().getCategory() + "\n"
                + "Quantity : " + entry.getValue())
                   .collect(Collectors.toList());
        this.listLegend.getItems().addAll(out);

        this.listSelectedCategories.getItems().addAll(this.selectedCategories.stream()
                .map((cat) -> cat.toString()).collect(Collectors.toList()));
        this.barProductsSold.getData().add(productSerie);
    }
}
