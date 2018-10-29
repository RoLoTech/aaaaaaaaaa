package labTic.services;

import labTic.MainApp;
import labTic.services.entities.Restaurant;
import labTic.services.entities.Tables;
import labTic.services.exceptions.FullRestaurantException;
import labTic.services.exceptions.RestaurantNoExists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;


@SpringBootTest(classes = MainApp.class)
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

        try {

            rs.book(rest, LocalTime.parse("10:15:30"), "prueba");
        } catch (FullRestaurantException e) {
            e.printStackTrace();
        }

    }
}
