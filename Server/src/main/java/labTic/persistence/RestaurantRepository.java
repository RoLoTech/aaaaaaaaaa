package labTic.persistence;

import org.springframework.data.repository.CrudRepository;
import labTic.services.entities.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

}
