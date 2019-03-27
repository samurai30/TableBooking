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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.EventListener;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterPage implements Initializable {
    @FXML
    ToggleGroup registerTYPE;
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

    public boolean checkContact(String s){

        boolean containsString = false;
        if (s != null && !s.isEmpty()){

            for (char c : s.toCharArray()){

                if(containsString = Character.isAlphabetic(c)){
                    break;
                }
            }
        }

        return containsString;
    }

    public boolean checkUsername(){
        EntityManagerDefault em = new EntityManagerDefault();

        @SuppressWarnings("unchecked")
        List<CustomerEntity> ls =  em.entityManager.createQuery("SELECT e FROM CustomerEntity e WHERE e.username = :custname")
                .setParameter("custname", cUsername.getText()).getResultList();

        return ls.size() == 0;
    }


    public boolean checkCred(){

        if(cFname.getLength()<3){
            errorPasword.setText("First Name must be 3 character long");
            return false;

        }
        else if(cLname.getLength()<5){
            errorPasword.setText("Last Name must be 3 character long");
            return false;
        }
        else if(cContact.getLength()<10 ){
            errorPasword.setText("Please enter 10 digit number");
            return false;
        }else if(checkContact(cContact.getText())){
            errorPasword.setText("Contact must not contain Text");
        }
        else if(cUsername.getLength()<6){
            errorPasword.setText("Username must be 6 character long");
        }
        else if(!passwordCheck()){
            return false;
        }
        else if(!checkUsername() || cUsername.getLength()<5 || cCpassword.getText().isEmpty() || cUsername.getText().isEmpty()){
            errorPasword.setText("Username already used");
            return false;
        }
        return true;
    }

    public boolean passwordCheck(){
        if(cPassword.getText().isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.WARNING);
            errorAlert.setHeaderText("Please Insert Password");
            errorAlert.setContentText("Please input password field first.");
            errorAlert.showAndWait();

            return false;
        }else {
            if(cPassword.getText().equals(cCpassword.getText())){
                errorPasword.setText("");
            return true;
            }else{
                errorPasword.setText("Your Password Doesn't Match");
            return false;
            }
        }

    }
    public void ConfirmPassword(KeyEvent event){

       try {
        passwordCheck();
       }catch (Exception e){
           System.out.println(e);
       }


    }

    public void uniqueUsername(KeyEvent event){

    try {
        if (checkUsername()){
            errorPasword.setText("");
        }
        else {
            errorPasword.setText("Username already used");
        }
    }catch (Exception e){
        System.out.println("no result");
    }
    }

    public void Register(ActionEvent event){

        EntityManagerDefault em = new EntityManagerDefault();
        RadioButton rb = (RadioButton) registerTYPE.getSelectedToggle();

        try {
            if (checkCred()){
            errorPasword.setText("");
            if(rb.getText().equals("Customer")){

                em.entityManager.getTransaction().begin();
                CustomerEntity customer = new CustomerEntity();

                customer.setlName(cLname.getText());
                customer.setfName(cFname.getText());
                customer.setEmail(cEmail.getText());
                customer.setContact(Long.parseLong(cContact.getText()));
                customer.setUsername(cUsername.getText());
                customer.setPassword(cPassword.getText());

                em.entityManager.persist(customer);
                em.entityManager.getTransaction().commit();
                registerSuccess(event);


            }else if(rb.getText().equals("Vendor")){

                em.entityManager.getTransaction().begin();

                VendorEntity vendor = new VendorEntity();

                vendor.setFirstName(cFname.getText());
                vendor.setLastName(cLname.getText());
                vendor.setEmail(cEmail.getText());
                vendor.setContact(Long.parseLong(cContact.getText()));
                vendor.setUsername(cUsername.getText());
                vendor.setPassword(cPassword.getText());

                em.entityManager.persist(vendor);
                em.entityManager.getTransaction().commit();
                registerSuccess(event);

            }




            }


        }catch (Exception e){
            System.out.println("Sorry cannot Insert");
        }finally {


            em.entityManager.close();
            em.entityManagerFactory.close();
        }

    }
    public void registerSuccess(ActionEvent event){
        try {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setHeaderText("Registered Successfully");
            successAlert.setContentText("Please login");
            successAlert.showAndWait();
            RadioButton rb = (RadioButton) registerTYPE.getSelectedToggle();
            Parent loginSuccess;
            if(rb.getText().equals("Customer")){
                 loginSuccess = FXMLLoader.load(getClass().getResource("../LoginPage.fxml"));

            }else
            {
                 loginSuccess = FXMLLoader.load(getClass().getResource("../VendorLogin.fxml"));

            }
            Scene successScene = new Scene(loginSuccess,600,400);
            Stage successWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            successWindow.setScene(successScene);
            successWindow.show();
        }catch (Exception e){

        }finally {

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
