package labTic.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import labTic.services.RestaurantService;
import labTic.services.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class RestaurantController implements Initializable {


    @Autowired
    private RestaurantService restaurantService;

    private int x=0;

    private List<Restaurant> restaurants;

    @FXML
    private Text text3;

    @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    private Text text4;

    @FXML
    void btnNext(MouseEvent event) {

    }

    @FXML
    void btnPrev(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restaurants = restaurantService.findAll();
        showRestaurants();
    }

    void showRestaurants(){
        if(x<restaurants.size())
            text1.setText(restaurants.get(x).getName());
        else
            text1.setText(null);

        if(x<restaurants.size()-1)
            text2.setText(restaurants.get(x + 1).getName());
        else
            text2.setText(null);

        if(x<restaurants.size()-2)
            text3.setText(restaurants.get(x + 2).getName());
        else
            text3.setText(null);

        if(x<restaurants.size()-3)
            text4.setText(restaurants.get(x + 3).getName());
        else
            text4.setText(null);


    }
}
