package labTic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        fxmlLoader.setControllerFactory(MainApp.getContext()::getBean);

        root = fxmlLoader.load(LogInController.class.getResource("LogIn.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args){ launch(args); }

    public static ConfigurableApplicationContext getContext() { return context; }

    @Override
    public void stop() {
        MainApp.getContext().close();
    }
}
