package unieuroop.test.department;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Staff;

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
    }

}
