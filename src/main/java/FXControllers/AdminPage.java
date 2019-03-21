package FXControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    @FXML
    Label ErrorAddList;

         public void AddToList(ActionEvent event){
            String tt = String.valueOf(Category.getSelectionModel().getSelectedItem());

                if(ChoiceList.getItems().contains(tt)){
                    ErrorAddList.setText("This Item already inserted");
                }
                else if(Category.getSelectionModel().isEmpty())
                {
                    ErrorAddList.setText("Please Choose One");
                }
                else {
                    ErrorAddList.setText("");
                    ChoiceList.getItems().addAll(tt);
                }

            }

        public void AddToDatabase(ActionEvent event)
        {

           if(ChoiceList.getItems().isEmpty() || RestName.getText().isEmpty())
           {
               ErrorAddList.setText("Please Select:");

           }else {
               ErrorAddList.setText("");

               for (int i=0;i<ChoiceList.getItems().size();i++){


               }

           }


        }



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        String list[] = {"Fine Dining","Out Door Dining","In Door Dining"};

        Category.getItems().addAll(list);

    }
}
