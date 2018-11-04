package labTic.services;


import labTic.ClientMain;
import labTic.services.exceptions.InvalidRestaurantInformation;
import labTic.services.exceptions.RestaurantNoExists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = ClientMain.class)
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
//        try {
//            rs.addRestaurant(12l, "La Pasiva", "Luis Alberto de Herrera", "Casual", "099886555", "Brazo Oriental");
//            assertEquals(rs.findOneByName("La Pasiva").getName(), "La Pasiva");
//        } catch (RestaurantAlreadyExists restaurantAlreadyExists) {
//            restaurantAlreadyExists.printStackTrace();
//        }
//        try {
//            rs.updateRestaurant(12l, "Pizzas Hamburguesas");
//        } catch (RestaurantNoExists restaurantNoExists) {
//            restaurantNoExists.printStackTrace();
//        }
        ClassPathResource backImgFile = new ClassPathResource("image/20180204_193708.jpg");
        byte[] arrayPic = new byte[0];
        try {
            arrayPic = new byte[(int) backImgFile.contentLength()];
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            backImgFile.getInputStream().read(arrayPic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            rs.addPic(123455, arrayPic);
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
