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
    public void testBasic() throws InvalidRestaurantInformation {

        try {
            rs.addRestaurant(12, "La Redonda", "Luis Alberto de Herrera","Casual","099886555","Brazo Oriental");
            assertEquals(rs.findOneByName("La Redonda").getName(), "La Redonda");
        } catch (RestaurantAlreadyExists restaurantAlreadyExists) {
            restaurantAlreadyExists.printStackTrace();
        }

        try {
            rs.updateRestaurant(12, "Pizzas Hamburguesas");
        } catch (RestaurantNoExists restaurantNoExists) {
            restaurantNoExists.printStackTrace();
        }

//        rs.findAllByFood_type("Pizzas");


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
