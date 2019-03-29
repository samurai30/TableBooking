package FXControllers;

import RestaurantEntityType.RestaurantEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageUser implements Initializable {

    @FXML
    Button logoutButton;
    @FXML
    ChoiceBox typeChoice;
    @FXML
    ListView restList;
    @FXML
    Label errorMessageBook;
    @FXML
    Button bookButton;
    @FXML
    Button searchButton;
    public void LogoutUser(ActionEvent event){
        try{
            Parent Logout = FXMLLoader.load(getClass().getResource("../LoginPage.fxml"));
            Scene scene = new Scene(Logout,600,400);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            LoginPage.customer = null;
            window.show();

        }catch (Exception e){

        }finally {

        }

    }

    public void SearchRest(ActionEvent event){
        if(!typeChoice.getSelectionModel().isEmpty()){
            restList.getItems().clear();
            errorMessageBook.setText("");
            EntityManagerDefault em = new EntityManagerDefault();
            @SuppressWarnings("unchecked")
            List<RestaurantEntity> entities = em.entityManager.createQuery("SELECT e FROM RestaurantEntity e WHERE e.restType=:resttype")
                    .setParameter("resttype",typeChoice.getSelectionModel().getSelectedItem().toString())
                    .getResultList();

            entities.forEach((x)->{
                restList.getItems().addAll(x.getName());
            });

        }else {

            restList.getItems().clear();
            errorMessageBook.setText("Please select a Type");

        }

    }

    public static String restName="";

    public void BookRestaurant(ActionEvent event){

        if(!restList.getSelectionModel().isEmpty()){
            errorMessageBook.setText("");
            restName = restList.getSelectionModel().getSelectedItems().toString();
            try{
                Parent CustomerForward = FXMLLoader.load(getClass().getResource("../BookingPage.fxml"));
                Scene customerScene = new Scene(CustomerForward, 900,600);
                Stage customerWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
                customerWindow.setScene(customerScene);
                customerWindow.show();

            }catch (Exception e){


            }


        }else {
            errorMessageBook.setText("Please select a restaurant");
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            String[] types = {"Veg","Non-Veg"};

            typeChoice.getItems().addAll(types);



    }
}
