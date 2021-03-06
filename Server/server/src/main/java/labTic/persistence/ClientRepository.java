package labTic.persistence;

import org.springframework.data.repository.CrudRepository;
import labTic.business.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findOneByEmail(String email);

    Client findOneByUser(String user);

}
