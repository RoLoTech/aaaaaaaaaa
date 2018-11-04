package labTic.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.ResourceBundle;

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
//        x=0;
//        restaurants = restaurantService.filter(cbLocation.getValue(),cbFoodType.getValue(),"","",null,"","");
//        showRestaurants();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalTime horaActual = LocalTime.now();

        restaurants = restaurantService.findAll();
        CheckMenuItem thisFoodType;
        CheckMenuItem thisLocation;
        CheckMenuItem thisPrice;
        CheckMenuItem thisStyle;
        /*ObservableList<String> prices = FXCollections.observableArrayList("");
        ObservableList<String> foodTypes = FXCollections.observableArrayList("");
        ObservableList<String> locations = FXCollections.observableArrayList("");*/

        for(int i = 0;i<restaurants.size();i++){
            thisLocation = new CheckMenuItem(restaurants.get(i).getArea());
            thisFoodType = new CheckMenuItem(restaurants.get(i).getFoodtype());
            thisPrice = new CheckMenuItem(restaurants.get(i).getPriceRange());
            thisStyle = new CheckMenuItem(restaurants.get(i).getStyle());

            if(!filtroMultiple1.getItems().contains(thisLocation))
                filtroMultiple1.getItems().add(thisLocation);

            if(!filtroMultiple2.getItems().contains(thisFoodType))
                filtroMultiple2.getItems().add(thisFoodType);

            if(!filtroMultiple3.getItems().contains(thisStyle))
                filtroMultiple3.getItems().add(thisStyle);

            if(!filtroMultiple4.getItems().contains(thisPrice))
                filtroMultiple4.getItems().add(thisPrice);

        }
//        cbLocation.setItems(locations);
//        cbFoodType.setItems(foodTypes);
        showRestaurants();
    }


    private void showRestaurants(){
        if(x<restaurants.size())
            text1.setText(restaurants.get(x).getName()+"\n"+ restaurants.get(x).getArea()+"\n"+restaurants.get(x).getPriceRange()+
                            "\n"+restaurants.get(x).getFoodtype());
        else
            text1.setText(null);

        if(x<restaurants.size()-1)
            text2.setText(restaurants.get(x + 1).getName()+"\n"+restaurants.get(x + 1).getArea()+"\n"+restaurants.get(x + 1).getPriceRange()+
                            "\n"+restaurants.get(x + 1).getFoodtype());
        else
            text2.setText(null);

        if(x<restaurants.size()-2)
            text3.setText(restaurants.get(x + 2).getName()+"\n"+restaurants.get(x + 2).getArea()+"\n"+restaurants.get(x + 2).getPriceRange()+
                            "\n"+restaurants.get(x + 2).getFoodtype());
        else
            text3.setText(null);

        if(x<restaurants.size()-3)
            text4.setText(restaurants.get(x + 3).getName()+"\n"+restaurants.get(x + 3).getArea()+"\n"+restaurants.get(x + 3).getPriceRange()+
                            "\n"+restaurants.get(x + 3).getFoodtype());
        else
            text4.setText(null);


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
