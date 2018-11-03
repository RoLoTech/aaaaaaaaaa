package labTic.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import labTic.MainApp;
import labTic.services.entities.Restaurant;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class RestaurantViewController implements Initializable {

   // public Restaurant restaurant;

    private Restaurant restaurant;

    @FXML
    private Text titulo;

    @FXML
    private Text horario;

    @FXML
    private Text ubicacion;

    @FXML
    private Text tipoComida;

    @FXML
    private Text precio;


    @FXML
    void btnReservar(MouseEvent event) {

    }

    @FXML
    void btnVolver(MouseEvent event) throws Exception {
        MainApp.changeScene("Client/Restaurantes.fxml", event);
    }

    @FXML
    void loginTab(MouseEvent event) {

    }

    @FXML
    void signupTab(MouseEvent event) {

    }

    void setRestaurant(Restaurant newRestaurant){
        this.restaurant = newRestaurant;
        titulo.setText(restaurant.getName());
        ubicacion.setText(restaurant.getAddress());
        tipoComida.setText(restaurant.getFoodtype());
        horario.setText("L a V de " +restaurant.getOpeningTime()+ " a "+ restaurant.getClosingTime());
        precio.setText(restaurant.getPriceRange());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
