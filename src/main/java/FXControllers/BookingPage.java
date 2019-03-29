package FXControllers;

import RestaurantEntityType.CategoryEntity;
import RestaurantEntityType.RestaurantEntity;
import RestaurantEntityType.TablesEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class BookingPage implements Initializable {

    @FXML
    public Button CheckAvailibility;
    @FXML
    public DatePicker BookDay;
    @FXML
    public ChoiceBox PeopleNo;
    @FXML
    public ChoiceBox DiningArea;
    @FXML
    public ListView ResultView;
    @FXML
    public Spinner BookTime;

    public void CheckAvailibityFunction(ActionEvent event){
     try {
         if(!PeopleNo.getSelectionModel().isEmpty() && !DiningArea.getSelectionModel().isEmpty() && !BookDay.getValue().toString().equals("") && !BookTime.getValue().toString().equals("")){
             EntityManagerDefault em = new EntityManagerDefault();

             @SuppressWarnings("unchecked")
             List<CategoryEntity> categories = em.entityManager.createQuery("SELECT e.categoryName FROM CategoryEntity e WHERE e.categoryName=:catname")
                     .setParameter("catname",DiningArea.getSelectionModel().getSelectedItem().toString())
                     .getResultList();

             categories.forEach((x)->{
                 @SuppressWarnings("unchecked")
                 List<TablesEntity> tablesEntities = em.entityManager.createQuery("SELECT e FROM TablesEntity e WHERE e.tableSize= :tbSize AND e.cat_id=:tbCat AND e.status = :sts")
                         .setParameter("tbSize",PeopleNo.getSelectionModel().getSelectedItem().toString())
                         .setParameter("tbCat",x.getId())
                         .setParameter("sts","Available")
                         .getResultList();

                 tablesEntities.forEach((g)->{

                     System.out.println(g.getRest_id()+g.getStatus());

                 });


             });


         }else {


             System.out.println("Please Enter the details");
         }
     }catch (Exception e){
         System.out.println("Failed");


         }

    }
    List<RestaurantEntity> restaurantEnt;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        BookDay.setDayCellFactory(picker -> new DateCell(){
            public void updateItem(LocalDate date,boolean empty){
                super.updateItem(date,empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });


        EntityManagerDefault em = new EntityManagerDefault();

        @SuppressWarnings("unchecked")
        List<RestaurantEntity> restaurantEntities = em.entityManager.createQuery("SELECT e FROM RestaurantEntity e WHERE e.name=:restName")
                .setParameter("restName",HomePageUser.restName)
                .getResultList();
        restaurantEnt = restaurantEntities;

        @SuppressWarnings("unchecked")
                List<String> categories = em.entityManager.createQuery("SELECT e.categoryName FROM CategoryEntity e")
                .getResultList();

        DiningArea.getItems().addAll(categories);
        String[] people = {"1","2","3","4","5"};

        PeopleNo.getItems().addAll(people);
        em.entityManager.close();







    }
}
