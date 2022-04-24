package unieuroop.model.person;

import java.time.LocalDate;

public interface BasePerson {

    /**
     * set person name.
     * @param name
     */
    void setPersonName(String name);

    /**
     * set person surname.
     * @param surname
     */
    void setPersonSurname(String surname);

    /**
     * set person birthday.
     * @param birthday
     */
    void setPersonBirthday(LocalDate birthday);

    /**
     * @return the name of the person 
     */
    String getName();

    /**
     * @return the surname of the person 
     */
    String getSurname();

    /**
     * @return the birthday of the person 
     */
    LocalDate getBirthdayDate();

    /**
     * 
     * @return person code
     */
    String getCode();

    /**
     * @return toString of the person
     */
    String toString();

    /**
     * override of hashCode.
     * @return hashCode
     */
    int hashCode();

    /**
     * override of equals.
     * @param obj
     * @return equals
     */
    boolean equals(Object obj);

}
