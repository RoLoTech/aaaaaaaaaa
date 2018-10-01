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
import labTic.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LogInController implements Initializable {

    @Autowired
    private MainApp main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void login(MouseEvent event) {

        String sUser        = user.getText();
        String sPassword    = pass.getText();



    }

    @FXML
    void loginTab(MouseEvent event) {

    }

    @FXML
    void signupTab(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(main.getContext()::getBean);
        Parent root = fxmlLoader.load(SignUpController.class.getResource("SignUp.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }

    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

}
