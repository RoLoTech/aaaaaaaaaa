package labTic.persistence;

import labTic.services.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {


    List<Restaurant> findAllByFoodtype(String foodtype);

    Restaurant findOneByRut(long rut);

//    List<Restaurant> findAllByFood_type(long rut);



}
