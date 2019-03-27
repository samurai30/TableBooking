package FXControllers;

import RestaurantEntityType.RestaurantEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.validation.ConstraintViolation;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class HomePageVendor implements Initializable {

    @FXML
    TextField restName;

    @FXML
    TextField restLocation;

    @FXML
    TextField restLandmark;

    @FXML
    ChoiceBox restStar;

    @FXML

    Button applyButton;

    @FXML
    Label restLocError;
    @FXML
    Label restNameError;
    @FXML
    Label restLandError;


    public void AddRestaurant(ActionEvent event){


        try {

            EntityManagerDefault em = new EntityManagerDefault();
            RestaurantEntity restaurant = new RestaurantEntity();

            restaurant.setName(restName.getText());
            restaurant.setLocation(restLocation.getText());
            restaurant.setLandmark(restLandmark.getText());
            restaurant.setStartRating(4);

            Set<ConstraintViolation<RestaurantEntity>> constraintViolations = em.validator.validate(restaurant);

            if(constraintViolations.size() >0){
                for (ConstraintViolation<RestaurantEntity> violation : constraintViolations) {

                        if("name".equals(violation.getPropertyPath().toString())){
                            System.out.println(violation.getPropertyPath());
                            restNameError.setText(violation.getMessage());
                        }else{
                            restNameError.setText("");
                        }
                        if("landmark".equals(violation.getPropertyPath().toString())){
                            restLandError.setText(violation.getMessage());
                        }else {
                            restLandError.setText("");
                        }
                        if("location".equals(violation.getPropertyPath().toString())){

                            restLocError.setText(violation.getMessage());
                        }
                        else {
                            restLocError.setText("");
                        }
                }
            }else{
                System.out.println("Valid");
            }






        }catch (Exception e){

        }


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
