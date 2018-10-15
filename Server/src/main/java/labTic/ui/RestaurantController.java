package labTic.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import labTic.services.RestaurantService;
import labTic.services.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
        if(x+4<restaurants.size()) {
            x=x+4;
            showRestaurants();
            System.out.println(x);
        }

    }

    @FXML
    void btnPrev(MouseEvent event) {
        if(x-4>=0){
            x=x-4;
            showRestaurants();
            System.out.println(x);
        }

    }

    @FXML
    private ChoiceBox<String> cbLocation;

    @FXML
    void cbLocationClick(MouseEvent event) {
        x=0;
        if(cbLocation.getValue().equals("<Todas las zonas>")){
            restaurants = restaurantService.findAll();
            showRestaurants();
        }else{
            restaurants = restaurantService.findByArea(cbLocation.getValue());
            showRestaurants();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        restaurants = restaurantService.findAll();
        ObservableList<String> arr1 = FXCollections.observableArrayList("<Todas las zonas>","Pocitos","Brazo Oriental","Punta Carretas","Malvin","Malvin Norte");
        cbLocation.setItems(arr1);
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
