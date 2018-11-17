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
import labTic.services.entities.Booking;
import labTic.services.entities.Client;
import labTic.services.entities.Restaurant;
import labTic.services.exceptions.FullRestaurantException;
import labTic.services.exceptions.NoAvailableTablesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

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

    public void setRestaurantAndClient(Restaurant restaurant, Client client, int cantPersonas, Event event) {
        this.client = client;
        this.restaurant = restaurant;
        long startTime = System.currentTimeMillis();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run(){
                long thisTime = System.currentTimeMillis();
                Booking booking = new Booking(); //Aca va la funcion que devuelve la reserva de la base de datos

                if(booking.getConfirmed() == true){
                    ClientMain.showAlert("Exito","Reserva Confirmada");
                    goToReservaConfirmadaController(event);
                    timer.cancel();
                }
                if(booking.getRejected()==true){
                    ClientMain.showAlert("Error","Reserva rechazada por el restaurante");
                    goBackToRestaurantView(event);
                    timer.cancel();
                }
                if( thisTime-startTime > 600000 ){
                    ClientMain.showAlert("Error","La reserva no ha sido confirmada");
                    goBackToRestaurantView(event);
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 5000);
    }

    void goBackToRestaurantView(Event event){
        try {
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void goToReservaConfirmadaController(Event event){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory((ClientMain.getContext()::getBean));

            Parent root = loader.load(ReservaConfirmadaController.class.getResourceAsStream("Client/ReservaConfirmada.fxml"));
            ReservaConfirmadaController controller = loader.getController();
            controller.setRestaurant(restaurant);
            controller.setClient(client);

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(ReservaConfirmadaController.class.getResource("Client/RestaurantView.css").toExternalForm());
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    @FXML
    void btnCancelar(MouseEvent event) {

    }

    @FXML
    void btnVolver(MouseEvent event) {

    }

}
