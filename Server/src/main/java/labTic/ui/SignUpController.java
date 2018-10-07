package labTic.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import labTic.MainApp;
import labTic.persistence.ClientRepository;
import labTic.services.ClientService;
import labTic.services.exceptions.ClientAlreadyExists;
import labTic.services.exceptions.InvalidClientInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SignUpController {

//    @Autowired
//    private MainApp mainApp;

    @Autowired
    private ClientService clientService;

    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private PasswordField lastName;

    @FXML
    private TextField phoneNumber;

    @FXML
    void loginTab(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setControllerFactory((MainApp.getContext()::getBean));

        Parent root = fxmlLoader.load(LogInController.class.getResourceAsStream("LogIn.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }

    @FXML
    void signupTab(MouseEvent event) {

    }

    @FXML
    void signup(MouseEvent event) throws ClientAlreadyExists, InvalidClientInformation {

        String sFirstName = firstName.getText();
        String sLastName = lastName.getText();
        String sPhoneNumber = phoneNumber.getText();
        String sUser = user.getText();
        String sPassword = pass.getText();
        String sEmail = email.getText();

        //addClient(String firstName, String lastName, String email, String user, String password, String phoneNumber)
        try{
            clientService.addClient(sFirstName,sLastName,sPhoneNumber,sUser,sPassword,sEmail);
            showAlert("Registro Exitoso","Usuario Registrado Correctamente");
            clean();
        }catch(ClientAlreadyExists cas){
            showAlert("Error", "El Usuario ya fue registrado");
        }catch(InvalidClientInformation ici){
            showAlert("Error", "No deje campos vacios");
        }
    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
    private void clean() {
        user.setText(null);
        pass.setText(null);
        firstName.setText(null);
        lastName.setText(null);
        phoneNumber.setText(null);
        email.setText(null);
    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }
}