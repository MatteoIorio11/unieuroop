package unieuroop.model.person;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ClientImpl implements Client {

    private final BasePerson person;
    @JsonCreator
    public ClientImpl(@JsonProperty("name")final String name, 
            @JsonProperty("surname")final String surname, 
            @JsonProperty("birthdayDate")final LocalDate birthdayDate, 
            @JsonProperty("clientCode")final int clientCode) {
        person = new BasePersonImpl(name, surname, birthdayDate, clientCode);
    }

    @Override
    public BasePerson getPerson() {
        return this.person;
    }

    @Override
    public String toString() {
        return this.person.toString();
    }
}
