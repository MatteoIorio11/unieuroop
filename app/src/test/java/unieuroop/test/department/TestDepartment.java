package unieuroop.test.department;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Staff;

public class TestDepartment {

    private static final LocalDate TIME_NOW = LocalDate.now();
    private static final LocalTime TIME_START = LocalTime.now();
    private static final LocalTime TIME_FINISH = LocalTime.of(10, 10);
    private Department department; 
    private final Staff staff1 = new Staff("Nome1", "Cognome1", TestDepartment.TIME_NOW,
            0, "email1@gmail.com", 12, TIME_START, TIME_FINISH);
    private final Staff staff2 = new Staff("Nome2", "Cognome2", TestDepartment.TIME_NOW,
            0, "email2@gmail.csom", 123, TIME_START, TIME_FINISH);
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
    }

}
