package unieuroop.controller.analytic;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import unieuroop.model.analytic.Analytic;
import unieuroop.model.product.Category;
import unieuroop.model.product.Product;

public final class ControllerAnalyticImpl{

    private final Analytic analytic;

    public ControllerAnalyticImpl(final Analytic analytic) {
        this.analytic = analytic;
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
    public Map<LocalDate, Double> getTotalSpent(){
        return this.analytic.getTotalSpent((date) -> true);
    }
    /**
     * 
     * @return
     */
   public  Map<LocalDate, Double> getTotalSold30Days(){
        return this.analytic.getTotalEarned((date) -> 
        Math.abs(LocalDate.now().getMonthValue() - date.getMonthValue()) == 1);
    }
    /**
     * 
     * @return
     */
    public Map<LocalDate, Double> getTotalSpent30Days(){
        return this.analytic.getTotalSpent((date) -> 
        Math.abs(LocalDate.now().getMonthValue() - date.getMonthValue()) == 1);
    }
    public Map<Month, Double> getLastYearSpent(){
        return this.analytic.getTotalSpentByMonth((year) -> LocalDate.now().getYear() == year);
    }
    public Map<Month, Double> getLastYearEarned(){
        return this.analytic.getTotalEarnedByMonth((year) -> LocalDate.now().getYear() == year);
    }
    public Map<Integer, Double> getYearsTotalSpent(){
        return this.analytic.getTotalSpentByYears();
    }
    public Map<Integer, Double> getYearsTotalEarned(){
        return this.analytic.getTotalEarnedByYear();
    }
}
