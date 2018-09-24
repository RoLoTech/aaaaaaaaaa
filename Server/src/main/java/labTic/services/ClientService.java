package labTic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import labTic.persistence.ClientRepository;
import labTic.services.entities.Client;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public String addNewClient (String firstName, String lastName, String ci) {

        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setCi(ci);
        clientRepository.save(client);
        return "Cliente agregado";
    }
}
