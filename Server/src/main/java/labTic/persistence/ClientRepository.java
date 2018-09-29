package labTic.persistence;

        import org.springframework.data.repository.CrudRepository;
        import labTic.services.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findOneByDocument(long document);

}
