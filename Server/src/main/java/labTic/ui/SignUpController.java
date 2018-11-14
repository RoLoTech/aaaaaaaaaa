package labTic.ui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import labTic.ClientMain;
import labTic.services.ClientService;
import labTic.services.exceptions.ClientAlreadyExistsException;
import labTic.services.exceptions.InvalidClientInformationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpController {


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
    private TextField lastName;

    @FXML
    private TextField phoneNumber;


    @FXML
    void btnVolver(MouseEvent event) {

    }

    @FXML
    void loginTab(MouseEvent event) throws Exception {
        ClientMain.changeScene("Client/Login.fxml",event);}

    @FXML
    void signupTab(MouseEvent event) {

    }

    @FXML
    void signup(MouseEvent event) throws ClientAlreadyExistsException, InvalidClientInformationException {

        String sFirstName = firstName.getText();
        String sLastName = lastName.getText();
        String sPhoneNumber = phoneNumber.getText();
        String sUser = user.getText();
        String sPassword = pass.getText();
        String sEmail = email.getText();

        //addClient(String firstName, String lastName, String email, String user, String password, String phoneNumber)
        try{
            clientService.addClient(sFirstName,sLastName,sPhoneNumber,sUser,sPassword,sEmail);
            ClientMain.showAlert("Registro Exitoso","Usuario Registrado Correctamente");
            clean();
        }catch(ClientAlreadyExistsException cas){
            ClientMain.showAlert("Error", "El Usuario ya fue registrado");
            clean();
        }catch(InvalidClientInformationException ici){
            ClientMain.showAlert("Error", "No deje campos vacios");
        }
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