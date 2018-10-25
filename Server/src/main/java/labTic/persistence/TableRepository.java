package labTic.persistence;

import labTic.services.entities.Client;
import labTic.services.entities.Tables;
import org.springframework.data.repository.CrudRepository;

public interface TableRepository extends CrudRepository<Tables, Long> {

    Tables findOneByRestaurantRut(Long restaurantRut);

}
