package labTic.services;

import com.querydsl.core.BooleanBuilder;
import labTic.persistence.BookingRepository;
import labTic.persistence.TableRepository;
import labTic.services.entities.*;
import labTic.services.exceptions.*;
import labTic.services.rmi.RestaurantManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import labTic.persistence.RestaurantRepository;
import labTic.services.entities.QRestaurant;
import labTic.services.entities.QClient;
import labTic.services.entities.QTable;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class RestaurantService implements RestaurantManager {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public TableRepository getTableRepository() {
        return tableRepository;
    }

    public RestaurantRepository getRestaurantRepository() {
        return restaurantRepository;
    }

    public BookingRepository getBookingRepository() {
        return bookingRepository;
    }

    public void addRestaurant(long rut, String name, String address, String style, String phoneNumber, String area, String food, String price)
            throws InvalidRestaurantInformationException, RestaurantAlreadyExistsException {

        if (name == null || "".equals(name) || address == null || "".equals(address) || style == null || "".equals(style) ||
                phoneNumber == null || "".equals(phoneNumber) || area == null || "".equals(area) || food == null || "".equals(food)
                || price == null || "".equals(price)) {

            throw new InvalidRestaurantInformationException("Alguno de los datos ingresados no es correcto");

        }

        if (restaurantRepository.findOneByRut(rut) != null) {
            throw new RestaurantAlreadyExistsException();
        }

        Restaurant oRestaurant = new Restaurant(rut, name, address, style, phoneNumber, area, food, price);

        restaurantRepository.save(oRestaurant);

    }

    public void updateRestaurant(long rut, String food_type) throws RestaurantDoesNotExistException {
        if (restaurantRepository.findOneByRut(rut) == null) {
            throw new RestaurantDoesNotExistException();
        }
        Restaurant oRestaurant = restaurantRepository.findOneByRut(rut);
        oRestaurant.setFood_type(food_type);
        restaurantRepository.save(oRestaurant);
    }

    public void addHours(long rut, String monday, String tuesday, String wednesday, String thursday,
                         String friday, String saturday, String sunday) throws RestaurantDoesNotExistException {
        if (restaurantRepository.findOneByRut(rut) == null) {
            throw new RestaurantDoesNotExistException();
        }
        Restaurant oRestaurant = restaurantRepository.findOneByRut(rut);
        oRestaurant.setHoursMonday(monday);
        oRestaurant.setHoursTuesday(tuesday);
        oRestaurant.setHoursWednesday(wednesday);
        oRestaurant.setHoursThursday(thursday);
        oRestaurant.setHoursFriday(friday);
        oRestaurant.setHoursSaturday(saturday);
        oRestaurant.setHoursSunday(sunday);
        restaurantRepository.save(oRestaurant);
    }

    public void addPic(long rut, byte[] pic) throws RestaurantDoesNotExistException {
        if (restaurantRepository.findOneByRut(rut) == null) {
            throw new RestaurantDoesNotExistException();
        }
        Restaurant oRestaurant = restaurantRepository.findOneByRut(rut);
        oRestaurant.setProfilePicture(pic);
        restaurantRepository.save(oRestaurant);
    }


    public List<Restaurant> findAllByFood_type(String food_type) {
        return restaurantRepository.findAllByFoodtype(food_type);
    }

    public Restaurant findOneByName(String name) {
        return restaurantRepository.findOneByName(name);
    }

    public Restaurant findOneByRut(long rut) {
        return restaurantRepository.findOneByRut(rut);
    }

    public List<Restaurant> findByArea(String area) {
        return restaurantRepository.findByArea(area);
    }

    public List<Restaurant> findAll() {
        return (List<Restaurant>) restaurantRepository.findAll();
    }

    public List<Restaurant> filter(List<String> area, List<String> foodtype, String address, List<String> pricerange, Float rating, List<String> style, String name) {
        BooleanBuilder builder = new BooleanBuilder();
        final QRestaurant restaurant = QRestaurant.restaurant;
        if (area != null && area.size() != 0) {
            for (String element : area) {
                builder.or(restaurant.area.eq(element));
            }
        }
        if (foodtype != null && foodtype.size() != 0) {
            for (String element : foodtype) {
                builder.or(restaurant.foodtype.contains(element));
            }
        }
        if (address != null && address.trim().length() != 0) {
            builder.and(restaurant.address.like(address));
        }
        if (pricerange != null && pricerange.size() != 0) {
            for (String element : pricerange) {
                builder.or(restaurant.priceRange.eq(element));
            }
        }
        if (rating != null) {
            builder.and(restaurant.rating.eq(rating));
        }
        if (style != null && style.size() != 0) {
            for (String element : style) {
                builder.or(restaurant.style.eq(element));
            }
        }
        if (name != null && name.trim().length() != 0) {
            builder.and(restaurant.name.like(name));
        }

        Iterable<Restaurant> restaurants = restaurantRepository.findAll(builder);

        List<Restaurant> list = new ArrayList<>();
        for (Restaurant rest : restaurants) {
            list.add(rest);
        }

        return list;
    }

    public void book(Long rut, String alias, int assistants) throws FullRestaurantException, NoAvailableTablesException {
        if (this.getCurrentCapacity(restaurantRepository.findOneByRut(rut)) == 0) {
            throw new FullRestaurantException();
        }
        if (this.getCurrentCapacity(restaurantRepository.findOneByRut(rut)) < assistants) {
            throw new NoAvailableTablesException();
        }
        int unassignedAssistants = assistants;
        List<Tables> list = tableRepository.findAllByRestaurantRutAndCapacityGreaterThanEqualAndOccupantIsNull(rut, assistants);
        if (list.size() == 0) {
            list = this.tableRepository.findAllByRestaurantRutAndOccupantIsNull(rut);
            int index = 0;
            while (unassignedAssistants > 0) {
                unassignedAssistants = unassignedAssistants - list.get(index).getCapacity();
                Booking booking = new Booking((long) (rut.hashCode() + alias.hashCode()), rut, alias, list.get(index));
                bookingRepository.save(booking);
                index++;
            }
        }
    }

    public void release(Restaurant restaurant, String alias) {
        List<Tables> result = tableRepository.findAllByRestaurantRutAndOccupant(restaurant.getRut(), alias);
        for (Tables table : result) {
            table.setOccupant(null);
        }
        List<Booking> bookingsToFinalize = (List<Booking>) bookingRepository.findByAliasAndConfirmedAndFinished(alias, true, false);
        for (Booking booking : bookingsToFinalize) {
            booking.setFinished();
        }
    }

    public List<Restaurant> showActiveRestaurants() {
        return restaurantRepository.findAllByAvailability(true);
    }

    public List<Restaurant> showRestaurantsNow() {

        return null;
    }

    public void confirmBooking(Booking booking) {
        booking.setConfirmed();
    }

    public void rejectBooking(Booking booking) {
        booking.setRejected();
    }

    public void toggleAvailability(Restaurant restaurant) {
        if (restaurant.getAvailability()) {
            restaurant.setAvailability(false);
        } else {
            restaurant.setAvailability(true);
        }
    }

    public String[] getTimeTable(Restaurant restaurant, int day) {
        String[] times = null;
        switch (day) {
            case 1:
                times = restaurant.getHoursMonday().split(" ");
                break;
            case 2:
                times = restaurant.getHoursTuesday().split(" ");
                break;
            case 3:
                times = restaurant.getHoursMonday().split(" ");
                break;
            case 4:
                times = restaurant.getHoursMonday().split(" ");
                break;
            case 5:
                times = restaurant.getHoursMonday().split(" ");
                break;
            case 6:
                times = restaurant.getHoursMonday().split(" ");
                break;
            default:
                times = restaurant.getHoursSunday().split(" ");
        }
        return times;
    }

    public List<Restaurant> getAvailableRestaurantsNow() {
        List<Restaurant> candidates = restaurantRepository.findAllByAvailability(true);
        int day = LocalDateTime.now().getDayOfWeek().getValue();
        for (Restaurant candidate : candidates) {
            String[] timeTable = this.getTimeTable(candidate, day);
            LocalTime openingTime = LocalTime.parse(timeTable[0]);
            LocalTime closingTime = LocalTime.parse(timeTable[1]);
            if (LocalTime.now().isBefore(openingTime) || LocalTime.now().isAfter(closingTime)) {
                candidates.remove(candidate);
            }
        }
        return candidates;
    }

    public List<Booking> getBookings(Restaurant restaurant) {
        return bookingRepository.findAllByRut(restaurant.getRut());
    }

    public List<Booking> getNewBookings(Restaurant restaurant) {
        return bookingRepository.findAllByRutAndConfirmedAndFinishedAndRejected(restaurant.getRut(), false, false, false);
    }

    public Integer getFee(Restaurant restaurant) {
        return restaurant.getCompletedReservations() * 100;
    }

    public Integer getCurrentCapacity(Restaurant restaurant) {
        int capacity = 0;
        List<Tables> result = (List<Tables>) tableRepository.findAllByRestaurantRutAndOccupantIsNull(restaurant.getRut());
        for (Tables table : result) {
            capacity = capacity + table.getCapacity();
        }
        return capacity;
    }

    List<Tables> getCustomerTables(Restaurant restaurant, Client client) throws NoBookingsMadeException {
        if (tableRepository.findAllByRestaurantRutAndOccupant(restaurant.getRut(), client.getFirstName() + " " + client.getLastName()).size() == 0) {
            throw new NoBookingsMadeException();
        }
        return tableRepository.findAllByRestaurantRutAndOccupant(restaurant.getRut(), client.getFirstName() + " " + client.getLastName());
    }

    public Boolean getClientBookingsOnHold(Restaurant restaurant, Client client) {
        if(bookingRepository.findByAliasAndFinished(client.getFirstName() + client.getLastName(), false).getConfirmed()){
            return true;
        }
        return false;
    }
}
