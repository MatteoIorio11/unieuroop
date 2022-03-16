package unieuroop.test.shop;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import unieuroop.model.department.Department;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.person.Staff;
import unieuroop.model.shop.Shop;
import unieuroop.model.shop.ShopImpl;

public class TestShop {

    private static final LocalDate TIME_NOW = LocalDate.now();
    private static final LocalTime TIME_START = LocalTime.now();
    private static final LocalTime TIME_FINISH = LocalTime.of(10, 10);
    private Department department; 
    private Shop shop01;
    private final Staff staff1 = new Staff("Nome1", "Cognome1", TestShop.TIME_NOW,
            0, "email1@gmail.com", "password1", TIME_START, TIME_FINISH);
    private final Staff staff2 = new Staff("Nome2", "Cognome2", TestShop.TIME_NOW,
            0, "email2@gmail.csom", "password2", TIME_START, TIME_FINISH);

    @Before
    public void setUp() throws Exception {
        this.shop01 = new ShopImpl("shop01");
        this.department = new DepartmentImpl("department1", Set.of(staff1, staff2), Map.of());
    }

    @Test
    public void test() {
        
    }

}
