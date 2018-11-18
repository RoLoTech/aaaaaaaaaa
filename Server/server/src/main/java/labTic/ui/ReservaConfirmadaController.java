package labTic.ui;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import labTic.services.entities.Booking;
import labTic.services.entities.Client;
import labTic.services.entities.Restaurant;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

@Component
public class ReservaConfirmadaController {

    private Restaurant restaurant;

    private Client client;

    private Booking booking;

    private String alias;

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

        }
        //txtMesa.setText();

    }
    void setClient(Client client){
        this.client = client;
    }
    void setClient(String alias){this.alias = alias;}
    void setBooking(Booking booking){
        this.booking = booking;
        txtMesa.setText(""+booking.getTable().getId());
        txtPersonas.setText(""+booking.getAssistants());
        txtHora.setText(booking.getStartDate().toString());
    }

}
