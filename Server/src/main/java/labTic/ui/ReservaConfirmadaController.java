package labTic.ui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import labTic.services.entities.Client;
import labTic.services.entities.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class ReservaConfirmadaController {

    private Restaurant restaurant;

    private Client client;

    @FXML
    private ImageView imgRestaurant;

    @FXML
    private Text txtRestaurant;

    @FXML
    private Text txtHora;

    @FXML
    private Text txtUbicacion;

    @FXML
    private Text txtPersonas;

    @FXML
    private Text txtTelefono;

    @FXML
    private Text txtDireccion;

    @FXML
    private Text txtMesa;

    @FXML
    void btnVolver(MouseEvent event) {

    }

    void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;

    }
    void setClient(Client client){
        this.client = client;
    }

}
