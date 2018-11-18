package commons;

import commons.exceptions.ClientAlreadyExistsException;
import commons.exceptions.InvalidClientInformationException;
import labTic.business.entities.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRemoteManager extends Remote {
    public void addClient(String firstName, String lastName, String email, String user, String password, String phoneNumber)
            throws InvalidClientInformationException, ClientAlreadyExistsException, RemoteException;

    public Client findOneByEmail(String email);

    public Client findOneByUser(String user);
}
