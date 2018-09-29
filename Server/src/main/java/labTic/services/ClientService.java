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


    public void addClient(Long document, String name, String address)
            throws InvalidClientInformation, ClientAlreadyExists {

        if (name == null || "".equals(name) || address == null || "".equals(address)) {

            throw new InvalidClientInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (clientRepository.findOneByDocument(document) != null) {

            throw new ClientAlreadyExists();
        }

        Client oClient = new Client(document, name, address);

        clientRepository.save(oClient);

    }

}
