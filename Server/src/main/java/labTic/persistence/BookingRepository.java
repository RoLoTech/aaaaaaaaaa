package labTic.persistence;

import labTic.services.entities.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    Booking findByAlias(String alias);

}
