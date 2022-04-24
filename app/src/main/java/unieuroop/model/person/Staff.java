package unieuroop.model.person;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

import javafx.util.Pair;

public interface Staff {

    /**
     * 
     * @param email
     */
    void setEmail(String email);

    /**
     * 
     * @param password
     */
    void setPassword(Integer password);

    /**
     * 
     * @param worktime
     */
    void setWorkTime(Map<DayOfWeek, Pair<LocalTime, LocalTime>> worktime);

    /**
     * @return email of the Staff
     */
    String getEmail();

    /**
     * @return password of the Staff
     */
    int getPassword();

    /**
     * @param day 
     * @return return the workTime of the Staff based on the day of the week
     */
    Pair<LocalTime, LocalTime> getWorkTime(DayOfWeek day);

    /**
     * 
     * @return return the workTime of the Staff based on the day of the week
     */
    Map<DayOfWeek, Pair<LocalTime, LocalTime>> getWorkingTimeTable();

    /**
     * 
     * @return base person
     */
    BasePerson getPerson();

    /**
     * @return toString of the Staff
     */
    String toString();

    /**
     * override of hashCode.
     * @return hashCode
     */
    int hashCode();

    /**
     * override of equals.
     * @param obj
     * @return equals
     */
    boolean equals(Object obj);

}
