package labTic.services;

import labTic.services.exceptions.ClientAlreadyExistsException;
import labTic.services.exceptions.InvalidClientInformationException;
import labTic.services.rmi.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import labTic.persistence.ClientRepository;
import labTic.services.entities.Client;

@Service
public class ClientService implements ClientManager {

    @Autowired
    private ClientRepository clientRepository;

    public void addClient(String firstName, String lastName, String email, String user, String password, String phoneNumber)
            throws InvalidClientInformationException, ClientAlreadyExistsException {

        if (firstName == null || "".equals(firstName) || lastName == null || "".equals(lastName) || email == null || "".equals(email)
                || user == null || "".equals(user) || password == null || "".equals(password) || phoneNumber == null || "".equals(phoneNumber))
            throw new InvalidClientInformationException("Alguno de los datos ingresados no es correcto");
        if (clientRepository.findOneByEmail(email) != null)
            throw new ClientAlreadyExistsException();
        if (clientRepository.findOneByUser(user) != null)
            throw new ClientAlreadyExistsException();
        Client oClient = new Client(firstName, lastName, email, user, password, phoneNumber);
        clientRepository.save(oClient);
    }

    public Client findOneByEmail(String email) {
        return clientRepository.findOneByEmail(email);
    }

    public Client findOneByUser(String user) {
        return clientRepository.findOneByUser(user);
    }

}
