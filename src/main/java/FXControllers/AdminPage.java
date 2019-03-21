package FXControllers;

import RestaurantEntityType.RestaurantEntity;
import RestaurantEntityType.TablesEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import javax.persistence.EntityManager;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class AdminPage implements Initializable {

    @FXML
    Button AddRest;
    @FXML
    TextField RestName;
    @FXML
    ListView ChoiceList;
    @FXML
    ChoiceBox Category;
    @FXML
    Button AddToList;
    @FXML
    Label ErrorAddList;

         public void AddToList(ActionEvent event){
            String tt = String.valueOf(Category.getSelectionModel().getSelectedItem());

                if(ChoiceList.getItems().contains(tt)){
                    ErrorAddList.setText("This Item already inserted");
                }
                else if(Category.getSelectionModel().isEmpty())
                {
                    ErrorAddList.setText("Please Choose One");
                }
                else {
                    ErrorAddList.setText("");
                    ChoiceList.getItems().addAll(tt);
                }

            }

        public void AddToDatabase(ActionEvent event)
        {
            EntityManagerDefault em = new EntityManagerDefault();

            try {

                if(ChoiceList.getItems().isEmpty() || RestName.getText().isEmpty())
                {
                    ErrorAddList.setText("Please Select:");

                }else {
                    em.entityManager.getTransaction().begin();

                    ErrorAddList.setText("");

                    RestaurantEntity rst = new RestaurantEntity();



                    rst.setName(RestName.getText());

                    List<String> list = ChoiceList.getItems();

                    System.out.println(list);

                    Set<TablesEntity> LsTbe = new HashSet<>();

                    for (String s: list){
                        TablesEntity tbe =new TablesEntity();

                        tbe.setType(s);

                        LsTbe.add(tbe);

                    }

                    rst.setTablesEntities(LsTbe);
                    em.entityManager.persist(rst);
                    em.entityManager.getTransaction().commit();

                }
            }catch (Exception e){

            }finally {
                em.entityManagerFactory.close();
            }




        }



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        String list[] = {"Fine Dining","Out Door Dining","In Door Dining"};

        Category.getItems().addAll(list);

    }
}
