package unieuroop.view.dates;

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import unieuroop.controller.analytic.ControllerAnalyticImpl;

public final class ViewDateSold implements Initializable {

    @FXML
    private BarChart<String, Double> barTotalSoldYear;
    @FXML
    private BarChart<String, Integer> barSelectedDates;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnClearAll;
    @FXML
    private ListView<String> listDates;
    @FXML
    private Label labelDates;
    private final ControllerAnalyticImpl controller;
    private List<LocalDate> selectedDates = new LinkedList<>();
    private LocalDate date = LocalDate.now();
    public ViewDateSold(final ControllerAnalyticImpl controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.barSelectedDates.setLegendVisible(false);
        this.barTotalSoldYear.setLegendVisible(false);
        final XYChart.Series<String, Double> serie1 = new XYChart.Series<>();
        final XYChart.Series<String, Integer> serie2 = new XYChart.Series<>();
        this.controller.getTotalEarnedByYear().forEach((year, earned) -> serie1.getData()
                .add(new XYChart.Data<String, Double>(String.valueOf(year), earned)));
        this.barTotalSoldYear.getData().add(serie1);
        this.datePicker.setOnDragDetected((dragEvent) -> {
        });

        this.listDates.getSelectionModel().selectedItemProperty().addListener((e) -> {
            final var string = this.listDates.getSelectionModel().getSelectedItem();
            if (string != null) {
                final var code = string.split(":")[1].split("\n")[0].split(" ")[1];
                final var d = this.barSelectedDates.getData().stream().flatMap((s) -> s.getData().stream().filter((data) -> data.getXValue().equals(code))).findAny();
                        d.get().getNode().setStyle("-fx-bar-fill: blue;");
            }
        });

        this.datePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            final var calculatedMap = this.controller.getSelectedDate(date, newValue);
            serie2.getData().clear();
            this.listDates.getItems().clear();
            if (!calculatedMap.isEmpty()) {
                this.selectedDates = calculatedMap.entrySet().stream()
                        .map((entry) -> entry.getKey())
                        .collect(Collectors.toList());
                calculatedMap.forEach((date, quantity) -> 
                serie2.getData().add(new XYChart.Data<String, Integer>(String.valueOf(this.selectedDates.indexOf(date)), quantity)));
                this.listDates.getItems().addAll(this.selectedDates.stream()
                        .map((date) -> String.valueOf("Code : " + this.selectedDates.indexOf(date) + "\n" + "Date : " + date))
                        .collect(Collectors.toList()));
            }
            final var lowerBound = this.date.isBefore(newValue) ? this.date : newValue;
            final var upperBound = this.date.isAfter(newValue) ? this.date : newValue;
            this.labelDates.setText("Dare from : " + lowerBound + " to " + upperBound);
            this.date = LocalDate.of(newValue.getYear(), newValue.getMonthValue(), newValue.getDayOfMonth());
            this.barSelectedDates.getData().clear();
            this.barSelectedDates.getData().add(serie2);
        });
        this.btnClearAll.setOnMouseClicked((e) -> {
            this.selectedDates.clear();
            this.barSelectedDates.getData().clear();
        });
    }

}
