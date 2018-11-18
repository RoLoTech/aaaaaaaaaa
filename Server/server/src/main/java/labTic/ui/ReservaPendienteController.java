package labTic.ui;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import labTic.ClientMain;
import labTic.persistence.BookingRepository;
import labTic.services.RestaurantService;
import labTic.services.entities.Booking;
import labTic.services.entities.Client;
import labTic.services.entities.Restaurant;
import labTic.services.exceptions.FullRestaurantException;
import labTic.services.exceptions.NoAvailableTablesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

@Component
public class ReservaPendienteController implements Initializable {

    private Restaurant restaurant;

    private Client client;

    private boolean cancelar = false;

    private Booking booking;

    private String alias;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RestaurantService restaurantService;

    private Event event;

    @FXML
    private ImageView imgRestaurant;

    @FXML
    private ImageView imgLoading;

    @FXML
    private Text txtRestaurant;

    @FXML
    private Text txtUbicacion;

    @FXML
    private Text txtPersonas2;
    @FXML
    private Text txtTelefono;

    @FXML
    private Text txtDireccion;

    public void setRestaurantAndClient(Restaurant restaurant, Client client, int cantPersonas, Event event,String alias) {
        this.event = event;
        this.client = client;
        this.restaurant = restaurant;
        this.restaurant = restaurant;
        this.alias = alias;
        txtPersonas.setText(""+cantPersonas);
        txtDireccion.setText(restaurant.getAddress());
        txtRestaurant.setText(restaurant.getName());
        txtUbicacion.setText(restaurant.getArea());
        txtTelefono.setText(restaurant.getPhone());
        try{
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(restaurant.getProfilePicture()));
            Image image = SwingFXUtils.toFXImage(img, null);
            imgRestaurant.setImage(image);
        }catch(Exception e){
            e.printStackTrace();
        } }

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
            controller.setBooking(booking);

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
        booking.setRejected();
        bookingRepository.save(booking);
        cancelar = true;
        ClientMain.showAlert("Cancelado","Reserva Cancelada");
        goBackToRestaurantView(event);

    }

    @FXML
    void btnVolver(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        long startTime = System.currentTimeMillis();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run(){
                long thisTime = System.currentTimeMillis();
                booking = restaurantService.getClientBookingsOnHold(restaurant,alias); //Aca va la funcion que devuelve la reserva de la base de datos

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
                    booking.setRejected();
                    bookingRepository.save(booking);
                    ClientMain.showAlert("Error","La reserva no ha sido confirmada");
                    goBackToRestaurantView(event);
                    timer.cancel();
                }
                if(cancelar == true){
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 5000);

    }
}
