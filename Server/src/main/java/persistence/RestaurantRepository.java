package persistence;

import org.springframework.data.repository.CrudRepository;
import services.entities.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

}
