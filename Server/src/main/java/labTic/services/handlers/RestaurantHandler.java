package labTic.services.handlers;

public interface RestaurantHandler {
    public void bookTable(int restaurantID, int tableNumber);

    public void releaseTable(int restaurantID, int tableNumber);
}
