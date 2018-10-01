package labTic.ui;

import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AppController implements Initializable {

    @Autowired
    private Main main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
