package labTic.persistence;

import com.querydsl.core.BooleanBuilder;
import labTic.services.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    List<Restaurant> findAllByFoodtype(String foodtype);

    List<Restaurant> findRestaurantsByAreaAndFoodtype(String area, String foodtype);

    List<Restaurant> findRestaurantsByAreaAndFoodtypeAndAddressContainingAndPriceRangeAndRatingAndStyleAndNameContaining(String area, String foodtype, String address, String pricerange, Float rating, String style, String name);

    Restaurant findOneByRut(long rut);

    Restaurant findOneByName(String name);

    List<Restaurant> findByArea(String area);

    List<Restaurant> findAll();

    List<Restaurant> findAll(BooleanBuilder builder);
// Filtro global en progreso
//    @Query("select r from Restaurant r where r.restaurant_food_type=foodtype and r.area=restaurant_area and r.")
//    List<Restaurant> filtro_v2(String area, String foodtype, String address, String pricerange, Float rating, String style, String name);


    //List<Restaurant> findAll(Restaurant restaurant);

//    List<Restaurant> findAllByFood_type(long rut);


}
