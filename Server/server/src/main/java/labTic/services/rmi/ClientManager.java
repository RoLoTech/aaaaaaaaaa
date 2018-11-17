package labTic.services.rmi;

import labTic.services.entities.Client;
import labTic.services.exceptions.ClientAlreadyExistsException;
import labTic.services.exceptions.InvalidClientInformationException;

public interface ClientManager {
    public void addClient(String firstName, String lastName, String email, String user, String password, String phoneNumber)
            throws InvalidClientInformationException, ClientAlreadyExistsException;

    public Client findOneByEmail(String email);

    public Client findOneByUser(String user);
}
