package labTic;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import labTic.ui.LogInController;
import labTic.ui.SignUpController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClientMain extends Application {

    private static ConfigurableApplicationContext context;

    private Parent root;


    @Override
    public void init() {
        ClientMain.context = SpringApplication.run(ClientMain.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory((ClientMain.getContext()::getBean));
        root = fxmlLoader.load(LogInController.class.getResourceAsStream("Client/LogIn.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add(SignUpController.class.getResource("Client/LogInSignUp.css").toExternalForm());
        primaryStage.show();

    }

    public static void main(String[] args){ launch(args); }

    public static ConfigurableApplicationContext getContext() { return context; }

    @Override
    public void stop() {
        ClientMain.getContext().close();
    }

    public static void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public static void changeScene(String newFXML, Event event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory((ClientMain.getContext()::getBean));

        Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream(newFXML));

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.getScene().getStylesheets().add(SignUpController.class.getResource("Client/LogInSignUp.css").toExternalForm());

    }
}
