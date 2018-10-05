package labTic.services;

import labTic.services.exceptions.RestaurantAlreadyExists;
import labTic.services.exceptions.InvalidRestaurantInformation;
import labTic.services.exceptions.RestaurantNoExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import labTic.persistence.RestaurantRepository;
import labTic.services.entities.Restaurant;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;


    public void addRestaurant(long rut, String name, String adress)
            throws InvalidRestaurantInformation, RestaurantAlreadyExists {

        if (name == null || "".equals(name) || adress == null || "".equals(adress)) {

            throw new InvalidRestaurantInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el restaurante no existe

        if (restaurantRepository.findOneByRut(rut) != null) {

            throw new RestaurantAlreadyExists();
        }

        Restaurant oRestaurant = new Restaurant(rut, name, adress);

        restaurantRepository.save(oRestaurant);

    }

    public void updateRestaurant(long rut, String food_type) throws RestaurantNoExists {
        if (restaurantRepository.findOneByRut(rut) == null) {
            throw new RestaurantNoExists();
        }

        Restaurant oRestaurant = restaurantRepository.findOneByRut(rut);

        oRestaurant.setFood_type(food_type);
        restaurantRepository.save(oRestaurant);



    }



    public List<Restaurant> findAllByFood_type(String food_type) {

        return restaurantRepository.findAllByFoodtype(food_type);

    }

}
