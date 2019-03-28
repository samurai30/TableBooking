package FXControllers;

import RestaurantEntityType.CategoryEntity;
import RestaurantEntityType.RestaurantEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.validation.ConstraintViolation;
import java.net.URL;
import java.util.List;
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
    @FXML
    Tab restApplyTab;
    @FXML
    Label startError;
    @FXML
    ChoiceBox restType;
    @FXML
    Label typeError;
    @FXML
    ChoiceBox categoryDropdown;
    public void AddRestaurant(ActionEvent event){


        try {
            if(restStar.getSelectionModel().isEmpty()){
                startError.setText("Please Select.");
            }
            else {
                startError.setText("");
            }
            if(restType.getSelectionModel().isEmpty()){
                typeError.setText("Please Select");
            }else {
                typeError.setText("");
            }


            String stars = String.valueOf(restStar.getSelectionModel().getSelectedItem().toString());
             EntityManagerDefault em = new EntityManagerDefault();
            RestaurantEntity restaurant = new RestaurantEntity();

            restaurant.setName(restName.getText());
            restaurant.setLocation(restLocation.getText());
            restaurant.setLandmark(restLandmark.getText());
            restaurant.setStartRating(Integer.parseInt(stars));
            restaurant.setRestType(restType.getSelectionModel().getSelectedItem().toString());
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
                restaurant.setVen_id(VendorLogin.vendorEntity.getId());
                restaurant.setVerified("unverified");
                em.entityManager.getTransaction().begin();
                em.entityManager.persist(restaurant);
                em.entityManager.getTransaction().commit();

                em.entityManager.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Successfully added");
                alert.setContentText("Added Restaurant Please add tables in the Add tables section");
                alert.setTitle("SUCCESS");
                alert.showAndWait();
                restApplyTab.setDisable(true);
            }






        }catch (Exception e){

        }


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] stars = {"1","2","3","4","5"};
        String [] type = {"Veg","Non-Veg"};
        restType.getItems().addAll(type);
        restStar.getItems().addAll(stars);

        EntityManagerDefault em = new EntityManagerDefault();
        @SuppressWarnings("unchecked")
        List<RestaurantEntity> restaurants = em.entityManager.createQuery("SELECT e FROM RestaurantEntity e WHERE e.ven_id = :venId")
                .setParameter("venId",VendorLogin.vendorEntity.getId())
                .getResultList();
        if(restaurants.size() > 0){
            restApplyTab.setDisable(true);
        }else {
            restApplyTab.setDisable(false);
        }

        @SuppressWarnings("unchecked")
        List<String> categoryList= em.entityManager.createQuery("SELECT e.categoryName FROM CategoryEntity e")
                .getResultList();


        if(categoryList.size()>0){
            categoryDropdown.getItems().addAll(categoryList);
        }

    }
}
