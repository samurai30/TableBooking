package FXControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPage implements Initializable {

    @FXML
    Button AddRest;
    @FXML
    TextField RestName;
    @FXML
    ListView ChoiceList;
    @FXML
    ChoiceBox Category;
    @FXML
    Button AddToList;

    public void AddToList(ActionEvent event){
        String tt = (String) Category.getSelectionModel().getSelectedItem();

        if(ChoiceList.getItems().contains(tt)){
            System.out.println("Sorry cant add");
        }else {

            ChoiceList.getItems().addAll(tt);
        }

        }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        String list[] = {"Fine Dining","Out Door Dining","In Door Dining"};

        Category.getItems().addAll(list);

    }
}
