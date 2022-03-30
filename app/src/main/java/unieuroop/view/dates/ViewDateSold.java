package unieuroop.view.dates;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    private final ControllerAnalyticImpl controller;
    private final Set<LocalDate> selectedDates = new HashSet<>();
    public ViewDateSold(final ControllerAnalyticImpl controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final XYChart.Series<String, Double> serie1 = new XYChart.Series<>();
        final XYChart.Series<String, Integer> serie2 = new XYChart.Series<>();
        this.controller.getTotalEarnedByYear().forEach((year, earned) -> serie1.getData()
                .add(new XYChart.Data<String, Double>(String.valueOf(year), earned)));
        this.barTotalSoldYear.getData().add(serie1);
        this.datePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            this.selectedDates.add(newValue);
            this.controller.getSelectedDate(selectedDates).forEach((date, quantity) -> 
                serie2.getData().add(new XYChart.Data<String, Integer>(date.toString(), quantity)));
            this.barSelectedDates.getData().add(serie2);
        });
        this.btnClearAll.setOnMouseClicked((e) -> {
            this.selectedDates.clear();
            this.barSelectedDates.getData().clear();
        });
    }

}
