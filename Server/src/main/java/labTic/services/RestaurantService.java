package labTic.services;

import com.querydsl.core.BooleanBuilder;
import labTic.services.exceptions.RestaurantAlreadyExists;
import labTic.services.exceptions.InvalidRestaurantInformation;
import labTic.services.exceptions.RestaurantNoExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import labTic.persistence.RestaurantRepository;
import labTic.services.entities.Restaurant;

import java.util.*;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    private BooleanBuilder builder = new BooleanBuilder();


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

    public Restaurant findOneByName(String name) {
        return restaurantRepository.findOneByName(name);
    }

    public List<Restaurant> multipleFiltering(String area, String foodtype, String address, String pricerange, Float rating, String style, String name) {
        return restaurantRepository.findRestaurantsByAreaAndFoodtypeAndAddressContainingAndPriceRangeAndRatingAndStyleAndNameContaining(area, foodtype, address, pricerange, rating, style, name);

    }

    public List<Restaurant> filtro_v2(String area, String foodtype, String address, String pricerange, Float rating, String style, String name) {
        if (area == null || area == "") {
            area = "area";
        }
        return restaurantRepository.findRestaurantsByAreaAndFoodtypeAndAddressContainingAndPriceRangeAndRatingAndStyleAndNameContaining(area, foodtype, address, pricerange, rating, style, name);
    }

    public List<Restaurant> findByArea(String area) {
        return restaurantRepository.findByArea(area);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

   /* Filtro definitivo, falta generar QClasses
   public List<Restaurant> filter(String area, String foodtype, String address, String pricerange, Float rating, String style, String name) {
        final QRestaurant restaurant = QRestaurant.restaurant;
        if (area != null && area.trim().length() != 0) {
            builder.and(restaurant.restaurant_area.eq(area));
        }
        return restaurantRepository.findAll(builder);
    }
*/

   /* public List<Restaurant> filterBy(String... args) {
        List<Restaurant> results = new ArrayList<Restaurant>();
        if (args.length == 1){
            results = RestaurantRepository.findAllBy1args[0];
        }

        return results;
    } */
}
