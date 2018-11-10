package labTic.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import labTic.persistence.RestaurantRepository;
import labTic.services.RestaurantService;
import labTic.services.entities.Restaurant;
import labTic.services.exceptions.RestaurantNoExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

@Component
public class PerfilController implements Initializable {

    private Restaurant restaurant;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @FXML
    private TextArea descripcion;


//    @FXML
//    private TextField hourMonday;
//
//    @FXML
//    private TextField hourTuesday;
//
//    @FXML
//    private TextField hourWednesday;
//
//    @FXML
//    private TextField hourThursday;
//
//    @FXML
//    private TextField hourFriday;
//
//    @FXML
//    private TextField hourSaturday;
//
//    @FXML
//    private TextField hourSunday;

    @FXML
    private TextField houropen;

    @FXML
    private TextField hourclose;

    @FXML
    private TextField name;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField address;

    @FXML
    private TextField image;

    void setRestaurant(Restaurant restaurant){

        this.restaurant = restaurant;
        name.setText(restaurant.getName());
        phoneNumber.setText(restaurant.getPhone());
        address.setText(restaurant.getAddress());
    }

    @FXML
    void perfilTab(MouseEvent event) {

    }

    @FXML
    void reservasTab(MouseEvent event) {

    }

    @FXML
    void btnGuardar(MouseEvent event) {

        restaurant.setName(name.getText());
        restaurant.setPhone(phoneNumber.getText());
        restaurant.setAddress(address.getText());

        restaurantRepository.save(restaurant);


        if(!"".equals(image.getText())){
            String imageSource = image.getText();
            Resource backImgFile = new FileSystemResource(image.getText());
            byte[] arrayPic = new byte[0];
            try {
                arrayPic = new byte[(int) backImgFile.contentLength()];
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                backImgFile.getInputStream().read(arrayPic);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                restaurantService.addPic(restaurant.getRut(), arrayPic);
            } catch (RestaurantNoExists restaurantNoExists) {
                restaurantNoExists.printStackTrace();
            }
        }
//        restaurant.setOpeningTime(LocalTime.parse(houropen.getText()));
//        restaurant.setClosingTime(LocalTime.parse(hourclose.getText()));



    }

    @FXML
    void btnBuscarImagen(MouseEvent event) {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            String fileAsString = file.toString();
            image.setText(fileAsString);
        } else {
            image.setText(null);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        houropen.setText(restaurant.getOpeningTime().toString());
//        hourclose.setText(restaurant.getClosingTime().toString());
       /* name.setText(restaurant.getName());
        phoneNumber.setText(restaurant.getPhone());
        address.setText(restaurant.getAddress());*/

    }
}
