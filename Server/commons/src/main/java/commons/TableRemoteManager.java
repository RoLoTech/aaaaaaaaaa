package commons;

import commons.exceptions.RestaurantDoesNotExistException;
import labTic.business.entities.Tables;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TableRemoteManager extends Remote {

    public void addTable(long restaurantRut, int capacity)
            throws RestaurantDoesNotExistException, RemoteException;

    public Tables findAllByRestaurantRut(Long restaurantRut) throws RemoteException;


}
