package commons;

import commons.exceptions.*;
import labTic.persistence.BookingRepository;
import labTic.persistence.RestaurantRepository;
import labTic.persistence.TableRepository;
import labTic.business.entities.Booking;
import labTic.business.entities.Client;
import labTic.business.entities.Restaurant;
import labTic.business.entities.Tables;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RestaurantRemoteManager extends Remote {
    public TableRepository getTableRepository()throws RemoteException;

    public RestaurantRepository getRestaurantRepository()throws RemoteException;

    public BookingRepository getBookingRepository()throws RemoteException;

    public void addRestaurant(long rut, String name, String address, String style, String phoneNumber, String area, String food, String price)
            throws InvalidRestaurantInformationException, RestaurantAlreadyExistsException, RemoteException;

    public void updateRestaurant(long rut, String food_type) throws RestaurantDoesNotExistException, RemoteException;

    public void addHours(long rut, String monday, String tuesday, String wednesday, String thursday,
                         String friday, String saturday, String sunday) throws RestaurantDoesNotExistException, RemoteException;

    public void addPic(long rut, byte[] pic) throws RestaurantDoesNotExistException, RemoteException;

    public List<Restaurant> findAllByFoodType(String food_type) throws RemoteException;

    public Restaurant findOneByName(String name) throws RemoteException;

    public Restaurant findOneByRut(long rut) throws RemoteException;

    public List<Restaurant> filter(List<String> area, List<String> foodtype, String address, List<String> pricerange, Float rating, List<String> style, String name) throws RemoteException;

    public void book(Long rut, String alias, int assistants) throws FullRestaurantException, NoAvailableTablesException, RemoteException;

    public void release(Restaurant restaurant, String alias) throws RemoteException;

    public List<Restaurant> showActiveRestaurants() throws RemoteException;

    public List<Restaurant> showRestaurantsNow() throws RemoteException;

    public void confirmBooking(Booking booking) throws RemoteException;

    public void rejectBooking(Booking booking) throws RemoteException;

    public void toggleAvailability(Restaurant restaurant) throws RemoteException;

    public String[] getTimeTable(Restaurant restaurant, int day) throws RemoteException;

    public List<Restaurant> getAvailableRestaurantsNow() throws RemoteException;

    public List<Booking> getBookings(Restaurant restaurant) throws RemoteException;

    public List<Booking> getNewBookings(Restaurant restaurant) throws RemoteException;

    public Integer getFee(Restaurant restaurant)throws RemoteException;

    public List<Tables> getCustomerTables(Restaurant restaurant, Client client) throws NoBookingsMadeException, RemoteException;

    }
