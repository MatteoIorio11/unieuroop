package unieuroop.controller.staff;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javafx.util.Pair;
import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.person.Staff;
import unieuroop.model.shop.Shop;

public final class ControllerStaffImpl {

    private static final int ADULT = 18;
    private static final int MAXDATE = 1900;
    private final LocalDate maxBirthday = LocalDate.of(LocalDate.now().getYear() - ADULT, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
    private final LocalDate minBirthday = LocalDate.of(MAXDATE, 1, 1);
    private final Shop shop;

    public ControllerStaffImpl(final Shop shop) {
        this.shop = shop;
    }

    public void addStaff(final String name, final String surname, final LocalDate birthday, final String id, final String email, final String password, 
            final DayOfWeek day, final String hoursStartWork, final String minutesStartWork, final String hoursEndWork, final String minutesEndWord) {
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(minBirthday) || birthday.isAfter(maxBirthday) || id.isEmpty() || email.isEmpty() 
                || password.isEmpty() || hoursStartWork.isEmpty() || minutesStartWork.isEmpty() || hoursEndWork.isEmpty() || minutesEndWord.isEmpty() || Integer.parseInt(hoursStartWork) >= Integer.parseInt(minutesEndWord)) {
            throw new IllegalArgumentException("Impossible because one of the parameters are null");
        }
        this.shop.addStaff(new Staff(name, surname, birthday, Integer.valueOf(id), email, Integer.valueOf(password), 
                Map.of(day, new Pair<LocalTime, LocalTime>(LocalTime.of(Integer.parseInt(hoursStartWork), Integer.parseInt(minutesStartWork)), LocalTime.of(Integer.parseInt(hoursEndWork), Integer.parseInt(minutesEndWord))))));
        serializationStaff();
    }
 
    public void editStaff(final String name, final String surname, final LocalDate birthday, final String id, final String email, final String password, 
            final DayOfWeek day, final String hoursStartWork, final String minutesStartWork, final String hoursEndWork, final String minutesEndWord, final Staff staff) {
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(minBirthday) || birthday.isAfter(maxBirthday) || id.isEmpty() || email.isEmpty() 
                || password.isEmpty() || hoursStartWork.isEmpty() || minutesStartWork.isEmpty() || hoursEndWork.isEmpty() || minutesEndWord.isEmpty() || Integer.parseInt(hoursStartWork) >= Integer.parseInt(hoursEndWork)) {
            throw new IllegalArgumentException("Impossible because one of the parameters are null");
        }
        staff.setPersonName(name);
        staff.setPersonSurname(surname);
        staff.setPersonBirthday(birthday);
        staff.setId(Integer.parseInt(id));
        staff.setEmail(email);
        staff.setPassword(Integer.parseInt(password));
        staff.setWorkTime(Map.of(day, new Pair<LocalTime, LocalTime>(LocalTime.of(Integer.parseInt(hoursStartWork), Integer.parseInt(minutesStartWork)), LocalTime.of(Integer.parseInt(hoursEndWork), Integer.parseInt(minutesEndWord)))));
        serializationStaff();
    }

    public void deleteStaff(final Staff staff) {
        if (!Objects.isNull(staff)) {
            this.shop.removeStaff(staff);
            serializationStaff();
        }
    }

    public Set<Staff> getStaff() {
        return this.shop.getStaffs();
    }

    private void serializationStaff() {
        try {
            Serialization.<Set<Staff>>serialize(Files.STAFFS.getPath(), this.shop.getStaffs());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
