package unieuroop.model.person;

import java.time.LocalDate;

public final class ClientImpl implements Client {

    private final BasePerson person;
    public ClientImpl(final String name, final String surname, final LocalDate birthdayDate, final int clientCode) {
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
