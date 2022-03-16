package unieuroop.model.person;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Staff extends AbstractPerson implements Serializable {

    private static final long serialVersionUID = 3841944704413502446L;
    private final Integer id;
    private final String email;
    private final String password;
    private final LocalTime startTime;
    private final LocalTime endTime;

    @JsonCreator
    public Staff(@JsonProperty("name") final String name, 
            @JsonProperty("surname") final String surname, 
            @JsonProperty("birthdayDate") final LocalDate birthdayDate,
            @JsonProperty("id") final Integer id,
            @JsonProperty("email") final String email,
            @JsonProperty("password") final String password,
            @JsonProperty("startTime") final LocalTime startTime,
            @JsonProperty("endTime") final LocalTime endTime) {
        super(name, surname, birthdayDate);
        this.id = id;
        this.email = email;
        this.password = password;
        this.startTime = startTime;
        this.endTime = endTime;
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
    public String getPassword() {
        return this.password;
    }

    /**
     * @return startTime of the Staff
     */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
    * @return endTime of the Staff
    */
   public LocalTime getEndTime() {
       return this.endTime;
   }

    /**
     * @return toString of the Staff
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.id + " " + this.email + " " + this.password + " " + this.startTime + " " + this.endTime;
    }
}
