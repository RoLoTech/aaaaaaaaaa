package labTic.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class ReservasController {


    @FXML
    private TableView<?> table1;

    @FXML
    private TableView<?> table2;

    @FXML
    void clickTable1(MouseEvent event) {

    }

    @FXML
    void clickTable2(MouseEvent event) {

    }

    @FXML
    void perfilTab(MouseEvent event) {

    }

    @FXML
    void reservasTab(MouseEvent event) {

    }

    @FXML
    private ToggleButton btnDisponible;

    @FXML
    void btnDisponibleClick(MouseEvent event) {

        if (btnDisponible.isSelected())
            btnDisponible.setText("No Disponible");
        else
            btnDisponible.setText("Disponible");

    }
}
