package FXControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPage implements Initializable {

    @FXML
    Button backLogin;
    @FXML
    TextField cFname;
    @FXML
    TextField cLname;
    @FXML
    TextField cEmail;
    @FXML
    TextField cContact;
    @FXML
    TextField cUsername;
    @FXML
    PasswordField cPassword;
    @FXML
    Button cRegister;
    @FXML
    TextField countryCode;
    @FXML
    PasswordField cCpassword;
    @FXML
    Label errorPasword;
    public void ConfirmPassword(KeyEvent event){

       try {
           if(cPassword.getText().isEmpty()){
               Alert errorAlert = new Alert(Alert.AlertType.ERROR);
               errorAlert.setHeaderText("Please Insert Password");
               errorAlert.setContentText("Please input password field first.");
               errorAlert.showAndWait();

           }else {
                if(cPassword.getText().equals(cCpassword.getText())){
                    System.out.println("Works");
                    errorPasword.setText("");
                }else{
                    errorPasword.setText("Your Password Doesn't Match");
                }


           }

       }catch (Exception e){
           System.out.println(e);
       }


    }

    public void Register(ActionEvent event){
        EntityManagerDefault em = new EntityManagerDefault();

        try {
            em.entityManager.getTransaction().begin();




        }catch (Exception e){

        }


    }

    public void goBackToLogin(ActionEvent event){

        try {
            Parent BackloginFx = FXMLLoader.load(getClass().getResource("../LoginPage.fxml"));
            Scene scene = new Scene(BackloginFx);
            Stage backLogin = (Stage)((Node)event.getSource()).getScene().getWindow();
            backLogin.setScene(scene);
            backLogin.show();


        }catch (Exception e){

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
