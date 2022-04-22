package unieuroop.model.person;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.util.Pair;

public final class StaffImpl implements Staff {

    private String email;
    private int password;
    private Map<DayOfWeek, Pair<LocalTime, LocalTime>> workTime;
    private final BasePerson person;

    @JsonCreator
    public StaffImpl(@JsonProperty("name") final String name, 
            @JsonProperty("surname") final String surname, 
            @JsonProperty("birthdayDate") final LocalDate birthdayDate,
            @JsonProperty("id") final Integer id,
            @JsonProperty("email") final String email,
            @JsonProperty("password") final Integer password,
            @JsonProperty("workTime") final Map<DayOfWeek, Pair<LocalTime, LocalTime>> workTime) {
        person = new BasePersonImpl(name, surname, birthdayDate, id);
        this.email = email;
        this.password = password;
        this.workTime = workTime;
    }

    @Override
    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public void setPassword(final Integer password) {
        this.password = password;
    }

    @Override
    public void setWorkTime(final Map<DayOfWeek, Pair<LocalTime, LocalTime>> worktime) {
        this.workTime = worktime;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public int getPassword() {
        return this.password;
    }

    @Override
    public Pair<LocalTime, LocalTime> getWorkTime(final DayOfWeek day) {
        if (this.workTime.containsKey(day)) {
            return this.workTime.get(day);
        } else {
            throw new IllegalArgumentException("day out of workDays");
        }
    }

    @Override
    public Map<DayOfWeek, Pair<LocalTime, LocalTime>> getWorkingTimeTable() {
        return Map.copyOf(this.workTime);
    }

    @Override
    public BasePerson getPerson() {
        return this.person;
    }

    @Override
    public String toString() {
        return this.person.toString() + " " + this.email;
    }

    @Override
    public int hashCode() {
        return this.person.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return this.person.equals(obj);
    }
}
