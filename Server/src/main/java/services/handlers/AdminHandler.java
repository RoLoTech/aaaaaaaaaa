package services.handlers;


import services.entities.Client;
import services.entities.Restaurant;

public interface AdminHandler {

    public void addRestaurant(Restaurant restaurant);

    public Restaurant findRestaurant(int id);

    public void removeRestaurant(int id);

    public Client addClient(Client client);

    public void findClient(int id);

    public void removeClient(int id);
}