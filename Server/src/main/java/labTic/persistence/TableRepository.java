package labTic.persistence;

import labTic.services.entities.Tables;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TableRepository extends CrudRepository<Tables, Long> {

    Tables findOneByRestaurantRut(long restaurantRut);

    List<Tables> findAllByRestaurantRut(long rut);

    List<Tables> findAllByRestaurantRutAndCapacityGreaterThanEqualAndOccupantIsNull(long rut, int capacity);

    List<Tables> findAllByRestaurantRutAndOccupantIsNull(long rut);

    List<Tables> findAllByRestaurantRutAndOccupant(long rut, String occupant);

}
