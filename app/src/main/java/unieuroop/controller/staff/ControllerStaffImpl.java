package unieuroop.controller.staff;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
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
    private static final int MINMINUTE = 0;
    private static final int MAXMINUTE = 59;
    private static final int MINHOUR = 0;
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
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(minBirthday) || birthday.isAfter(maxBirthday) || email.isEmpty() || password.isEmpty() 
                || hoursStartWork.isEmpty() || minutesStartWork.isEmpty() || hoursEndWork.isEmpty() || minutesEndWork.isEmpty()) {
            throw new IllegalArgumentException("Impossible because one of the parameters are null, hours range from 0 to 23, minutes range from 0 to 59");
        }
        if (checkWorkTime(Integer.parseInt(hoursStartWork), Integer.parseInt(minutesStartWork), Integer.parseInt(hoursEndWork), Integer.parseInt(minutesEndWork))) {
            throw new IllegalArgumentException("Error in work time, hours range from 0 to 23, minutes range from 0 to 59, worktime must be 8 hours");
        }
        final var days = new HashMap<DayOfWeek, Pair<LocalTime, LocalTime>>();
        final var times = new Pair<>(LocalTime.of(Integer.parseInt(hoursStartWork), Integer.parseInt(minutesStartWork)), LocalTime.of(Integer.parseInt(hoursEndWork), Integer.parseInt(minutesEndWork)));
        IntStream.range(DayOfWeek.MONDAY.getValue(), DayOfWeek.SATURDAY.getValue()).forEach(i -> days.put(DayOfWeek.of(i), times));
        final String code = UUID.randomUUID().toString();
        this.shop.addStaff(new StaffImpl(name, surname, birthday, code, email, password.hashCode(), days));
        this.serializeStaff();
    }
 
    @Override
    public void editStaff(final String name, final String surname, final LocalDate birthday, final String email, final String password, 
            final String hoursStartWork, final String minutesStartWork, final String hoursEndWork, final String minutesEndWork, final Staff staff) throws IOException {
        if (name.isEmpty() || surname.isEmpty() || birthday.isBefore(minBirthday) || birthday.isAfter(maxBirthday) || email.isEmpty() || password.isEmpty() 
                || hoursStartWork.isEmpty() || minutesStartWork.isEmpty() || hoursEndWork.isEmpty() || minutesEndWork.isEmpty()) {
            throw new IllegalArgumentException("Impossible because one of the parameters is null");
        }
        if (checkWorkTime(Integer.parseInt(hoursStartWork), Integer.parseInt(minutesStartWork), Integer.parseInt(hoursEndWork), Integer.parseInt(minutesEndWork))) {
            throw new IllegalArgumentException("Error in work time, hours range from 0 to 23, minutes range from 0 to 59, worktime must be 8 hours");
        }
        final var staffShop = this.shop.getStaffs().stream().filter(staffStream -> staffStream.equals(staff)).findAny();
        if (staffShop.isPresent()) {
            this.shop.editStaff(name, surname, birthday, email, password, hoursStartWork, minutesStartWork, hoursEndWork, minutesEndWork, staff);
            this.serializeStaff();
        } else {
            throw new IllegalArgumentException("The selected staff does not exist"); 
        }
    }

    @Override
    public void deleteStaff(final Staff staff) throws IOException {
        if (!Objects.isNull(staff)) {
            this.shop.removeStaff(staff);
            this.serializeStaff();
        }
    }

    @Override
    public Set<Staff> getStaff() {
        return this.shop.getStaffs();
    }

    private boolean checkWorkTime(final int hourStart, final int minStart, final int hourEnd, final int minEnd) {
        return hourEnd < MINHOUR || hourEnd > MAXHOUR || hourStart < MINHOUR || hourStart > MAXHOUR || hourStart >= hourEnd || minStart > MAXMINUTE
                || minStart < MINMINUTE || minEnd > MAXMINUTE || minEnd < MINMINUTE || checkEightHuorsWork(hourStart, minStart, hourEnd, minEnd);
    }

    private boolean checkEightHuorsWork(final int hourStart, final int minStart, final int hourEnd, final int minEnd) {
        return Math.abs(hourEnd - hourStart) + Math.abs(minEnd - minStart) != 8;
    }

    private void serializeStaff() throws IOException {
        Serialization.<Set<Staff>>serialize(Files.STAFFS.getPath(), this.shop.getStaffs());
        Serialization.<Set<Department>>serialize(Files.DEPARTMENTS.getPath(), this.shop.getDepartments());
    }

    @Override
    public Set<Staff> getUnsignedStaff() {
        return this.shop.getStaffs().stream()
                .filter((staff) -> this.shop.getDepartments().stream().allMatch((department) -> !department.getStaff().contains(staff)))
                .collect(Collectors.toSet());
    }
}
