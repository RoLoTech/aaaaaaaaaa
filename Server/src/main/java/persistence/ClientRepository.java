package persistence;

import org.springframework.data.repository.CrudRepository;
import services.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
