package services;

import commons.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Service
public class RemoteFactory {

    @Value("${client.rmi.port}")
    private int remotePort;


    public ClientRemoteManager getClientRemoteManager() throws RemoteException {
        ClientRemoteManager clientRemoteManager = null;
        Registry oRegistry = LocateRegistry.getRegistry(remotePort);
        try {
            clientRemoteManager = (ClientRemoteManager) oRegistry.lookup(RemoteConstants.CLIENT_REMOTE_NAME);
        } catch (NotBoundException e) {
            throw new RuntimeException("No se encuentra uno de los objetos remotos con el nombre indicado");
        }

        return clientRemoteManager;
    }

    public RestaurantRemoteManager getRestaurantRemoteManager() throws RemoteException {
        RestaurantRemoteManager restaurantRemoteManager = null;
        Registry oRegistry = LocateRegistry.getRegistry(remotePort);
        try {
            restaurantRemoteManager = (RestaurantRemoteManager) oRegistry.lookup(RemoteConstants.RESTAURANT_REMOTE_NAME);
        } catch (NotBoundException e) {
            throw new RuntimeException("No se encuentra uno de los objetos remotos con el nombre indicado");
        }

        return restaurantRemoteManager;
    }

    public BookingRemoteManager getBookingRemoteManager() throws RemoteException {
        BookingRemoteManager bookingRemoteManager = null;
        Registry oRegistry = LocateRegistry.getRegistry(remotePort);
        try {
            bookingRemoteManager = (BookingRemoteManager) oRegistry.lookup(RemoteConstants.BOOKING_REMOTE_NAME);
        } catch (NotBoundException e) {
            throw new RuntimeException("No se encuentra uno de los objetos remotos con el nombre indicado");
        }

        return bookingRemoteManager;
    }

    public TableRemoteManager getTableRemoteManager() throws RemoteException {
        TableRemoteManager tableRemoteManager = null;
        Registry oRegistry = LocateRegistry.getRegistry(remotePort);
        try {
            tableRemoteManager = (TableRemoteManager) oRegistry.lookup(RemoteConstants.BOOKING_REMOTE_NAME);
        } catch (NotBoundException e) {
            throw new RuntimeException("No se encuentra uno de los objetos remotos con el nombre indicado");
        }

        return tableRemoteManager;
    }
}
