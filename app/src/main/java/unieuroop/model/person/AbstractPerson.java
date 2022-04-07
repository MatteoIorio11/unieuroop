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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthdayDate == null) ? 0 : birthdayDate.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractPerson other = (AbstractPerson) obj;
        if (birthdayDate == null) {
            if (other.birthdayDate != null)
                return false;
        } else if (!birthdayDate.equals(other.birthdayDate))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        return true;
    }
    
}
