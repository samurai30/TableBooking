package FXControllers;

import RestaurantEntityType.VendorEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VendorLogin implements Initializable {

    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    @FXML
    Button loginButton;
    @FXML
    Hyperlink forgotPasswordLink;
    @FXML
    Button RegisterButton;
    @FXML
    Label errorMessage;
    public static VendorEntity vendorEntity = new VendorEntity();


    public void LoginVendor(ActionEvent event){

        try{
            EntityManagerDefault em = new EntityManagerDefault();

            @SuppressWarnings("unchecked")
            List<VendorEntity> vendorEntities = em.entityManager.createQuery("SELECT e FROM VendorEntity e WHERE e.username = :user_name ")
                    .setParameter("user_name", usernameField.getText())
                    .getResultList();
            if(vendorEntities.size()>0){

                vendorEntities.forEach((x)-> {

                    if(x.getUsername().equals(usernameField.getText()) && x.getPassword().equals(passwordField.getText())){

                        errorMessage.setText("");
                        vendorEntity = x;
                        try{
                            Parent VendorForward = FXMLLoader.load(getClass().getResource("../HomePageVendor.fxml"));
                            Scene vendorScene = new Scene(VendorForward, 900,600);
                            Stage VendorWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
                            VendorWindow.setScene(vendorScene);
                            VendorWindow.show();

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
