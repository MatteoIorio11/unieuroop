package unieuroop.model.person;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

public class Client extends AbstractPerson implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2150913221610743492L;
    private final int clientCode;

    public Client(final String name, final String surname, final LocalDate birthdayDate, final int clientCode) {
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
