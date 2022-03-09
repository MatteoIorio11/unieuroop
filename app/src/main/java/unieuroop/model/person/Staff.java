package unieuroop.model.person;

import java.time.LocalDate;

public class Staff extends AbstractPerson {

    private final Integer id;
    private final String email;
    private final String password;
    private final LocalDate workTime;

    public Staff(final String name, final String surname, final LocalDate birthdayDate, final Integer id, final String email, final String password, final LocalDate workTime) {
        super(name, surname, birthdayDate);
        this.id = id;
        this.email = email;
        this.password = password;
        this.workTime = workTime;
    }

    /**
     * @return id of the Staff
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @return email of the Staff
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return password of the Staff
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @return workTime of the Staff
     */
    public LocalDate getWorkTime() {
        return this.workTime;
    }
}
