package unieuroop.model.person;

import java.time.LocalDate;

public class BasePerson {

    private String name;
    private String surname;
    private LocalDate birthday;
    private final int code;

    protected BasePerson(final String name, final String surname, final LocalDate birthday, final int code) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.code = code;
    }

    /**
     * set person name.
     * @param name
     */
    public void setPersonName(final String name) {
        this.name = name;
    }

    /**
     * set person surname.
     * @param surname
     */
    public void setPersonSurname(final String surname) {
        this.surname = surname;
    }

    /**
     * set person birthday.
     * @param birthday
     */
    public void setPersonBirthday(final LocalDate birthday) {
        this.birthday = birthday;
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
        return this.birthday;
    }

    /**
     * 
     * @return person code
     */
    public int getCode() {
        return this.code;
    }

    /**
     * @return toString of the person
     */
    @Override
    public String toString() {
        return this.name + " " + this.surname + " " + this.birthday + " " + this.code;
    }

    /**
     * override of hashCode.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

    /**
     * override of equals.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasePerson other = (BasePerson) obj;
        return this.code == other.getCode() ? true : false;
    }

}
