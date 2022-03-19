package unieuroop.controller.analytic;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import unieuroop.model.analytic.Analytic;

public class ControllerAnalytic {

    private final Analytic analytic;

    public ControllerAnalytic(final Analytic analytic) {
        this.analytic =analytic;
    }
    /**
     * 
     * @return
     */
    Map<LocalDate, Double> getTotalEarned(){
        return this.analytic.getTotalEarned((date) -> true);
    }
    /**
     * 
     * @return
     */
    Map<LocalDate, Double> getTotalSpent(){
        return this.analytic.getTotalSpent((date) -> true);
    }
    /**
     * 
     * @return
     */
    Map<LocalDate, Double> getTotalSold30Days(){
        return this.analytic.getTotalEarned((date) -> 
        Math.abs(LocalDate.now().getMonthValue() - date.getMonthValue()) == 1);
    }
    /**
     * 
     * @return
     */
    Map<LocalDate, Double> getTotalSpent30Days(){
        return null;
    }
}
