package labTic.services;

import commons.ClientRemoteManager;
import commons.exceptions.ClientAlreadyExistsException;
import commons.exceptions.InvalidClientInformationException;
import labTic.business.ClientService;
import labTic.business.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class ClientRemoteService implements ClientRemoteManager {

    @Autowired
    private ClientService clientService;


    @Override
    public void addClient(String firstName, String lastName, String email, String user, String password, String phoneNumber) throws InvalidClientInformationException, ClientAlreadyExistsException, RemoteException {
        clientService.addClient(firstName, lastName, email, user, password, phoneNumber);
    }

    @Override
    public Client findOneByEmail(String email) {
        return clientService.findOneByEmail(email);
    }

    @Override
    public Client findOneByUser(String user) {
        return clientService.findOneByUser(user);
    }
}
