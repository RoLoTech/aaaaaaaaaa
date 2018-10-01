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
public class SignUpController implements Initializable {

    @Autowired
    private MainApp main;

    @Autowired
    private ClientService cs;

    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField email;

    @FXML
    void loginTab(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(LogInController.class.getResource("LogIn.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }

    @FXML
    void signupTab(MouseEvent event) {

    }

    @FXML
    void signup(MouseEvent event) throws ClientAlreadyExists, InvalidClientInformation {

        String sUser        = user.getText();
        String sPassword    = pass.getText();
        String sEmail       = email.getText();

        cs.addClient(sUser, sPassword, sEmail);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}