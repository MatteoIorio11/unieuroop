package unieuroop.view.balance;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import unieuroop.controller.analytic.ControllerAnalyticImpl;

public final class ViewBalance implements Initializable {
    @FXML
    private PieChart chartSpent;
    @FXML
    private PieChart chartEarned;
    @FXML
    private StackedAreaChart<Integer, Double> areaChart;
    @FXML
    private NumberAxis xAxis;

    private final ControllerAnalyticImpl controller;
    public ViewBalance(final ControllerAnalyticImpl controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final ObservableList<PieChart.Data> secondPieChartData = FXCollections.observableArrayList(
                this.controller.getLastYearEarned().entrySet().stream()
                .map((entry) -> new PieChart.Data(entry.getKey().toString(), entry.getValue())).collect(Collectors.toList()));
        secondPieChartData.forEach((data) -> data.nameProperty().bind(Bindings.concat(data.getName(), "\n", data.pieValueProperty())));

        final ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(this.controller.getLastYearSpent().entrySet().stream().map((e) -> new PieChart.Data(e.getKey().toString(), e.getValue())).collect(Collectors.toList()));
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), "\n", data.pieValueProperty(), " euros"
                        )
                )
        );
        final XYChart.Series<Integer, Double> serie1 = new XYChart.Series<>();
        serie1.setName("Spent");
        final XYChart.Series<Integer, Double> serie2 = new XYChart.Series<>();
        serie2.setName("Earned");
        this.controller.getYearsTotalEarned().entrySet().forEach((entry) ->  serie2.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue())));
        this.controller.getYearsTotalSpent().entrySet().forEach((entry) -> serie1.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue())));

        final var lowerEarned = this.controller.getYearsTotalEarned().entrySet().stream()
                .map((entry) -> entry.getKey()).sorted().findFirst();
        final var lowerSpent = this.controller.getYearsTotalEarned().entrySet().stream()
                .map((entry) -> entry.getKey()).sorted().findFirst();
        if (lowerEarned.isPresent() && lowerSpent.isPresent()) {
            xAxis.setLowerBound(lowerEarned.get() > lowerSpent.get() ? lowerSpent.get() : lowerEarned.get());
            xAxis.setUpperBound(lowerEarned.get() < lowerSpent.get() ? lowerSpent.get() : lowerEarned.get());
        }
        areaChart.getData().add(serie1);
        areaChart.getData().add(serie2);
        xAxis.setAutoRanging(false);
        xAxis.setTickUnit(2);
        chartSpent.setData(pieChartData);
        chartSpent.setLegendVisible(false);
        chartEarned.setData(secondPieChartData);
        chartEarned.setLegendVisible(false);
    }
}
