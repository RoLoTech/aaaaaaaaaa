package labTic.ui;

import commons.exceptions.RestaurantDoesNotExistException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import labTic.ClientMain;
import labTic.RestaurantMain;
import labTic.persistence.RestaurantRepository;
import labTic.business.RestaurantService;
import labTic.business.RestaurantService;
import labTic.business.TableService;
import labTic.business.entities.Restaurant;
import labTic.business.entities.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PerfilController implements Initializable {

    private Restaurant restaurant;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TableService tableService;

    @FXML
    private TextArea descripcion;


//    @FXML
//    private TextField hourMonday;
//
//    @FXML
//    private TextField hourTuesday;
//
//    @FXML
//    private TextField hourWednesday;
//
//    @FXML
//    private TextField hourThursday;
//
//    @FXML
//    private TextField hourFriday;
//
//    @FXML
//    private TextField hourSaturday;
//
//    @FXML
//    private TextField hourSunday;

    @FXML
    private TextField horarioLunes;

    @FXML
    private TextField horarioMiercoles;

    @FXML
    private TextField horarioViernes;

    @FXML
    private TextField horarioDomingo;

    @FXML
    private TextField horarioMartes;

    @FXML
    private TextField horarioJueves;

    @FXML
    private TextField horarioSabado;

    @FXML
    private TextField houropen;

    @FXML
    private TextField hourclose;

    @FXML
    private TextField name;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField address;

    @FXML
    private TextField image;

    @FXML
    private TableView<Tables> tblMesas;

    @FXML
    private TableColumn<Tables, Long> tblMesasc1;

    @FXML
    private TableColumn<Tables, Integer> tblMesasc2;

    @FXML
    private TextField txtAgregarMesaPersonas;

    @FXML
    void btnAgregarMesa(MouseEvent event) {
        try {
            int cantPersonasMesa = Integer.valueOf(txtAgregarMesaPersonas.getText());
            tableService.addTable(restaurant.getRut(),cantPersonasMesa);
            updateTables();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    void setRestaurant(Restaurant restaurant){

        this.restaurant = restaurant;
        name.setText(restaurant.getName());
        phoneNumber.setText(restaurant.getPhone());
        address.setText(restaurant.getAddress());
        descripcion.setText(restaurant.getDescription());
        horarioDomingo.setText(restaurant.getHoursSunday());
        horarioLunes.setText(restaurant.getHoursMonday());
        horarioMartes.setText(restaurant.getHoursTuesday());
        horarioMiercoles.setText(restaurant.getHoursWednesday());
        horarioJueves.setText(restaurant.getHoursThursday());
        horarioViernes.setText(restaurant.getHoursFriday());
        horarioSabado.setText(restaurant.getHoursSaturday());

        updateTables();

    }

    private void updateTables(){
        ObservableList<Tables> tablesObservableList = FXCollections.observableArrayList();
        List<Tables> tablesList = restaurantService.getAllTables(restaurant);
        for(int i =0; i<tablesList.size();i++)
            tablesObservableList.add(tablesList.get(i));
        tblMesas.setItems(tablesObservableList);
        tblMesasc1.setCellValueFactory(new PropertyValueFactory<Tables,Long>("id"));
        tblMesasc2.setCellValueFactory(new PropertyValueFactory<Tables,Integer>("capacity"));
    }

    @FXML
    void perfilTab(MouseEvent event) {

    }

    @FXML
    void reservasTab(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory((RestaurantMain.getContext()::getBean));

            Parent root = loader.load(ReservasController.class.getResourceAsStream("Restaurant/Reservas.fxml"));
            ReservasController controller = loader.getController();
            controller.setRestaurant(restaurant);

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(ReservasController.class.getResource("Restaurant/Reservas.css").toExternalForm());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void btnGuardar(MouseEvent event) {

        restaurant.setName(name.getText());
        restaurant.setPhone(phoneNumber.getText());
        restaurant.setAddress(address.getText());
        restaurant.setDescription(descripcion.getText());

        restaurant.setHoursMonday(horarioLunes.getText());
        restaurant.setHoursTuesday(horarioMartes.getText());
        restaurant.setHoursWednesday(horarioMiercoles.getText());
        restaurant.setHoursThursday(horarioJueves.getText());
        restaurant.setHoursFriday(horarioViernes.getText());
        restaurant.setHoursSaturday(horarioSabado.getText());
        restaurant.setHoursSunday(horarioDomingo.getText());

        restaurantRepository.save(restaurant);

        if(!"".equals(image.getText())){
            Resource backImgFile = new FileSystemResource(image.getText());
            byte[] arrayPic = new byte[0];
            try {
                arrayPic = new byte[(int) backImgFile.contentLength()];
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                backImgFile.getInputStream().read(arrayPic);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                restaurantService.addPic(restaurant.getRut(), arrayPic);
            } catch (RestaurantDoesNotExistException restaurantNoExists) {
                restaurantNoExists.printStackTrace();
            }
        }
//        restaurant.setOpeningTime(LocalTime.parse(houropen.getText()));
//        restaurant.setClosingTime(LocalTime.parse(hourclose.getText()));



    }

    @FXML
    void btnBuscarImagen(MouseEvent event) {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            String fileAsString = file.toString();
            image.setText(fileAsString);
        } else {
            image.setText(null);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        houropen.setText(restaurant.getOpeningTime().toString());
//        hourclose.setText(restaurant.getClosingTime().toString());
       /* name.setText(restaurant.getName());
        phoneNumber.setText(restaurant.getPhone());
        address.setText(restaurant.getAddress());*/

    }
}
