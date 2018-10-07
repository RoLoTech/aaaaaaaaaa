package labTic.services;


import labTic.services.exceptions.InvalidRestaurantInformation;
import labTic.services.exceptions.RestaurantAlreadyExists;
import labTic.services.exceptions.RestaurantNoExists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestaurantTest {

    @Autowired
    RestaurantService rs;

    @Test
    public void testBasic() {

        try {
            rs.addRestaurant(123456789, "La Pasiva", "Luis Alberto de Herrera");
            assertEquals(rs.findOneByName("La Pasiva").getName(), "La Pasiva");
        } catch (InvalidRestaurantInformation invalidRestaurantInformation) {
            invalidRestaurantInformation.printStackTrace();
        } catch (RestaurantAlreadyExists restaurantAlreadyExists) {
            restaurantAlreadyExists.printStackTrace();
        }

        try {
            rs.updateRestaurant(123456789, "Panchos");
        } catch (RestaurantNoExists restaurantNoExists) {
            restaurantNoExists.printStackTrace();
        }

        rs.findAllByFood_type("panchos");


        // Se prueba agregar el mismo cliente con la cedula

/*
        try {
            cs.addClient("Sebastian", "Cura","sebastiancura97@gmail.com");
        } catch (InvalidClientInformation invalidClientInformation) {
            invalidClientInformation.printStackTrace();
        } catch (ClientAlreadyExists clientAlreadyExists) {
            clientAlreadyExists.printStackTrace();
        }

*/
        // Ok flujo correcto

    }


}
