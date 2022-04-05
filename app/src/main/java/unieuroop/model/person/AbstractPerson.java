package unieuroop.model.person;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class AbstractPerson {
    private final String name;
    private final String surname;
    private final LocalDate birthdayDate;

    protected AbstractPerson(final String name, final String surname, final LocalDate birthdayDate) {
        this.name = name;
        this.surname = surname;
        this.birthdayDate = birthdayDate;
    }

    /**
     * @return the name of the person 
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the surname of the person 
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * @return the birthday of the person 
     */
    public LocalDate getBirthdayDate() {
        return this.birthdayDate;
    }

    /**
     * @return toString of the person
     */
    @Override
    public String toString() {
        return this.name + " " + this.surname + " " + this.birthdayDate;
    }
}
