package unieuroop.model.person;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Client extends AbstractPerson {

    private final int clientCode;
    @JsonCreator
    public Client(@JsonProperty("name")final String name, 
            @JsonProperty("surname")final String surname, 
            @JsonProperty("birthdayDate")final LocalDate birthdayDate, 
            @JsonProperty("clientCode")final int clientCode) {
        super(name, surname, birthdayDate, clientCode);
        this.clientCode = clientCode;
    }

    /**
     * @return the code of the client
     */
    public int getCode() {
        return this.clientCode;
    }
}
