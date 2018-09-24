package labTic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import labTic.services.ClientService;

public class Main extends Application {

    private ClientService cs;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Formulario");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Formulario");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label firstName = new Label("First Name:");
        grid.add(firstName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label lastName = new Label("Last Name:");
        grid.add(lastName, 0, 2);

        TextField userTextField2 = new TextField();
        grid.add(userTextField2, 1, 2);

        Label CI = new Label("CI:");
        grid.add(CI, 0, 3);

        TextField userTextField3 = new TextField();
        grid.add(userTextField3, 1, 3);


        //grid.setGridLinesVisible(true);

        //BOTON
        Button btn = new Button("FormSubmit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //actiontarget.setFill(Color.FIREBRICK);
                //actiontarget.setText(userTextField.getText());
                //ClientService x = new ClientService();
                cs.addNewClient(userTextField.getText(),userTextField2.getText(),userTextField3.getText());


            }
        });

        Scene scene = new Scene(grid, 400, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
