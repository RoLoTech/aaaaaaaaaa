package labTic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import labTic.services.entities.Client;
import labTic.ui.AppController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MainApp extends Application {

    private AnnotationConfigApplicationContext context;
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception {

/*        Parent fxml = FXMLLoader.load(getClass().getResource("app.fmxl"));

        Scene scene = new Scene(fxml);

        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.show();*/

        context = new AnnotationConfigApplicationContext(MainApp.class);

        FXMLLoader loader = new FXMLLoader(AppController.class.getResource("App.fxml"));
        loader.setControllerFactory(context::getBean);

        root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

/*    public static void main(String... args){

        launch(args);

    }*/

}
