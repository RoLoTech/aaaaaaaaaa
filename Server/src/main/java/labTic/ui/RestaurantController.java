package labTic.ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import labTic.ClientMain;
import labTic.services.RestaurantService;
import labTic.services.entities.Client;
import labTic.services.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class RestaurantController implements Initializable {


    @Autowired
    private RestaurantService restaurantService;

    private Client client;


    public int x=0;


    public List<Restaurant> restaurants;

    @FXML
    private MenuButton filtroMultiple1;

    @FXML
    private MenuButton filtroMultiple2;

    @FXML
    private MenuButton filtroMultiple3;

    @FXML
    private MenuButton filtroMultiple4;

    @FXML
    void btnFiltroMultiple1 (ActionEvent event) {

    }

    @FXML
    void btnFiltroMultiple2 (ActionEvent event) {
    }

    @FXML
    void btnFiltroMultiple3 (ActionEvent event) {
    }

    @FXML
    void btnFiltroMultiple4 (ActionEvent event) {
    }

    @FXML
    private Text textRestaurant4;

    @FXML
    private Text textRating4;

    @FXML
    private Text textLocation4;

    @FXML
    private Text textComida4;

    @FXML
    private Text textEstilo4;

    @FXML
    private Text textPrecio4;

    @FXML
    private Text textRestaurant3;

    @FXML
    private Text textRating3;

    @FXML
    private Text textLocation3;

    @FXML
    private Text textComida3;

    @FXML
    private Text textEstilo3;

    @FXML
    private Text textPrecio3;

    @FXML
    private Text textRestaurant2;

    @FXML
    private Text textRating2;

    @FXML
    private Text textLocation2;

    @FXML
    private Text textComida2;

    @FXML
    private Text textEstilo2;

    @FXML
    private Text textPrecio2;

    @FXML
    private Text textRestaurant1;

    @FXML
    private Text textRating1;

    @FXML
    private Text textLocation1;

    @FXML
    private Text textComida1;

    @FXML
    private Text textEstilo1;

    @FXML
    private Text textPrecio1;
    
    @FXML
    private Text text3;

    @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    private Text text4;

    void setClient(Client client){
        this.client = client;
    }

    @FXML
    void cardRestaurant1(MouseEvent event) throws Exception {
        selectRestaurant(x,event);
    }

    @FXML
    void cardRestaurant2(MouseEvent event) throws Exception{
        selectRestaurant(x+1,event);
    }

    @FXML
    void cardRestaurant3(MouseEvent event) throws Exception {
        selectRestaurant(x+2,event);
    }

    @FXML
    void cardRestaurant4(MouseEvent event) throws  Exception{
        selectRestaurant(x+3,event);
    }

    @FXML
    void btnNext(MouseEvent event) {
        if(x+4<restaurants.size()) {
            x=x+4;
            showRestaurants();
        }

    }

    @FXML
    void btnPrev(MouseEvent event) {
        if(x-4>=0){
            x=x-4;
            showRestaurants();
        }
    }
    @FXML
    void btnFiltrar(MouseEvent event) {
        x=0;

        List<String> locations = filtroMultiple1.getItems().stream()
                    .filter(item -> CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                    .map(MenuItem::getText)
                    .collect(Collectors.toList());
        List<String> foodTypes = filtroMultiple2.getItems().stream()
                .filter(item -> CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                .map(MenuItem::getText)
                .collect(Collectors.toList());
        List<String> prices = filtroMultiple4.getItems().stream()
                .filter(item -> CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                .map(MenuItem::getText)
                .collect(Collectors.toList());
        List<String> styles = filtroMultiple3.getItems().stream()
                .filter(item -> CheckMenuItem.class.isInstance(item) && CheckMenuItem.class.cast(item).isSelected())
                .map(MenuItem::getText)
                .collect(Collectors.toList());
        restaurants = restaurantService.filter(locations,foodTypes,null,prices,null,styles,"");
        showRestaurants();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //LocalTime horaActual = LocalTime.now();
        textRating1.setText(null);
        textRating2.setText(null);
        textRating3.setText(null);
        textRating4.setText(null);

        restaurants = restaurantService.findAll();

        CheckMenuItem thisFoodType;
        CheckMenuItem thisLocation;
        CheckMenuItem thisPrice;
        CheckMenuItem thisStyle;

        for(int i = 0;i<restaurants.size();i++){

            thisLocation = new CheckMenuItem(restaurants.get(i).getArea());
            thisFoodType = new CheckMenuItem(restaurants.get(i).getFoodtype());
            thisPrice = new CheckMenuItem(restaurants.get(i).getPriceRange());
            thisStyle = new CheckMenuItem(restaurants.get(i).getStyle());

            if(!filtroMultiple1.getItems().contains(thisLocation)) {
                filtroMultiple1.getItems().add(thisLocation);
                //locations.add(sLocation);
            }

            if(!filtroMultiple2.getItems().contains(thisFoodType)) {
                filtroMultiple2.getItems().add(thisFoodType);
               // foodTypes.add(sFoodType);
            }

            if(!filtroMultiple3.getItems().contains(thisStyle)) {
                filtroMultiple3.getItems().add(thisStyle);
                //styles.add(sStyle);
            }
            if(!filtroMultiple4.getItems().contains(thisPrice)) {
                filtroMultiple4.getItems().add(thisPrice);
                //prices.add(sPrice);
            }

        }
//        cbLocation.setItems(locations);
//        cbFoodType.setItems(foodTypes);
        showRestaurants();
    }


    private void showRestaurants(){

        if(x<restaurants.size()){
            textRestaurant1.setText(restaurants.get(x).getName());
            textLocation1.setText(restaurants.get(x).getArea());
            textPrecio1.setText(restaurants.get(x).getPriceRange());
            textComida1.setText(restaurants.get(x).getFoodtype());
            textEstilo1.setText(restaurants.get(x).getStyle());
        }
        else {
            textRestaurant1.setText(null);
            textLocation1.setText(null);
            textPrecio1.setText(null);
            textComida1.setText(null);
            textEstilo1.setText(null);
        }

        if(x<restaurants.size()-1) {
            textRestaurant2.setText(restaurants.get(x+1).getName());
            textLocation2.setText(restaurants.get(x+1).getArea());
            textPrecio2.setText(restaurants.get(x+1).getPriceRange());
            textComida2.setText(restaurants.get(x+1).getFoodtype());
            textEstilo2.setText(restaurants.get(x+1).getStyle());
        }else {
            textRestaurant2.setText(null);
            textLocation2.setText(null);
            textPrecio2.setText(null);
            textComida2.setText(null);
            textEstilo2.setText(null);
        }

        if(x<restaurants.size()-2) {
            textRestaurant3.setText(restaurants.get(x+2).getName());
            textLocation3.setText(restaurants.get(x+2).getArea());
            textPrecio3.setText(restaurants.get(x+2).getPriceRange());
            textComida3.setText(restaurants.get(x+2).getFoodtype());
            textEstilo3.setText(restaurants.get(x+2).getStyle());
        }else {
            textRestaurant3.setText(null);
            textLocation3.setText(null);
            textPrecio3.setText(null);
            textComida3.setText(null);
            textEstilo3.setText(null);
        }

        if(x<restaurants.size()-3) {
            textRestaurant4.setText(restaurants.get(x+3).getName());
            textLocation4.setText(restaurants.get(x+3).getArea());
            textPrecio4.setText(restaurants.get(x+3).getPriceRange());
            textComida4.setText(restaurants.get(x+3).getFoodtype());
            textEstilo4.setText(restaurants.get(x+3).getStyle());
        }else {
            textRestaurant4.setText(null);
            textLocation4.setText(null);
            textPrecio4.setText(null);
            textComida4.setText(null);
            textEstilo4.setText(null);
        }


    }

    private void selectRestaurant(int listPosition, Event event) throws Exception{

        if(listPosition < restaurants.size()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory((ClientMain.getContext()::getBean));

            Parent root = loader.load(SignUpController.class.getResourceAsStream("Client/RestaurantView.fxml"));
            RestaurantViewController controller = loader.getController();
            controller.setRestaurant(restaurants.get(listPosition));
            controller.setClient(client);

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(SignUpController.class.getResource("Client/Restaurant.css").toExternalForm());

            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantView.fxml"));
            Parent root = loader.load();

            RestaurantViewController controller = loader.getController();
            controller.setRestaurant(restaurants.get(listPosition));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(SignUpController.class.getResource("LogInSignUp.css").toExternalForm());*/
        }
    }
}
