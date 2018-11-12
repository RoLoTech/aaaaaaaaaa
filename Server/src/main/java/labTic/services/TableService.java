package labTic.services;

import labTic.persistence.ClientRepository;
import labTic.persistence.RestaurantRepository;
import labTic.persistence.TableRepository;
import labTic.services.entities.Client;
import labTic.services.entities.Tables;
import labTic.services.exceptions.ClientAlreadyExists;
import labTic.services.exceptions.InvalidClientInformation;
import labTic.services.exceptions.RestaurantNoExists;
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
            throws RestaurantNoExists {


        if (restaurantRepository.findOneByRut(restaurantRut) == null) {
            throw new RestaurantNoExists();
        }


        Tables oTable = new Tables(restaurantRut, capacity);

        tableRepository.save(oTable);

    }

    public Tables findAllByRestaurantRut(Long restaurantRut){
        return tableRepository.findOneByRestaurantRut(restaurantRut);
    }

    public void updateTable(long restaurantRut, String occupant, LocalTime startReservation) throws RestaurantNoExists {
        if (restaurantRepository.findOneByRut(restaurantRut) == null) {
            throw new RestaurantNoExists();
        }

        Tables oTable = tableRepository.findOneByRestaurantRut(restaurantRut);

        oTable.setOccupant(occupant);
        oTable.setStartReservation(startReservation);
        tableRepository.save(oTable);


    }


}