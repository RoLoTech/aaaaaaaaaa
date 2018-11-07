package labTic.persistence;

import labTic.services.entities.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    Booking findByAlias(String alias);

    List<Booking> findAllByRut(Long rut);

}
