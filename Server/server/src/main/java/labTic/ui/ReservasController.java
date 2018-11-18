package labTic.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import labTic.services.RestaurantService;
import labTic.services.entities.Booking;
import labTic.services.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

@Component
public class ReservasController implements Initializable {

    private Restaurant restaurant;

    @Autowired
    private RestaurantService restaurantService;


    @FXML
    private TableView<Booking> table1; //confirmadas
    @FXML
    private TableColumn<Booking,String> cAlias;
    @FXML
    private TableColumn<Booking,Integer> cCantidad;

    @FXML
    private TableView<Booking> table2; //sin confirmar
    @FXML
    private TableColumn<Booking, String> nCAlias;
    @FXML
    private TableColumn<Booking, Integer> nCcantidad;

    @FXML
    void clickTable1(MouseEvent event) {

    }

    @FXML
    void clickTable2(MouseEvent event) {

    }

    @FXML
    void perfilTab(MouseEvent event) {

    }

    @FXML
    void reservasTab(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ObservableList<Booking> newBookingsObservableList = FXCollections.observableArrayList();
                List<Booking> newBookingsSimpleList = restaurantService.getNewBookings(restaurant);
                for(int i = 0; i<newBookingsSimpleList.size();i++){
                    newBookingsObservableList.add(newBookingsSimpleList.get(i));
                }
                table2.setItems(newBookingsObservableList);
                nCAlias.setCellValueFactory(new PropertyValueFactory<Booking,String>("alias"));
                nCcantidad.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("assistants"));

                ObservableList<Booking> confirmedBookingsObservableList = FXCollections.observableArrayList();
                List<Booking> confirmedBookingsSimpleList = restaurantService.getUnFinishedBookings(restaurant);
                for(int i = 0; i<confirmedBookingsSimpleList.size();i++){
                    confirmedBookingsObservableList.add(confirmedBookingsSimpleList.get(i));
                }
                table1.setItems(confirmedBookingsObservableList);
                cAlias.setCellValueFactory(new PropertyValueFactory<Booking,String>("alias"));
                cCantidad.setCellValueFactory(new PropertyValueFactory<Booking,Integer>("assistants"));


            }
        };
        timer.scheduleAtFixedRate(timerTask,0,5000);


        /*
        * Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run(){
                long thisTime = System.currentTimeMillis();
                booking = new Booking(); //Aca va la funcion que devuelve la reserva de la base de datos

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
        timer.scheduleAtFixedRate(timerTask, 0, 5000);*/




    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    @FXML
    private ToggleButton btnDisponible;

    @FXML
    void btnDisponibleClick(MouseEvent event) {
        restaurantService.toggleAvailability(restaurant);
        if(restaurant.getAvailability())
            btnDisponible.setText("Disponible");
        else
            btnDisponible.setText("No Disponible");


    }
}
