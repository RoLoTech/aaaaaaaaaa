package labTic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import labTic.ui.LogInRestaurantController;
import labTic.ui.PerfilController;
import labTic.ui.SignUpController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestaurantMain extends Application {

    private static ConfigurableApplicationContext context;

    private Parent root;


    @Override
    public void init() {
        RestaurantMain.context = SpringApplication.run(RestaurantMain.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory((RestaurantMain.getContext()::getBean));
        root = fxmlLoader.load(SignUpController.class.getResourceAsStream("Restaurant/LogInRestaurant.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add(SignUpController.class.getResource("Client/LogInSignUp.css").toExternalForm());
        primaryStage.show();
    }
     public static void main(String[] args){ launch(args); }

     public static ConfigurableApplicationContext getContext() { return context; }

     @Override
     public void stop() {
            labTic.RestaurantMain.getContext().close();
        }


}
