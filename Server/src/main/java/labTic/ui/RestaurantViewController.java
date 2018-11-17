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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import labTic.ClientMain;
import labTic.services.RestaurantService;
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

@Component
public class RestaurantViewController implements Initializable {

   // public Restaurant restaurant;

    private Restaurant restaurant;

    private Client client;

    @Autowired
    RestaurantService restaurantService;

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
    private Text txtDescripcion;

    @FXML
    private TextField txtPersonas;

    @FXML
    private TextField txtHora;

    @FXML
    void tabDescripcion(ActionEvent event) {

    }

    @FXML
    void tabVistaGeneral(ActionEvent event) {

    }

    @FXML
    void btnReservar(MouseEvent event) {
        try {
            int cantPersonas = Integer.valueOf(txtPersonas.getText());
            restaurantService.book(restaurant.getRut(),client.getFirstName()+" "+client.getLastName(),cantPersonas);

            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory((ClientMain.getContext()::getBean));

            Parent root = loader.load(ReservaPendienteController.class.getResourceAsStream("Client/ReservaPendiente.fxml"));
            ReservaPendienteController controller = loader.getController();
            controller.setRestaurantAndClient(restaurant,client,cantPersonas,event);

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(RestaurantController.class.getResource("Client/Restaurant.css").toExternalForm());

        }catch(NumberFormatException nfe){
            ClientMain.showAlert("Error","Introduzca un numero en cantidad de personas");
        }catch(FullRestaurantException fre){
            ClientMain.showAlert("Error","Restaurante lleno");
        }catch(NoAvailableTablesException nate){
            ClientMain.showAlert("Error","No hay mesas disponibles");
        }catch(IOException ie){
            ie.printStackTrace();
        }
        //if(txtHora.getText()!=null && txtPersonas.getText()!=null){
           /* try{
                int cantPersonas = Integer.valueOf(txtPersonas.getText());
                LocalTime localTime = LocalTime.parse(txtHora.getText());
                //System.out.println(restaurant.getRut());

                //usar timers, invokeLater
                /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Esperando Verificacion");
                alert.setHeaderText(null);
                alert.setContentText("Espere, por favor...");
                alert.showAndWait();

                //ClientMain.showAlert("Esperando verificacion del Restaurante","Espere, porfavor");
            }catch(NumberFormatException nfe){
                ClientMain.showAlert("Error","Asegurese de que ingreso bien los datos");
            }catch(FullRestaurantException fre) {
                ClientMain.showAlert("Error", "Restaurante lleno");
            }
        }*/

    }

    @FXML
    void btnVolver(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory((ClientMain.getContext()::getBean));

        Parent root = loader.load(RestaurantController.class.getResourceAsStream("Client/Restaurantes.fxml"));
        RestaurantController controller = loader.getController();
        controller.setClient(client);

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.getScene().getStylesheets().add(RestaurantController.class.getResource("Client/Restaurant.css").toExternalForm());
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
        horario.setText("Lunes: "+restaurant.getHoursMonday()+" / "+"Martes: "+restaurant.getHoursTuesday()
            +"\n"+"Miercoles: "+restaurant.getHoursWednesday()+" / "+"Jueves: "+restaurant.getHoursThursday()
                +"\n"+"Viernes: "+restaurant.getHoursFriday()+" / "+ "Sabado: "+restaurant.getHoursSaturday()
                    +"\n"+"Domingo: "+restaurant.getHoursSunday());
        precio.setText(restaurant.getPriceRange());
        try{
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(restaurant.getProfilePicture()));
        Image image = SwingFXUtils.toFXImage(img, null);
        restaurantProfilePic.setImage(image);
        txtDescripcion.setText(restaurant.getDescription());
        }catch(Exception e){
            e.printStackTrace();

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
