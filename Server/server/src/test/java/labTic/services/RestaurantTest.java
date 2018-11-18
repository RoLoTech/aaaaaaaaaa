package labTic.services;


import commons.exceptions.InvalidRestaurantInformationException;
import commons.exceptions.RestaurantDoesNotExistException;
import labTic.ClientMain;
import labTic.business.BookingService;
import labTic.business.RestaurantService;
import labTic.business.TableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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
    public void testBasic() throws InvalidRestaurantInformationException {
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
        Resource backImgFile = new FileSystemResource("C:\\Users\\Seba\\Desktop\\restaurant.jpeg");
        //ClassPathResource backImgFile = new ClassPathResource("image/20180204_193708.jpg");
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
            rs.addPic(6, arrayPic);
            rs.updateRestaurant(6, "Pizza");
        } catch (RestaurantDoesNotExistException restaurantNoExists) {
            restaurantNoExists.printStackTrace();
        }
        try {
            rs.addHours(1, "12:00-00:00", "12:00-00:00", "12:00-00:00",
                    "12:00-00:00", "12:00-01:00", "12:00-02:00", "12:00-00:00");
        } catch (RestaurantDoesNotExistException restaurantNoExists) {
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
