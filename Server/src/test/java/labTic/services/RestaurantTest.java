package labTic.services;


import labTic.MainApp;
import labTic.services.exceptions.FullRestaurantException;
import labTic.services.exceptions.InvalidRestaurantInformation;
import labTic.services.exceptions.RestaurantAlreadyExists;
import labTic.services.exceptions.RestaurantNoExists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = MainApp.class)
@RunWith(SpringRunner.class)
public class RestaurantTest {

    @Autowired
    RestaurantService rs;

    @Autowired
    TableService ts;

    @Autowired
    BookingService bs;



    @Test
    public void testBasic() throws InvalidRestaurantInformation {
        try {
            rs.addRestaurant(12l, "La Pasiva", "Luis Alberto de Herrera", "Casual", "099886555", "Brazo Oriental");
            assertEquals(rs.findOneByName("La Pasiva").getName(), "La Pasiva");
        } catch (RestaurantAlreadyExists restaurantAlreadyExists) {
            restaurantAlreadyExists.printStackTrace();
        }
        try {
            rs.updateRestaurant(12l, "Pizzas Hamburguesas");
        } catch (RestaurantNoExists restaurantNoExists) {
            restaurantNoExists.printStackTrace();
        }
    }

   /* @Test
    public void testBooking() throws FullRestaurantException{
        rs.book(rs.findOneByName("La Pasiva"), LocalTime.now(), "Rolo");
        assertEquals("Rolo", rs.getBookingRepository().findByAlias("Rolo").getAlias());
    }
*/

}
