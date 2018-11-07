package labTic.persistence;

import labTic.services.entities.Restaurant;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>, QuerydslPredicateExecutor {

    List<Restaurant> findAllByFoodtype(String foodtype);

    List<Restaurant> findRestaurantsByAreaAndFoodtypeAndAddressContainingAndPriceRangeAndRatingAndStyleAndNameContaining(String area, String foodtype, String address, String pricerange, Float rating, String style, String name);

    Restaurant findOneByRut(long rut);

    Restaurant findOneByName(String name);

    List<Restaurant> findByArea(String area);

    List<Restaurant> findAllByAvailability(Boolean availability);

}
