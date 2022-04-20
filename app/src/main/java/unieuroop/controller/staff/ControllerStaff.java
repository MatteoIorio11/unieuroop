package unieuroop.controller.staff;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

import unieuroop.model.person.Staff;
import unieuroop.model.person.StaffImpl;

public interface ControllerStaff {

    /**
     * add new staff.
     * @param name
     * @param surname
     * @param birthday
     * @param email
     * @param password
     * @param hoursStartWork
     * @param minutesStartWork
     * @param hoursEndWork
     * @param minutesEndWork
     * @throws IOException
     */
    void addStaff(String name, String surname, LocalDate birthday, String email, String password, String hoursStartWork,
            String minutesStartWork, String hoursEndWork, String minutesEndWork) throws IOException;

    /**
     * edit staff.
     * @param name
     * @param surname
     * @param birthday
     * @param email
     * @param password
     * @param hoursStartWork
     * @param minutesStartWork
     * @param hoursEndWork
     * @param minutesEndWork
     * @param staff
     * @throws IOException
     */
    void editStaff(String name, String surname, LocalDate birthday, String email, String password,
            String hoursStartWork, String minutesStartWork, String hoursEndWork, String minutesEndWork, Staff staff)
            throws IOException;

    /**
     * delete staff.
     * @param staff
     * @throws IOException
     */
    void deleteStaff(Staff staff) throws IOException;

    /**
     * 
     * @return all the staffs in the shop.
     */
    Set<StaffImpl> getStaff();

    /**
     * 
     * @return all the staffs unsigned at the department.
     */
    Set<StaffImpl> getUnsignedStaff();

}
