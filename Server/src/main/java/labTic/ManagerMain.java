package labTic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import labTic.ui.ManagerController;
import labTic.ui.SignUpController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ManagerMain extends Application {

    private static ConfigurableApplicationContext context;

    private Parent root;


    @Override
    public void init() { ManagerMain.context = SpringApplication.run(ManagerMain.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory((ManagerMain.getContext()::getBean));
        root = fxmlLoader.load(ManagerController.class.getResourceAsStream("Manager.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add(SignUpController.class.getResource("LogInSignUp.css").toExternalForm());
        primaryStage.show();

    }

    public static void main(String[] args){ launch(args); }

    public static ConfigurableApplicationContext getContext() { return context; }

    @Override
    public void stop() {
        labTic.ManagerMain.getContext().close();
    }

}
