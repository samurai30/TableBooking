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

public class BookingPage implements Initializable {

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
