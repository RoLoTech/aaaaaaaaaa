package labTic.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FilteringTest {

    @Autowired
    RestaurantService rs;

    @Test
    public void testSimpleFilter(){
        assertEquals(2, rs.findAllByFood_type("Hamburguesas").size());
        assertEquals(1, rs.findAllByFood_type("Panchos").size());
    }

    @Test
    public void testMultipleFilters(){
        assertEquals(1, rs.multipleFiltering("Malvin","Panchos","Luis Alberto de Herrera","$$$",new Float(5.0),"Familiar","La Pasiva").size());
    }
}