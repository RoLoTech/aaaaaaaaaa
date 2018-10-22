package labTic.services;

import com.querydsl.core.BooleanBuilder;
import labTic.services.entities.QRestaurant;
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


    public void addRestaurant(long rut, String name, String address, String style, String phoneNumber, String area)
            throws InvalidRestaurantInformation, RestaurantAlreadyExists {

        if (name == null || "".equals(name) || address == null || "".equals(address) || style == null || "".equals(style) ||
                    phoneNumber == null || "".equals(phoneNumber) || area == null || "".equals(area)) {

            throw new InvalidRestaurantInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el restaurante no existe

        if (restaurantRepository.findOneByRut(rut) != null) {

            throw new RestaurantAlreadyExists();
        }

        Restaurant oRestaurant = new Restaurant(rut, name, address, style,phoneNumber,area);

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

    public List<Restaurant> findByArea(String area) {
        return restaurantRepository.findByArea(area);
    }

    public List<Restaurant> findAll() {
        return (List<Restaurant>) restaurantRepository.findAll();
    }

    public List<Restaurant> filter(String area, String foodtype, String address, String pricerange, Float rating, String style, String name) {
        BooleanBuilder builder = new BooleanBuilder();
        final QRestaurant restaurant = QRestaurant.restaurant;
        if (area != null && area.trim().length() != 0) {
            builder.and(restaurant.area.eq(area));
        }
        if (foodtype != null && foodtype.trim().length() != 0) {
            builder.and(restaurant.foodtype.contains(foodtype));
        }
        if (address != null && address.trim().length() != 0) {
            builder.and(restaurant.address.like(address));
        }
        if (pricerange != null && pricerange.trim().length() != 0) {
            builder.and(restaurant.priceRange.eq(pricerange));
        }
        if (rating != null) {
            builder.and(restaurant.rating.eq(rating));
        }
        if (style != null && style.trim().length() != 0) {
            builder.and(restaurant.style.eq(style));
        }
        if (name != null && name.trim().length() != 0) {
            builder.and(restaurant.name.like(name));
        }

        Iterable<Restaurant> restaurants = restaurantRepository.findAll(builder);

        List<Restaurant> list = new ArrayList<>();
        for (Restaurant rest : restaurants) {
            list.add(rest);
        }

        return list;
    }


   /* public List<Restaurant> filterBy(String... args) {
        List<Restaurant> results = new ArrayList<Restaurant>();
        if (args.length == 1){
            results = RestaurantRepository.findAllBy1args[0];
        }

        return results;
    } */
}
