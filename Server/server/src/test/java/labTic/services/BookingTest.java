package labTic.services;

import labTic.ClientMain;
import labTic.services.entities.Restaurant;
import labTic.services.exceptions.FullRestaurantException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;


@SpringBootTest(classes = ClientMain.class)
@RunWith(SpringRunner.class)
public class BookingTest {

    @Autowired
    RestaurantService rs;

    @Autowired
    TableService ts;

    @Test
    public void testBook() {

       Restaurant rest = rs.findOneByName("aaa");

//        try {
//            ts.addTable(123455, 6);
//        } catch (RestaurantNoExists restaurantNoExists) {
//            restaurantNoExists.printStackTrace();
//        }

//        Tables  tab = ts.findAllByRestaurantRut(Long.valueOf(12));
/*
        try {

            rs.book(rest.getRut(), LocalTime.parse("10:15:30"), "prueba");
        } catch (FullRestaurantException e) {
            e.printStackTrace();
        }
*/
    }
}
