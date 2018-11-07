package labTic.services;

import com.querydsl.core.BooleanBuilder;
import javafx.scene.control.Tab;
import labTic.persistence.BookingRepository;
import labTic.persistence.TableRepository;
import labTic.services.entities.Booking;
import labTic.services.entities.QRestaurant;
import labTic.services.entities.Tables;
import labTic.services.exceptions.FullRestaurantException;
import labTic.services.exceptions.RestaurantAlreadyExists;
import labTic.services.exceptions.InvalidRestaurantInformation;
import labTic.services.exceptions.RestaurantNoExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import labTic.persistence.RestaurantRepository;
import labTic.services.entities.Restaurant;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class RestaurantService {

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
            throws InvalidRestaurantInformation, RestaurantAlreadyExists {

        if (name == null || "".equals(name) || address == null || "".equals(address) || style == null || "".equals(style) ||
                phoneNumber == null || "".equals(phoneNumber) || area == null || "".equals(area) || food == null || "".equals(food)
                || price == null || "".equals(price)) {

            throw new InvalidRestaurantInformation("Alguno de los datos ingresados no es correcto");

        }

        if (restaurantRepository.findOneByRut(rut) != null) {
            throw new RestaurantAlreadyExists();
        }

        Restaurant oRestaurant = new Restaurant(rut, name, address, style, phoneNumber, area, food, price);

        restaurantRepository.save(oRestaurant);

    }

    public void updateRestaurant(long rut, String food_type) throws RestaurantNoExists {
        if (restaurantRepository.findOneByRut(rut) == null) {
            throw new RestaurantNoExists();
        }
        Restaurant oRestaurant = restaurantRepository.findOneByRut(rut);
        oRestaurant.setFood_type(food_type);
        restaurantRepository.save(oRestaurant);
    }

    public void addHours(long rut, String monday, String tuesday, String wednesday, String thursday,
                         String friday, String saturday, String sunday) throws RestaurantNoExists {
        if (restaurantRepository.findOneByRut(rut) == null) {
            throw new RestaurantNoExists();
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

    public void addPic(long rut, byte[] pic) throws RestaurantNoExists {
        if (restaurantRepository.findOneByRut(rut) == null) {
            throw new RestaurantNoExists();
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

    public List<Restaurant> multipleFiltering(String area, String foodtype, String address, String pricerange, Float rating, String style, String name) {
        return restaurantRepository.findRestaurantsByAreaAndFoodtypeAndAddressContainingAndPriceRangeAndRatingAndStyleAndNameContaining(area, foodtype, address, pricerange, rating, style, name);

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

    public void book(Long rut, LocalTime startDate, String alias) throws FullRestaurantException {
        Tables tables = null;
        Iterable<Tables> result = tableRepository.findAllByRestaurantRut(rut);
        List<Tables> list = new ArrayList<>();
        for (Tables tableToIterate : result) {
            list.add(tableToIterate);
        }
        boolean successfulBooking = false;
        for (int i = 0; i < list.size() && !successfulBooking; i++) {
            if (list.get(i).getOccupant() == null) {
                list.get(i).setOccupant(alias);
                list.get(i).setStartReservation(startDate);
                tables = list.get(i);
                successfulBooking = true;
            }
        }
        if (!successfulBooking) {
            throw new FullRestaurantException();
        }
        Booking booking = new Booking((rut.hashCode() + alias.hashCode()), rut, alias, tables);
        bookingRepository.save(booking);

    }

    public void release(Restaurant restaurant, String alias) {
        Iterable<Tables> result = tableRepository.findAllByRestaurantRut(restaurant.getRut());
        List<Tables> list = new ArrayList<>();
        for (Tables tables : result) {
            list.add(tables);
        }
        boolean successfulRelease = false;
        for (int i = 0; i < list.size() && !successfulRelease; i++) {
            if (list.get(i).getOccupant() != null && list.get(i).getOccupant().equals(alias)) {
                bookingRepository.findByAlias(alias).setFinished();
                list.get(i).setOccupant(null);
                list.get(i).setStartReservation(null);
                successfulRelease = true;
            }
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
            if(LocalTime.now().isBefore(openingTime) || LocalTime.now().isAfter(closingTime)){
                candidates.remove(candidate);
            }
        }
        return candidates;
    }

    public Integer getFee(Restaurant restaurant) {
        return restaurant.getCompletedReservations() * 100;
    }

}
