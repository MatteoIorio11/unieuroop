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
import unieuroop.controller.analytic.ControllerAnalytic;

public final class ViewBalanceImpl implements Initializable, ViewBalance {
    @FXML private PieChart chartSpent;
    @FXML private PieChart chartEarned;
    @FXML private StackedAreaChart<Integer, Double> areaChart;
    @FXML private NumberAxis xAxis;

    private final ControllerAnalytic controller;
    public ViewBalanceImpl(final ControllerAnalytic controller) {
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
        final XYChart.Series<Integer, Double> spentSerie = new XYChart.Series<>();
        spentSerie.setName("Spent");
        final XYChart.Series<Integer, Double> earnedSerie = new XYChart.Series<>();
        earnedSerie.setName("Earned");
        this.controller.getTotalEarnedByYear().entrySet().forEach((entry) ->  earnedSerie.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue())));
        this.controller.getYearsTotalSpent().entrySet().forEach((entry) -> spentSerie.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue())));

        final var lowerEarned = this.controller.getTotalEarnedByYear().entrySet().stream()
                .map((entry) -> entry.getKey()).sorted(Integer::compare).findFirst();
        final var upperEarned = this.controller.getTotalEarnedByYear().entrySet().stream()
                .map((entry) -> entry.getKey()).sorted((year1, year2) -> Integer.compare(year2, year1)).findFirst();
        if (lowerEarned.isPresent() && upperEarned.isPresent()) {
            xAxis.setLowerBound(lowerEarned.get() > upperEarned.get() ? upperEarned.get() : lowerEarned.get());
            xAxis.setUpperBound(lowerEarned.get() < upperEarned.get() ? upperEarned.get() : lowerEarned.get());
        }
        xAxis.setAutoRanging(false);
        xAxis.setTickUnit(2);
        areaChart.getData().add(spentSerie);
        areaChart.getData().add(earnedSerie);
        chartSpent.setData(pieChartData);
        chartEarned.setData(secondPieChartData);
        chartSpent.setLegendVisible(false);
        chartEarned.setLegendVisible(false);
    }
}
