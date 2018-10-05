package labTic.services.exceptions;

public class RestaurantNoExists extends Exception {

    public RestaurantNoExists(String message) {
        super(message);
    }

    public RestaurantNoExists() {
    }
}
