package unieuroop.model.person;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Client extends AbstractPerson {

    private final Optional<Integer> clientCode;
    @JsonCreator
    public Client(@JsonProperty("name")final String name, 
            @JsonProperty("surname")final String surname, 
            @JsonProperty("birthdayDate")final LocalDate birthdayDate, 
            @JsonProperty("clientCode")final int clientCode) {
        super(name, surname, birthdayDate);
        this.clientCode = clientCode;
    }

    /**
     * @return the code of the client
     */
    public int getClientCode() {
        return this.clientCode;
    }

    /**
     * @return toString of the client
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
