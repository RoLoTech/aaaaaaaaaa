package labTic.ui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import labTic.ClientMain;
import labTic.services.RestaurantService;
import labTic.services.entities.Client;
import labTic.services.entities.Restaurant;
import labTic.services.exceptions.FullRestaurantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class ReservaPendienteController {

    private Restaurant restaurant;

    private Client client;

    @Autowired
    RestaurantService restaurantService;

    @FXML
    private ImageView imgRestaurant;

    @FXML
    private ImageView imgLoading;

    @FXML
    private Text txtRestaurant;

    @FXML
    private Text txtUbicacion;

    @FXML
    private Text txtPersonas;

    @FXML
    private Text txtTelefono;

    @FXML
    private Text txtDireccion;

    public void setRestaurantAndClient(Restaurant restaurant, Client client, int cantPersonas, Event event) throws IOException {
        this.client = client;
        this.restaurant = restaurant;
        LocalTime localTime = LocalTime.now();
        try {
            restaurantService.bookWithoutTable(restaurant.getRut(), localTime, client.getFirstName() + " " + client.getLastName(), cantPersonas);
            // Clase en la que está el código a ejecutar
            //initial time = System.now();
            TimerTask timerTask = new TimerTask() {
                public void run() {

                    //reservation  = check reservation
                    //if reservation.confirmed == true
                    //      confirmed = true
                    //      timer.cancel()
                    //if reservation.rejected == true
                    //      rejected = true
                    //      timer.cancel()
                    //if initial time - system.now() > 10 minutos
                    //      reservation.rejected = true
                    //      timer.cancel()

                }
            };

            // Aquí se pone en marcha el timer cada segundo.
            Timer timer = new Timer();
            // Dentro de 0 milisegundos avísame cada 1000 milisegundos
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
            //if confirmed == true
            //      showAlert - Reserva confirmada
            //      cambiamos de ventana a la de reserva confirmada
            //if rejected == true
            //      showAlert - Reserva rechazada
            //      cambiamos de ventana a la de buscar restaurantes
        }catch(FullRestaurantException fre){

            ClientMain.showAlert("Error","Restaurante lleno");

            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory((ClientMain.getContext()::getBean));

            Parent root = loader.load(RestaurantViewController.class.getResourceAsStream("Client/RestaurantView.fxml"));
            RestaurantViewController controller = loader.getController();
            controller.setRestaurant(restaurant);
            controller.setClient(client);

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(RestaurantViewController.class.getResource("Client/RestaurantView.css").toExternalForm());
        }
    }



    @FXML
    void btnCancelar(MouseEvent event) {

    }

    @FXML
    void btnVolver(MouseEvent event) {

    }

}
