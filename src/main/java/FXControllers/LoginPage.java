package FXControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {

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


    public void Login(ActionEvent event){


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
