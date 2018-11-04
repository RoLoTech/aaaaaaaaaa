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

        // Verifico si el restaurante no existe

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

    public void addPic (long rut, byte[] pic) throws RestaurantNoExists {
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

    public List<Restaurant> multipleFiltering(String area, String foodtype, String address, String pricerange, Float rating, String style, String name) {
        return restaurantRepository.findRestaurantsByAreaAndFoodtypeAndAddressContainingAndPriceRangeAndRatingAndStyleAndNameContaining(area, foodtype, address, pricerange, rating, style, name);

    }

    public List<Restaurant> findByArea(String area) {
        return restaurantRepository.findByArea(area);
    }

    public List<Restaurant> findAll() {
        return (List<Restaurant>) restaurantRepository.findAll();
    }

    public List<Restaurant> filter(String area, String foodtype, String address, String pricerange, Float rating, String style, String name) {
        BooleanBuilder builder = new BooleanBuilder();
        final QRestaurant restaurant = QRestaurant.restaurant;
        if (area != null && area.trim().length() != 0) {
            builder.and(restaurant.area.eq(area));
        }
        if (foodtype != null && foodtype.trim().length() != 0) {
            builder.and(restaurant.foodtype.contains(foodtype));
        }
        if (address != null && address.trim().length() != 0) {
            builder.and(restaurant.address.like(address));
        }
        if (pricerange != null && pricerange.trim().length() != 0) {
            builder.and(restaurant.priceRange.eq(pricerange));
        }
        if (rating != null) {
            builder.and(restaurant.rating.eq(rating));
        }
        if (style != null && style.trim().length() != 0) {
            builder.and(restaurant.style.eq(style));
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

    public void book(Restaurant restaurant, LocalTime startDate, String alias) throws FullRestaurantException {
        Tables tables = null;
        Iterable<Tables> result = tableRepository.findAllByRestaurantRut(restaurant.getRut());
        List<Tables> list = new ArrayList<>();
        for (Tables tableToIterate : result) {
            list.add(tableToIterate);
        }
        boolean succesfulBooking = false;
        for (int i = 0; i < list.size() && succesfulBooking == false; i++) {
            if (list.get(i).getOccupant() == null) {
                list.get(i).setOccupant(alias);
                list.get(i).setStartReservation(startDate);
                tables = list.get(i);
                succesfulBooking = true;
            }
        }
        if (succesfulBooking == false) {
            throw new FullRestaurantException();
        }
        Booking booking = new Booking((restaurant.hashCode() + alias.hashCode()), restaurant, alias, tables);
        bookingRepository.save(booking);

    }

    public void release(Restaurant restaurant, String alias) {
        Iterable<Tables> result = tableRepository.findAllByRestaurantRut(restaurant.getRut());
        List<Tables> list = new ArrayList<>();
        for (Tables tables : result) {
            list.add(tables);
        }
        boolean succesfulRelease = false;
        for (int i = 0; i < list.size() && succesfulRelease == false; i++) {
            if (list.get(i).getOccupant() != null && list.get(i).getOccupant().equals(alias)) {
                list.get(i).setOccupant(null);
                list.get(i).setStartReservation(null);
                succesfulRelease = true;
            }
        }
    }

   /* public List<Restaurant> filterBy(String... args) {
        List<Restaurant> results = new ArrayList<Restaurant>();
        if (args.length == 1){
            results = RestaurantRepository.findAllBy1args[0];
        }

        return results;
    } */
}
