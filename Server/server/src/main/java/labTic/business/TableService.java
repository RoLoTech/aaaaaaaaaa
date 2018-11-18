package labTic.business;

import commons.exceptions.RestaurantDoesNotExistException;
import labTic.persistence.RestaurantRepository;
import labTic.persistence.TableRepository;
import labTic.business.entities.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    public void addTable(long restaurantRut, int capacity)
            throws RestaurantDoesNotExistException {


        if (restaurantRepository.findOneByRut(restaurantRut) == null) {
            throw new RestaurantDoesNotExistException();
        }


        Tables oTable = new Tables(restaurantRepository.findOneByRut(restaurantRut), capacity);

        tableRepository.save(oTable);

    }

    public Tables findAllByRestaurantRut(Long restaurantRut){
        return tableRepository.findOneByRestaurant(restaurantRepository.findOneByRut(restaurantRut));
    }

}