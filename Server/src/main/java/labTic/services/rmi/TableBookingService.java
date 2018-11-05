package labTic.services.rmi;

import labTic.services.entities.Booking;
import labTic.services.exceptions.FullRestaurantException;

import java.time.LocalTime;

public interface TableBookingService {
    public Booking book(Long rut, LocalTime startDate, String alias) throws FullRestaurantException;
}
