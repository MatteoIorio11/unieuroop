package unieuroop.controller.staff;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.util.Pair;
import unieuroop.controller.serialization.Files;
import unieuroop.controller.serialization.Serialization;
import unieuroop.model.department.Department;
import unieuroop.model.person.Staff;
import unieuroop.model.person.StaffImpl;
import unieuroop.model.shop.Shop;

public final class ControllerStaffImpl implements ControllerStaff {

    private static final int ADULT = 18;
    private static final int MAXDATE = 1900;
    private static final int MAXMINUTE = 59;
    private static final int MAXHOUR = 23;
    private final LocalDate maxBirthday = LocalDate.of(LocalDate.now().getYear() - ADULT, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
    private final LocalDate minBirthday = LocalDate.of(MAXDATE, 1, 1);
    private final Shop shop;

    public ControllerStaffImpl(final Shop shop) {
        this.shop = shop;
    }

    @Override
    public void addStaff(final String name, final String surname, final LocalDate birthday, final String email, final String password, 
            final String hoursStartWork, final String minutesStartWork, final String hoursEndWork, final String minutesEndWork) throws IOException {
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(minBirthday) || birthday.isAfter(maxBirthday) || email.isEmpty() || password.isEmpty() || hoursStartWork.isEmpty() || minutesStartWork.isEmpty() 
                || hoursEndWork.isEmpty() || minutesEndWork.isEmpty() || checkWorkTime(Integer.parseInt(hoursStartWork), Integer.parseInt(minutesStartWork), Integer.parseInt(hoursEndWork), Integer.parseInt(minutesEndWork))) {
            throw new IllegalArgumentException("Impossible because one of the parameters are null, hours range from 0 to 23, minutes range from 0 to 59");
        }
        final var days = new HashMap<DayOfWeek, Pair<LocalTime, LocalTime>>();
        final var times = new Pair<>(LocalTime.of(Integer.parseInt(hoursStartWork), Integer.parseInt(minutesStartWork)), LocalTime.of(Integer.parseInt(hoursEndWork), Integer.parseInt(minutesEndWork)));
        IntStream.range(DayOfWeek.MONDAY.getValue(), DayOfWeek.SATURDAY.getValue()).forEach(i -> days.put(DayOfWeek.of(i), times));
        final var date = LocalDateTime.now();
        final ZonedDateTime zdt = date.atZone(ZoneId.systemDefault());
        final int code = (zdt.toInstant().getEpochSecond() + name + surname).hashCode();
        this.shop.addStaff(new StaffImpl(name, surname, birthday, Math.abs(code), email, password.hashCode(), days));
        this.serializationStaff();
    }
 
    @Override
    public void editStaff(final String name, final String surname, final LocalDate birthday, final String email, final String password, 
            final String hoursStartWork, final String minutesStartWork, final String hoursEndWork, final String minutesEndWork, final Staff staff) throws IOException {
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(minBirthday) || birthday.isAfter(maxBirthday) || email.isEmpty() || password.isEmpty() || hoursStartWork.isEmpty() || minutesStartWork.isEmpty() 
                || hoursEndWork.isEmpty() || minutesEndWork.isEmpty() || checkWorkTime(Integer.parseInt(hoursStartWork), Integer.parseInt(minutesStartWork), Integer.parseInt(hoursEndWork), Integer.parseInt(minutesEndWork))) {
            throw new IllegalArgumentException("Impossible because one of the parameters is null, hours range from 0 to 23, minutes range from 0 to 59");
        }
        final var days = new HashMap<DayOfWeek, Pair<LocalTime, LocalTime>>();
        final var times = new Pair<>(LocalTime.of(Integer.parseInt(hoursStartWork), Integer.parseInt(minutesStartWork)), LocalTime.of(Integer.parseInt(hoursEndWork), Integer.parseInt(minutesEndWork)));
        IntStream.range(DayOfWeek.MONDAY.getValue(), DayOfWeek.SUNDAY.getValue()).forEach(i -> days.put(DayOfWeek.of(i), times));
        staff.getPerson().setPersonName(name);
        staff.getPerson().setPersonSurname(surname);
        staff.getPerson().setPersonBirthday(birthday);
        staff.setEmail(email);
        staff.setPassword(Integer.parseInt(password));
        staff.setWorkTime(days);
        this.serializationStaff();
    }

    @Override
    public void deleteStaff(final Staff staff) throws IOException {
        if (!Objects.isNull(staff)) {
            this.shop.removeStaff(staff);
            serializationStaff();
        }
    }

    @Override
    public Set<StaffImpl> getStaff() {
        return this.shop.getStaffs();
    }

    private boolean checkWorkTime(final int hourStart, final int minStart, final int hourEnd, final int minEnd) {
        return hourEnd > MAXHOUR || hourEnd - hourStart > 8 || hourStart >= hourEnd || minStart > MAXMINUTE || minEnd > MAXMINUTE;
    }

    private void serializationStaff() throws IOException {
        Serialization.<Set<StaffImpl>>serialize(Files.STAFFS.getPath(), this.shop.getStaffs());
        Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
    }

    @Override
    public Set<StaffImpl> getUnsignedStaff() {
        return this.shop.getStaffs().stream()
                .filter((staff) -> this.shop.getDepartments().stream().allMatch((department) -> !department.getStaff().contains(staff)))
                .collect(Collectors.toSet());
    }
}
