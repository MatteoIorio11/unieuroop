package unieuroop.model.person;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {

    private final BasePerson person;
    @JsonCreator
    public Client(@JsonProperty("name")final String name, 
            @JsonProperty("surname")final String surname, 
            @JsonProperty("birthdayDate")final LocalDate birthdayDate, 
            @JsonProperty("clientCode")final int clientCode) {
        person = new BasePerson(name, surname, birthdayDate, clientCode);
    }
    /**
     * 
     * @return base person
     */
    public BasePerson getPerson() {
        return this.person;
    }

    /**
     * @return toString of BasePerson
     */
    @Override
    public String toString() {
        return this.person.toString();
    }
}
