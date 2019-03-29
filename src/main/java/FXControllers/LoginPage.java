package FXControllers;

import RestaurantEntityType.CustomerEntity;
import RestaurantEntityType.VendorEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;
    @FXML
    Hyperlink forgotPasswordLink;
    @FXML
    Button RegisterButton;
    @FXML
    Label errorMessage;


    public static CustomerEntity customer =  new CustomerEntity();


    public void Login(ActionEvent event){
        try{
            EntityManagerDefault em = new EntityManagerDefault();

            @SuppressWarnings("unchecked")
            List<CustomerEntity> customerEntities = em.entityManager.createQuery("SELECT e FROM CustomerEntity e WHERE e.username = :user_name ")
                    .setParameter("user_name", usernameField.getText())
                    .getResultList();
            if(customerEntities.size()>0){

                customerEntities.forEach((x)-> {

                    if(x.getUsername().equals(usernameField.getText()) && x.getPassword().equals(passwordField.getText())){

                        errorMessage.setText("");
                        customer = x;
                        try{
                            Parent CustomerForward = FXMLLoader.load(getClass().getResource("../HomePageUser.fxml"));
                            Scene customerScene = new Scene(CustomerForward, 900,600);
                            Stage customerWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
                            customerWindow.setScene(customerScene);
                            customerWindow.show();

                        }catch (Exception e){


                        }

                    }else {

                        errorMessage.setText("Invalid username or password");
                    }

                });

            }else {

                errorMessage.setText("Invalid username or password");
            }


        }catch (Exception e){

        }

    }
    public void NewRegister(ActionEvent event){

        try{
            Parent Register = FXMLLoader.load(getClass().getResource("../RegisterPage.fxml"));
            Scene scene = new Scene(Register,700,500);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }catch (Exception e){

        }finally {

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
