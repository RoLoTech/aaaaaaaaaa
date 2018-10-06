package labTic.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public SignUpController() {
        System.out.println("TESTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTt");
    }
    @FXML
    void loginTab(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MainApp.getContext()::getBean);

        Parent root = fxmlLoader.load((LogInController.class.getResourceAsStream("LogIn.fxml")));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void signupTab(MouseEvent event) {

    }

    @FXML
    void signup(MouseEvent event) throws ClientAlreadyExists, InvalidClientInformation {

        String sUser        = user.getText();
        String sPassword    = pass.getText();
        String sEmail       = email.getText();

        clientService.addClient(sUser, sPassword, sEmail);

    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }
}