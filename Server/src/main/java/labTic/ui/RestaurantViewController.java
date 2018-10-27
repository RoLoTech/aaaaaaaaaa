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

    @FXML
    private Text desc1;

    @FXML
    private Text desc2;

    @FXML
    private Text desc3;

    @FXML
    void btnVolver(MouseEvent event) throws Exception {
        MainApp.changeScene("Restaurantes.fxml", event);
    }

    @FXML
    void loginTab(MouseEvent event) {

    }

    @FXML
    void signupTab(MouseEvent event) {

    }

    void setRestaurant(Restaurant restaurant){
        desc1.setText(restaurant.getName());
        desc2.setText(restaurant.getArea());
        desc3.setText(restaurant.getFoodtype());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
