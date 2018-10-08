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
import labTic.services.ClientService;
import labTic.services.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LogInController  {

    @Autowired
    ClientService clientService;

//    @Autowired
//    private MainApp mainApp;

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//    }

    @FXML
    void login(MouseEvent event) {

        String sUser = user.getText();
        String sPassword = pass.getText();

        if(sUser==null || "".equals(sUser) || sPassword==null || "".equals(sPassword)) {
            showAlert("Error","No Deje Campos Vacios");
        }else {
            try{
                Client cliente = (Client)clientService.findOneByUser(sUser);
                if(cliente.getPassword().equals(sPassword))
                    showAlert("Exito", "Usuario Logueado Correctamente");
                else {
                    showAlert("Error", "Datos Incorrectos");
                    clean();
                    }
                System.out.println(clientService.findOneByUser(sUser).getPassword());

            }catch(Exception e){
                showAlert("Error", "Usuario No Registrado");
            }

        }

    }

    @FXML
    void loginTab(MouseEvent event) {

    }

    @FXML
    void signupTab(MouseEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setControllerFactory((MainApp.getContext()::getBean));

        Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("SignUp.fxml"));

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();


        stage.setScene(new Scene(root));

        stage.getScene().getStylesheets().add(SignUpController.class.getResource("LogInSignUp.css").toExternalForm());

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
    }

    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

}
