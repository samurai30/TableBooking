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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import java.math.BigInteger;
import java.net.URL;
import java.util.EventListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
    @FXML
    Label errorFname;
    @FXML
    Label errorLname;
    @FXML
    Label errorEmail;
    @FXML
    Label errorContact;
    @FXML
    Label errorUsername;

    public boolean checkUsername(){
        EntityManagerDefault em = new EntityManagerDefault();

        @SuppressWarnings("unchecked")
        List<CustomerEntity> ls =  em.entityManager.createQuery("SELECT e FROM CustomerEntity e WHERE e.username = :custname")
                .setParameter("custname", cUsername.getText()).getResultList();

        return ls.size() == 0;
    }

    public boolean checkUsernameVendor(){
        EntityManagerDefault em = new EntityManagerDefault();

        @SuppressWarnings("unchecked")
        List<VendorEntity> ls =  em.entityManager.createQuery("SELECT e FROM VendorEntity e WHERE e.username = :custname")
                .setParameter("custname", cUsername.getText()).getResultList();

        return ls.size() == 0;
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
        RadioButton rb = (RadioButton) registerTYPE.getSelectedToggle();
    try {
        if(rb.getText().equals("Customer")){
            if (checkUsername()){
                errorPasword.setText("");
            }
            else {
                errorPasword.setText("Username already used");
            }

        }else if (rb.getText().equals("Vendor")){

            if (checkUsernameVendor()){
                errorPasword.setText("");
            }
            else {
                errorPasword.setText("Username already used");
            }
        }



    }catch (Exception e){
        System.out.println("no result");
    }
    }

    public void Register(ActionEvent event){

        EntityManagerDefault em = new EntityManagerDefault();
        RadioButton rb = (RadioButton) registerTYPE.getSelectedToggle();

        try {
            if(rb.getText().equals("Customer") && checkUsername() && passwordCheck()){

               try {

                   CustomerEntity customer = new CustomerEntity();
                   customer.setlName(cLname.getText());
                   customer.setfName(cFname.getText());
                   customer.setEmail(cEmail.getText());
                   customer.setContact(cContact.getText());
                   customer.setUsername(cUsername.getText());
                   customer.setPassword(cPassword.getText());


                   Set<ConstraintViolation<CustomerEntity>> constraintValidatorSet = em.validator.validate(customer);

                   if(constraintValidatorSet.size()>0){
                       for (ConstraintViolation<CustomerEntity> cust : constraintValidatorSet){
                           if("username".equals(cust.getPropertyPath().toString())){
                               errorUsername.setText(cust.getMessage());
                           }else {
                               errorUsername.setText("");
                           }
                           if("password".equals(cust.getPropertyPath().toString())){
                               errorPasword.setText(cust.getMessage());
                           }else {
                               errorPasword.setText("");
                           }
                           if("contact".equals(cust.getPropertyPath().toString())){
                               errorContact.setText(cust.getMessage());

                           }else {
                               errorContact.setText("");
                           }
                           if("email".equals(cust.getPropertyPath().toString())){
                               errorEmail.setText(cust.getMessage());
                           }else {
                               errorEmail.setText("");
                           }
                           if("fName".equals(cust.getPropertyPath().toString())){
                               errorFname.setText(cust.getMessage());

                           }else {
                               errorFname.setText("");
                           }
                           if("lName".equals(cust.getPropertyPath().toString())){
                               errorLname.setText(cust.getMessage());
                           }else {
                               errorLname.setText("");
                           }



                       }
                   }else {
                       em.entityManager.getTransaction().begin();
                       em.entityManager.persist(customer);
                       em.entityManager.getTransaction().commit();
                       registerSuccess(event);
                   }

               }catch (Exception e){
                   System.out.println("Something went wrong");
               }


            }else if(rb.getText().equals("Vendor")  && checkUsernameVendor() && passwordCheck()){


               try {
                   VendorEntity vendor = new VendorEntity();

                   vendor.setFirstName(cFname.getText());
                   vendor.setLastName(cLname.getText());
                   vendor.setEmail(cEmail.getText());
                   vendor.setContact(cContact.getText());
                   vendor.setUsername(cUsername.getText());
                   vendor.setPassword(cPassword.getText());

                   Set<ConstraintViolation<VendorEntity>> violations = em.validator.validate(vendor);

                   if(violations.size()>0){

                        for (ConstraintViolation<VendorEntity> ven : violations){
                            if("username".equals(ven.getPropertyPath().toString())){
                                errorUsername.setText(ven.getMessage());
                            }else {
                                errorUsername.setText("");
                            }
                            if("password".equals(ven.getPropertyPath().toString())){
                                errorPasword.setText(ven.getMessage());
                            }else {
                                errorPasword.setText("");
                            }
                            if("contact".equals(ven.getPropertyPath().toString())){
                                errorContact.setText(ven.getMessage());

                            }else {
                                errorContact.setText("");
                            }
                            if("email".equals(ven.getPropertyPath().toString())){
                                errorEmail.setText(ven.getMessage());
                            }else {
                                errorEmail.setText("");
                            }
                            if("firstName".equals(ven.getPropertyPath().toString())){
                                errorFname.setText(ven.getMessage());

                            }else {
                                errorFname.setText("");
                            }
                            if("lastName".equals(ven.getPropertyPath().toString())){
                                errorLname.setText(ven.getMessage());
                            }else {
                                errorLname.setText("");
                            }
                       }

                   }else {
                       em.entityManager.getTransaction().begin();

                       em.entityManager.persist(vendor);
                       em.entityManager.getTransaction().commit();
                       registerSuccess(event);
                   }

               }catch (Exception e){
                   System.out.println("Something went wrong");
               }


            }else {
                System.out.println("Please enter valid details");
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
