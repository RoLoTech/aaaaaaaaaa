package labTic.persistence;

import labTic.services.entities.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    Booking findByAlias(String alias);

    List<Booking> findAllByRut(Long rut);

    List<Booking> findAllByRutAndConfirmedAndFinishedAndRejected (Long rut, boolean confirmed, boolean finished, boolean rejected);

    Booking findByAliasAndFinished (String alias, boolean finished);

}
