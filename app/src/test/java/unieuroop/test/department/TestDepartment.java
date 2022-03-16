package unieuroop.test.department;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Staff;
<<<<<<< HEAD

public class TestDepartment {

    private final Department testDepartment01 = new DepartmentImpl("Department01", Collections.emptySet(), Collections.emptyMap());

    @Before
    public void setUp() throws Exception {
        final Staff testStaff02 = new Staff("Marco", "Rossi", LocalDate.of(2000, 10, 17), 1235, "marcorossi3@gmail.com", "marcorossi123456", LocalTime.of(8, 0), LocalTime.of(12, 30));
        testDepartment01.addStaff(testStaff02);
    }

    @Test
    public void test() {
        try {
            assertEquals(Collections.emptySet(), testDepartment01.getStaff());
            final Staff testStaff01 = new Staff("Giovanni", "Verdi", LocalDate.of(1999, 9, 12), 1234, "giovanniverdi2@gmail.com", "giovanniverdi987654", LocalTime.of(8, 0), LocalTime.of(12, 30));
            testDepartment01.addStaff(testStaff01);
            assertEquals(2, testDepartment01.getStaff().size());
            final Staff testStaff02 = new Staff("Marco", "Rossi", LocalDate.of(2000, 10, 17), 1235, "marcorossi3@gmail.com", "marcorossi123456", LocalTime.of(8, 0), LocalTime.of(12, 30));
            testDepartment01.addStaff(testStaff02);
            setUp();
            fail("Exception must be detected");
        }catch (Exception e) {
            
        }
        fail("Not yet implemented");
=======

public class TestDepartment {
    private static final LocalDate TIME_NOW = LocalDate.now();
    private static final LocalTime TIME_START = LocalTime.now();
    private static final LocalTime TIME_FINISH = LocalTime.of(10, 10);
    private Department department; 
    private final Staff staff1 = new Staff("Nome1", "Cognome1", TestDepartment.TIME_NOW,
            0, "email1@gmail.com", "password1", null, null);
    private final Staff staff2 = new Staff("Nome2", "Cognome2", TestDepartment.TIME_NOW,
            0, "email2@gmail.com", "password2", null, null);
    @Before
    public void setUp() throws Exception {
        this.department = new DepartmentImpl("department1", Set.of(staff1, staff2), Map.of());
    }

    @Test
    public void testAddStaff() {
        try {
            this.department.addStaff(staff1);
            fail("ERROR Excpetion must be detected.");
        } catch (UnsupportedOperationException e) {
            fail("ERROR wrong exception throwned.");
        } catch (IllegalArgumentException e1) {
            assertTrue(e1.getMessage().contains("staff"));
        }
>>>>>>> develop
    }

}
