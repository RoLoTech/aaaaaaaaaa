package labTic.services;

import labTic.persistence.TableRepository;
import labTic.services.entities.Booking;
import labTic.services.entities.Tables;
import labTic.services.exceptions.FullRestaurantException;
import labTic.services.rmi.TableBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service("tableBookingServiceImpl")
@Qualifier("tableBookingServiceImpl")
public class TableBookingServiceImpl implements TableBookingService {

    @Autowired
    TableRepository tableRepository;

    @Override
    public Booking book(Long rut, LocalTime startDate, String alias) throws FullRestaurantException {
        Tables bookedTable = null;
        Iterable<Tables> result = tableRepository.findAllByRestaurantRut(rut);
        List<Tables> list = new ArrayList<>();
        for (Tables tableToIterate : result) {
            list.add(tableToIterate);
        }
        boolean succesfulBooking = false;
        for (int i = 0; i < list.size() && succesfulBooking == false; i++) {
            if (list.get(i).getOccupant() == null) {
                list.get(i).setOccupant(alias);
                list.get(i).setStartReservation(startDate);
                bookedTable = list.get(i);
                succesfulBooking = true;
            }
        }
        if (succesfulBooking == false) {
            throw new FullRestaurantException();
        }
        Booking booking = new Booking((rut + alias.hashCode()), rut, alias, bookedTable);


        return booking;
    }
}
