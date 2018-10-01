package labTic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import labTic.ui.LogInController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApp extends Application {

    private ConfigurableApplicationContext context;

    private FXMLLoader fxmlLoader;

    private Parent root;


    @Override
    public void init() throws Exception {
        context = SpringApplication.run(MainApp.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        fxmlLoader.setLocation(LogInController.class.getResource("LogIn.fxml"));
        root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

/*        Parent fxml = FXMLLoader.load(getClass().getResource("app.fmxl"));

        Scene scene = new Scene(fxml);

        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.show();*/

//        context = new ConfigurableApplicationContext(MainApp.class);
//
//        FXMLLoader loader = new FXMLLoader(AppController.class.getResource("LogIn.fxml"));
//        loader.setControllerFactory(context::getBean);
//
//        root = loader.load();
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();

    }

          public static void main(String... args){

           launch(args);

    }

}
