package FXControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    public Button CheckAvailibility;
    @FXML
    public DatePicker BookDay;
    @FXML
    public ChoiceBox PeopleNo;
    @FXML
    public ChoiceBox DiningArea;
    @FXML
    public ListView ResultView;
    @FXML
    public Spinner BookTime;

    public void CheckAvailibityFunction(ActionEvent event){
       /* try {
            Parent Forward = FXMLLoader.load(getClass().getResource("../FXLoginPage/RestaurantLogin.fxml"));
            Scene ForwardScene = new Scene(Forward);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(ForwardScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("works");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        BookDay.setDayCellFactory(picker -> new DateCell(){
            public void updateItem(LocalDate date,boolean empty){
                super.updateItem(date,empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });





    }
}
