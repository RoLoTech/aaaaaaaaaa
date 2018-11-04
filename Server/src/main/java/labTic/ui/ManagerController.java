package labTic.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import labTic.ClientMain;
import labTic.services.RestaurantService;
import labTic.services.exceptions.InvalidRestaurantInformation;
import labTic.services.exceptions.RestaurantAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagerController {

    @Autowired
    RestaurantService restaurantService;

    @FXML
    private TextField rut;

    @FXML
    private TextField address; //address

    @FXML
    private TextField area; //area

    @FXML
    private TextField name; //name

    @FXML
    private TextField style; //style

    @FXML
    private TextField phoneNumber;

    @FXML
    void createRestaurantTab(MouseEvent event) {

    }

    @FXML
    void searchRestaurantTab(MouseEvent event) {

    }

    @FXML
    void createRestaurant(MouseEvent event) {
        String name = this.name.getText();
        String style = this.style.getText();
        String sPhoneNumber = phoneNumber.getText();
        String address = this.address.getText();
        String area = this.area.getText();

        //addClient(String firstName, String lastName, String email, String user, String password, String phoneNumber)
        try{
            long rut = Long.parseLong(this.rut.getText());
            restaurantService.addRestaurant(rut, name, address, style, sPhoneNumber, area);
            ClientMain.showAlert("Registro Exitoso","Restaurante Registrado Correctamente");
            clean();
        }catch(RestaurantAlreadyExists ras){
            ClientMain.showAlert("Error", "El Restaurante ya fue registrado");
        }catch(InvalidRestaurantInformation iri){
            ClientMain.showAlert("Error", "No deje campos vacios");
        }catch(NumberFormatException nfe){
            ClientMain.showAlert("Error","El rut solo debe contener numeros");
        }
    }
    private void clean(){
        name.setText(null);
        address.setText(null);
        area.setText(null);
        style.setText(null);
        phoneNumber.setText(null);
        rut.setText(null);
    }
}
