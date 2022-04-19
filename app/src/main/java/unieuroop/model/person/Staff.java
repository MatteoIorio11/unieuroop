package unieuroop.model.person;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.util.Pair;

public class Staff extends AbstractPerson {
    private Integer id;
    private String email;
    private Integer password;
    private Map<DayOfWeek, Pair<LocalTime, LocalTime>> workTime;

    @JsonCreator
    public Staff(@JsonProperty("name") final String name, 
            @JsonProperty("surname") final String surname, 
            @JsonProperty("birthdayDate") final LocalDate birthdayDate,
            @JsonProperty("id") final Integer id,
            @JsonProperty("email") final String email,
            @JsonProperty("password") final Integer password,
            @JsonProperty("workTime") final Map<DayOfWeek, Pair<LocalTime, LocalTime>> workTime) {
        super(name, surname, birthdayDate);
        this.id = id;
        this.email = email;
        this.password = password;
        this.workTime = workTime;
    }

    /**
     * 
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * 
     * @param email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * 
     * @param password
     */
    public void setPassword(final Integer password) {
        this.password = password;
    }

    /**
     * 
     * @param worktime
     */
    public void setWorkTime(final Map<DayOfWeek, Pair<LocalTime, LocalTime>> worktime) {
        this.workTime = worktime;
    }

    /**
     * @return id of the Staff
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @return email of the Staff
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return password of the Staff
     */
    public Integer getPassword() {
        return this.password;
    }

    /**
     * @param day 
     * @return return the workTime of the Staff based on the day of the week
     */
    public Pair<LocalTime, LocalTime> getWorkTime(final DayOfWeek day) {
        if (this.workTime.containsKey(day)) {
            return this.workTime.get(day);
        } else {
            throw new IllegalArgumentException("day out of workDays");
        }
    }
    /**
     * 
     * @return return the workTime of the Staff based on the day of the week
     */
    public Map<DayOfWeek, Pair<LocalTime, LocalTime>> getWorkingTimeTable() {
        return Map.copyOf(this.workTime);
    }

    /**
     * @return toString of the Staff
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.id + " " + this.email;
    }
}
