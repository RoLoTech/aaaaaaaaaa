package labTic.ui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import labTic.ClientMain;
import labTic.ManagerMain;
import labTic.RestaurantMain;
import labTic.services.RestaurantService;
import labTic.services.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogInRestaurantController {

    @FXML
    private TextField rut;

    @Autowired
    private RestaurantService restaurantService;

    @FXML
    private PasswordField pass;

    @FXML
    void btnLogin(MouseEvent event) {
        try{
            long sUser = Long.parseLong(this.rut.getText());
            String sPassword = pass.getText();

            if("".equals(sUser) || sPassword==null || "".equals(sPassword)) {
                ClientMain.showAlert("Error","No Deje Campos Vacios");
            }else {
                try{
                    Restaurant restaurant = (Restaurant)restaurantService.findOneByRut(sUser);
                    if(restaurant.getPassword().equals(sPassword)) {
                        ClientMain.showAlert("Exito", "Usuario Logueado Correctamente");
                        goToProfile(restaurant,event);
                    }
                    else {
                        ClientMain.showAlert("Error", "Datos Incorrectos");
                    }

                }catch(Exception e){
                    ClientMain.showAlert("Error", "Restaurant No Registrado");
                    e.printStackTrace();
                }

            }
        }catch(NumberFormatException nfe){
            ClientMain.showAlert("Error","El rut solo debe contener numeros");
        }
    }
    void goToProfile(Restaurant restaurant, Event event){
       try {
           FXMLLoader loader = new FXMLLoader();
           loader.setControllerFactory((RestaurantMain.getContext()::getBean));

           Parent root = loader.load(SignUpController.class.getResourceAsStream("Restaurant/Perfil.fxml"));
           PerfilController controller = loader.getController();
           controller.setRestaurant(restaurant);

           Node node = (Node) event.getSource();
           Stage stage = (Stage) node.getScene().getWindow();

           stage.setScene(new Scene(root));
           stage.getScene().getStylesheets().add(SignUpController.class.getResource("Client/LogInSignUp.css").toExternalForm());
       }catch(Exception e){
           e.printStackTrace();

       }
    }

    void clean(){
        rut.setText(null);
        pass.setText(null);
    }

}


