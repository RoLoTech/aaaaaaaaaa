package labTic;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import labTic.services.ClientService;
import labTic.ui.LogInController;
import labTic.ui.SignUpController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApp extends Application {

    private static ConfigurableApplicationContext context;

    private Parent root;


    @Override
    public void init() {
        MainApp.context = SpringApplication.run(MainApp.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory((MainApp.getContext()::getBean));
        root = fxmlLoader.load(LogInController.class.getResourceAsStream("LogIn.sfxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add(SignUpController.class.getResource("LogInSignUp.css").toExternalForm());
        primaryStage.show();

    }

    public static void main(String[] args){ launch(args); }

    public static ConfigurableApplicationContext getContext() { return context; }

    @Override
    public void stop() {
        MainApp.getContext().close();
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
        fxmlLoader.setControllerFactory((MainApp.getContext()::getBean));

        Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream(newFXML));

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.getScene().getStylesheets().add(SignUpController.class.getResource("LogInSignUp.css").toExternalForm());

    }
}
