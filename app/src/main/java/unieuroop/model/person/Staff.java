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
}
