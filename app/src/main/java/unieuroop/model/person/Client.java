package unieuroop.model.person;

public interface Client {

    /**
     * 
     * @return base person
     */
    BasePerson getPerson();

    /**
     * @return toString of BasePerson
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
