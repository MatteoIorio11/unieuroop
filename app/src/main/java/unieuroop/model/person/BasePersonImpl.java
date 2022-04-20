package unieuroop.model.person;

import java.time.LocalDate;

public final class BasePersonImpl implements BasePerson {

    private String name;
    private String surname;
    private LocalDate birthday;
    private final int code;

    protected BasePersonImpl(final String name, final String surname, final LocalDate birthday, final int code) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.code = code;
    }

    @Override
    public void setPersonName(final String name) {
        this.name = name;
    }

    @Override
    public void setPersonSurname(final String surname) {
        this.surname = surname;
    }

    @Override
    public void setPersonBirthday(final LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public LocalDate getBirthdayDate() {
        return this.birthday;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + " " + this.birthday + " " + this.code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

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
