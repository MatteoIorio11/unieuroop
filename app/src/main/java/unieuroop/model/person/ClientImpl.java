package unieuroop.model.person;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientImpl other = (ClientImpl) obj;
        return Objects.equals(person, other.person);
    }
}
