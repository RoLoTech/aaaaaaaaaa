package labTic.services;

import labTic.ClientMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

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

    @Test
    public void testFinalFilter(){
        ArrayList<String> comidas = new ArrayList<>();
        comidas.add("Panchos");
      assertEquals(1, rs.filter(null, comidas,"Luis Alberto de Herrera",null, null,null,"La Pasiva").size());
    }
}