package labTic.persistence;

import labTic.business.entities.Restaurant;
import labTic.business.entities.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    Booking findByAlias(String alias);

    List<Booking> findAllByRut(Long rut);

    List<Booking> findAllByRutAndConfirmedAndFinishedAndRejected(Long rut, boolean confirmed, boolean finished, boolean rejected);

    Booking findByAliasAndFinished(String alias, boolean finished);

    Booking findByAliasAndRestaurantAndFinished(String alias, Restaurant restaurant, boolean finished);

    Booking findByAliasAndConfirmedAndFinished(String alias, boolean confirmed, boolean finished);

    List<Booking> findAllByRutAndFinishedAndConfirmed(Long rut,boolean finished,boolean confirmed);

}
