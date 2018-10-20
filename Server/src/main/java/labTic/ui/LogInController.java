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
    private ClientService clientService;


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
            MainApp.showAlert("Error","No Deje Campos Vacios");
        }else {
            try{
                Client cliente = (Client)clientService.findOneByUser(sUser);
                if(cliente.getPassword().equals(sPassword)) {
                    MainApp.showAlert("Exito", "Usuario Logueado Correctamente");
                    MainApp.changeScene("Restaurantes.fxml", event);
                }
                else {
                    MainApp.showAlert("Error", "Datos Incorrectos");
                    clean();
                    }

            }catch(Exception e){
                MainApp.showAlert("Error", "Usuario No Registrado");
                e.printStackTrace();
            }

        }

    }

    @FXML
    void loginTab(MouseEvent event) {

    }

    @FXML
    void signupTab(MouseEvent event) throws Exception {MainApp.changeScene("SignUp.fxml", event);}


    private void clean() {
        user.setText(null);
        pass.setText(null);
    }

    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

}
