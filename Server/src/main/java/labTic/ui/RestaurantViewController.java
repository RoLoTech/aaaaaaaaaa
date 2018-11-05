package labTic.ui;

import javafx.embed.swing.SwingFXUtils;
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
import labTic.services.entities.Client;
import labTic.services.entities.Restaurant;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class RestaurantViewController implements Initializable {

   // public Restaurant restaurant;

    private Restaurant restaurant;

    private Client client;

    @FXML
    private ImageView restaurantProfilePic;

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
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory((ClientMain.getContext()::getBean));

        Parent root = loader.load(SignUpController.class.getResourceAsStream("Client/Restaurantes.fxml"));
        RestaurantController controller = loader.getController();
        controller.setClient(client);

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.getScene().getStylesheets().add(SignUpController.class.getResource("Client/LogInSignUp.css").toExternalForm());
    }


    @FXML
    void loginTab(MouseEvent event) {

    }

    @FXML
    void signupTab(MouseEvent event) {

    }

    void setClient(Client client){
        this.client = client;
    }

    void setRestaurant(Restaurant newRestaurant){
        this.restaurant = newRestaurant;
        titulo.setText(restaurant.getName());
        ubicacion.setText(restaurant.getAddress());
        tipoComida.setText(restaurant.getFoodtype());
//        horario.setText("L a V de " +restaurant.getOpeningTime()+ " a "+ restaurant.getClosingTime());
        precio.setText(restaurant.getPriceRange());
        try{
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(restaurant.getProfilePicture()));
        Image image = SwingFXUtils.toFXImage(img, null);
        restaurantProfilePic.setImage(image);
        }catch(Exception e){

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
