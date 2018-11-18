package labTic.services;

import commons.TableRemoteManager;
import commons.exceptions.RestaurantDoesNotExistException;
import labTic.business.TableService;
import labTic.business.entities.Tables;
import org.springframework.beans.factory.annotation.Autowired;

public class TableRemoteService implements TableRemoteManager {

    @Autowired
    TableService tableService;

    @Override
    public void addTable(long restaurantRut, int capacity) throws RestaurantDoesNotExistException {
        tableService.addTable(restaurantRut, capacity);
    }

    @Override
    public Tables findAllByRestaurantRut(Long restaurantRut) {
        return null;
    }
}
