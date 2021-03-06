package unieuroop.view.dateanalytic;

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import unieuroop.controller.analytic.ControllerAnalytic;

public final class ViewDateSoldImpl implements Initializable, ViewDataSold {

    @FXML private BarChart<String, Double> barTotalSoldYear;
    @FXML private BarChart<String, Integer> barSelectedDates;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private Button btnClearAll;
    @FXML private ListView<String> listDates;
    @FXML private Label labelDates;
    private final ControllerAnalytic controller;
    private List<LocalDate> selectedDates = new LinkedList<>();
    private Optional<LocalDate> start = Optional.empty();
    private Optional<LocalDate> end = Optional.of(LocalDate.now());
    private final XYChart.Series<String, Integer> inpuDataSerie;
    public ViewDateSoldImpl(final ControllerAnalytic controller) {
        this.controller = controller;
        this.inpuDataSerie = new XYChart.Series<>();
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.barSelectedDates.setLegendVisible(false);
        this.barTotalSoldYear.setLegendVisible(false);
        final XYChart.Series<String, Double> dataSerie = new XYChart.Series<>();
        this.controller.getTotalEarnedByYear().entrySet().stream().sorted((entry1, entry2) -> Integer.compare(entry1.getKey(), entry2.getKey())).forEach((entry) -> dataSerie.getData()
                .add(new XYChart.Data<String, Double>(String.valueOf(entry.getKey()), entry.getValue())));
        this.barTotalSoldYear.getData().add(dataSerie);
    }

    @Override
    @FXML
    public void dateSelectStartHandler(final ActionEvent event) {
        this.start = Optional.of(this.startDate.getValue());
        this.calculateMap();
    }

    @Override
    @FXML
    public void dateSelectEndHandler(final ActionEvent event) {
        if (this.start.isEmpty()) {
            event.consume();
        } else {
            this.end = Optional.of(this.endDate.getValue());
            this.calculateMap();
        }
    }

    @Override
    @FXML
    public void listSelectDateHandler(final MouseEvent event) {
        final var string = this.listDates.getSelectionModel().getSelectedItem();
        if (string != null) {
            final var code = string.split(":")[1].split("\n")[0].split(" ")[1];
            final var dataSerie = this.barSelectedDates.getData().stream().flatMap((s) -> s.getData().stream().filter((data) -> data.getXValue().equals(code))).findAny();
                    dataSerie.get().getNode().setStyle("-fx-bar-fill: blue;");
        }
    }

    @Override
    @FXML
    public void buttonClearAllHandler(final ActionEvent event) {
        this.start = Optional.empty();
        this.end = Optional.of(LocalDate.now());
        this.selectedDates.clear();
        this.barSelectedDates.getData().clear();
        this.listDates.getItems().clear();
    }
    private void calculateMap() {
        final var calculatedMap = this.controller.getSelectedDate(this.start.get(), this.end.get());
        this.inpuDataSerie.getData().clear();
        this.listDates.getItems().clear();
        if (!calculatedMap.isEmpty()) {
            this.selectedDates = calculatedMap.entrySet().stream()
                    .map((entry) -> entry.getKey())
                    .collect(Collectors.toList());
            calculatedMap.forEach((date, quantity) -> 
            this.inpuDataSerie.getData().add(new XYChart.Data<String, Integer>(String.valueOf(this.selectedDates.indexOf(date)), quantity)));
            this.listDates.getItems().addAll(this.selectedDates.stream()
                    .map((date) -> String.valueOf("Code : " + this.selectedDates.indexOf(date) + "\n" 
                            + "Date : " + date + "\n" 
                            + "Quantity : " + calculatedMap.get(date)))
                    .collect(Collectors.toList()));
        }
        final var lowerBound = this.start.get().isBefore(this.end.get()) ? this.start.get() : this.end.get();
        final var upperBound = this.start.get().isAfter(this.end.get()) ? this.start.get() : this.end.get();
        this.labelDates.setText("Date from : " + lowerBound + " to " + upperBound);
        this.barSelectedDates.getData().clear();
        this.barSelectedDates.getData().add(inpuDataSerie);
    }
}
