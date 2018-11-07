package labTic.services;

import labTic.ClientMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = ClientMain.class)
@RunWith(SpringRunner.class)
public class FilteringTest {

    @Autowired
    RestaurantService rs;

    @Test
    public void testSimpleFilter(){
        assertEquals(1, rs.findAllByFood_type("Panchos").size());
    }

//    @Test
//    public void testMultipleFilters(){
//        assertEquals(1, rs.multipleFiltering("Malvin","PanchosHamburguesas","Luis Alberto de Herrera","$$$",new Float(5.0),"Familiar","La Pasiva").size());
//    }
//
    @Test
    public void testFinalFilter(){
        //assertEquals(1, rs.filter("","Panchos","Luis Alberto de Herrera","", null,"","La Pasiva").size());
//        assertEquals(1, rs.filter("","Panchos","Luis Alberto de Herrera","$$$",new Float(5.0),"Familiar","La Pasiva").size());
//        assertEquals(1, rs.filter(null,"Panchos","Luis Alberto de Herrera","$$$",new Float(5.0),"Familiar","La Pasiva").size());
//
    }
}