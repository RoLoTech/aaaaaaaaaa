package labTic.ui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import labTic.ClientMain;
import labTic.services.ClientService;
import labTic.services.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            ClientMain.showAlert("Error","No Deje Campos Vacios");
        }else {
            try{
                Client cliente = (Client)clientService.findOneByUser(sUser);
                if(cliente.getPassword().equals(sPassword)) {
                    ClientMain.showAlert("Exito", "Usuario Logueado Correctamente");
                    goToRestaurant(cliente,event);
                }
                else {
                    ClientMain.showAlert("Error", "Datos Incorrectos");
                    clean();
                    }

            }catch(Exception e){
                ClientMain.showAlert("Error", "Usuario No Registrado");
                e.printStackTrace();
            }

        }

    }

    @FXML
    void loginTab(MouseEvent event) {

    }

    @FXML
    void signupTab(MouseEvent event) throws Exception {
        ClientMain.changeScene("Client/SignUp.fxml", event);}


    private void clean() {
        user.setText(null);
        pass.setText(null);
    }

    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    private void goToRestaurant(Client client, Event event) throws  Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory((ClientMain.getContext()::getBean));

        Parent root = loader.load(SignUpController.class.getResourceAsStream("Client/Restaurantes.fxml"));
        RestaurantController controller = loader.getController();
        controller.setClient(client);

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.getScene().getStylesheets().add(SignUpController.class.getResource("Client/LogInSignUp.css").toExternalForm());
    }

}
