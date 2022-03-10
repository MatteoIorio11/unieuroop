package unieuroop.model.person;

import java.time.LocalDate;
import java.util.Optional;

public class Client extends AbstractPerson {

    private final Optional<Integer> clientCode;

    public Client(final String name, final String surname, final LocalDate birthdayDate, final Optional<Integer> clientCode) {
        super(name, surname, birthdayDate);
        this.clientCode = clientCode;
    }

    /**
     * @return the code of the client
     */
    public Optional<Integer> getClientCode() {
        return this.clientCode;
    }

    /**
     * @return toString of the client
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.clientCode;
    }
}
