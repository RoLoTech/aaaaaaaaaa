package labTic.services;

import labTic.services.exceptions.ClientAlreadyExists;
import labTic.services.exceptions.InvalidClientInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import labTic.persistence.ClientRepository;
import labTic.services.entities.Client;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public void addClient(String firstName, String lastName, String email)
            throws InvalidClientInformation, ClientAlreadyExists {

        if (firstName == null || "".equals(firstName) || lastName == null || "".equals(lastName) || email == null || "".equals(email)) {

            throw new InvalidClientInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (clientRepository.findOneByEmail(email) != null) {

            throw new ClientAlreadyExists();
        }

        Client oClient = new Client(firstName, lastName, email);

        clientRepository.save(oClient);

    }

}
