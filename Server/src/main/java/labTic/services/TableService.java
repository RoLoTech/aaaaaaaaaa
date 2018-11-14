package labTic.services;

import labTic.persistence.ClientRepository;
import labTic.persistence.RestaurantRepository;
import labTic.persistence.TableRepository;
import labTic.services.entities.Client;
import labTic.services.entities.Tables;
import labTic.services.exceptions.ClientAlreadyExistsException;
import labTic.services.exceptions.InvalidClientInformationException;
import labTic.services.exceptions.RestaurantDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

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


        Tables oTable = new Tables(restaurantRut, capacity);

        tableRepository.save(oTable);

    }

    public Tables findAllByRestaurantRut(Long restaurantRut){
        return tableRepository.findOneByRestaurantRut(restaurantRut);
    }

    public void updateTable(long restaurantRut, String occupant, LocalTime startReservation) throws RestaurantDoesNotExistException {
        if (restaurantRepository.findOneByRut(restaurantRut) == null) {
            throw new RestaurantDoesNotExistException();
        }

        Tables oTable = tableRepository.findOneByRestaurantRut(restaurantRut);

        oTable.setOccupant(occupant);
        tableRepository.save(oTable);


    }


}