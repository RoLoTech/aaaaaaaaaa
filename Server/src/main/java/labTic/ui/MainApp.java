package labTic.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent fxml = FXMLLoader.load(getClass().getResource("app.fmxl"));

        Scene scene = new Scene(fxml);

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String... args){

    }

}
