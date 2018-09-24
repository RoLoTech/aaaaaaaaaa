package services;

import org.springframework.beans.factory.annotation.Autowired;
import persistence.ClientRepository;
import persistence.RestaurantRepository;
import services.entities.Client;

public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public String addNewClient (String firstName, String lastName, Integer ci) {

        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setCi(ci);
        clientRepository.save(client);
        return "Cliente agregado";
    }
}
