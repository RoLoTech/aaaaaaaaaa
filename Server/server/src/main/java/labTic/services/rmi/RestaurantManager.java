package labTic.services.rmi;

import labTic.persistence.BookingRepository;
import labTic.persistence.RestaurantRepository;
import labTic.persistence.TableRepository;
import labTic.services.entities.Booking;
import labTic.services.entities.Restaurant;
import labTic.services.exceptions.*;

import java.time.LocalTime;
import java.util.List;

public interface RestaurantManager {
    public TableRepository getTableRepository();

    public RestaurantRepository getRestaurantRepository();

    public BookingRepository getBookingRepository();

    public void addRestaurant(long rut, String name, String address, String style, String phoneNumber, String area, String food, String price)
            throws InvalidRestaurantInformationException, RestaurantAlreadyExistsException;

    public void updateRestaurant(long rut, String food_type) throws RestaurantDoesNotExistException;

    public void addHours(long rut, String monday, String tuesday, String wednesday, String thursday,
                         String friday, String saturday, String sunday) throws RestaurantDoesNotExistException;

    public void addPic(long rut, byte[] pic) throws RestaurantDoesNotExistException;

    public List<Restaurant> findAllByFood_type(String food_type);

    public Restaurant findOneByName(String name);

    public Restaurant findOneByRut(long rut);

    public List<Restaurant> findByArea(String area);

    public List<Restaurant> findAll();

    public List<Restaurant> filter(List<String> area, List<String> foodtype, String address, List<String> pricerange, Float rating, List<String> style, String name);

    public void book(Long rut, String alias, int assistants) throws FullRestaurantException, NoAvailableTablesException;

    public void release(Restaurant restaurant, String alias);

    public List<Restaurant> showActiveRestaurants();

    public List<Restaurant> showRestaurantsNow();

    public void confirmBooking(Booking booking);

    public void rejectBooking(Booking booking);

    public void toggleAvailability(Restaurant restaurant);

    public String[] getTimeTable(Restaurant restaurant, int day);

    public List<Restaurant> getAvailableRestaurantsNow();

    public List<Booking> getBookings(Restaurant restaurant);

    public List<Booking> getNewBookings(Restaurant restaurant);

    public Integer getFee(Restaurant restaurant);




    }
