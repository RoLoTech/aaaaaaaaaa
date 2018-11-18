package labTic.persistence;

import labTic.services.entities.Restaurant;
import labTic.services.entities.Tables;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TableRepository extends CrudRepository<Tables, Long> {

    Tables findOneByRestaurant(Restaurant restaurant);

    List<Tables> findAllByRestaurant(Restaurant restaurant);

    List<Tables> findAllByRestaurantAndCapacityGreaterThanEqualAndOccupantIsNull(Restaurant restaurant, int capacity);

    List<Tables> findAllByRestaurantAndOccupantIsNull(Restaurant restaurant);

    List<Tables> findAllByRestaurantAndOccupant(Restaurant restaurant, String occupant);

}
