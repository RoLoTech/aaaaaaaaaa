package labTic.services;

import commons.RestaurantRemoteManager;
import commons.exceptions.*;
import labTic.business.RestaurantService;
import labTic.business.entities.Booking;
import labTic.business.entities.Client;
import labTic.business.entities.Restaurant;
import labTic.business.entities.Tables;
import labTic.persistence.BookingRepository;
import labTic.persistence.RestaurantRepository;
import labTic.persistence.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.util.List;

public class RestaurantRemoteService implements RestaurantRemoteManager {

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public TableRepository getTableRepository() {
        return restaurantService.getTableRepository();
    }

    @Override
    public RestaurantRepository getRestaurantRepository() {
        return restaurantService.getRestaurantRepository();
    }

    @Override
    public BookingRepository getBookingRepository() {
        return restaurantService.getBookingRepository();
    }

    @Override
    public void addRestaurant(long rut, String name, String address, String style, String phoneNumber, String area, String food, String price) throws InvalidRestaurantInformationException, RestaurantAlreadyExistsException {
        restaurantService.addRestaurant(rut, name, address, style, phoneNumber, area, food, price);
    }

    @Override
    public void updateRestaurant(long rut, String food_type) throws RestaurantDoesNotExistException {
        restaurantService.updateRestaurant(rut, food_type);
    }

    @Override
    public void addHours(long rut, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) throws RestaurantDoesNotExistException {
        restaurantService.addHours(rut, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
    }

    @Override
    public void addPic(long rut, byte[] pic) throws RestaurantDoesNotExistException {
        restaurantService.addPic(rut, pic);
    }

    @Override
    public List<Restaurant> findAllByFoodType(String food_type) {
        return restaurantService.findAllByFoodType(food_type);
    }

    @Override
    public Restaurant findOneByName(String name) {
        return restaurantService.findOneByName(name);
    }

    @Override
    public Restaurant findOneByRut(long rut) {
        return restaurantService.findOneByRut(rut);
    }

    @Override
    public List<Restaurant> filter(List<String> area, List<String> foodtype, String address, List<String> pricerange, Float rating, List<String> style, String name) {
        return restaurantService.filter(area, foodtype, address, pricerange, rating, style, name);
    }

    @Override
    public void book(Long rut, String alias, int assistants) throws FullRestaurantException, NoAvailableTablesException {
        restaurantService.book(rut, alias, assistants);
    }

    @Override
    public void release(Restaurant restaurant, String alias) {
        restaurantService.release(restaurant, alias);
    }

    @Override
    public List<Restaurant> showActiveRestaurants() {
        return restaurantService.showActiveRestaurants();
    }

    @Override
    public List<Restaurant> showRestaurantsNow() {
        return restaurantService.showRestaurantsNow();
    }

    @Override
    public void confirmBooking(Booking booking) {
        restaurantService.confirmBooking(booking);
    }

    @Override
    public void rejectBooking(Booking booking) {
        restaurantService.rejectBooking(booking);
    }

    @Override
    public void toggleAvailability(Restaurant restaurant) {
        restaurantService.toggleAvailability(restaurant);
    }

    @Override
    public String[] getTimeTable(Restaurant restaurant, int day) {
        return restaurantService.getTimeTable(restaurant, day);
    }

    @Override
    public List<Restaurant> getAvailableRestaurantsNow() {
        return restaurantService.getAvailableRestaurantsNow();
    }

    @Override
    public List<Booking> getBookings(Restaurant restaurant) {
        return restaurantService.getBookings(restaurant);
    }

    @Override
    public List<Booking> getNewBookings(Restaurant restaurant) {
        return restaurantService.getNewBookings(restaurant);
    }

    @Override
    public Integer getFee(Restaurant restaurant) {
        return restaurantService.getFee(restaurant);
    }

    @Override
    public List<Tables> getCustomerTables(Restaurant restaurant, Client client) throws NoBookingsMadeException {
        return restaurantService.getCustomerTables(restaurant, client);
    }
}
