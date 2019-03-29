package FXControllers;

import RestaurantEntityType.CategoryEntity;
import RestaurantEntityType.RestaurantEntity;
import RestaurantEntityType.TablesEntity;
import TableCollectionData.Tables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.validation.ConstraintViolation;
import java.net.URL;
import java.util.*;

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
    @FXML
    TableView<Tables> TablesList;
    @FXML
    TextField tableSize;
    @FXML
    Button AddTable;
    @FXML
    Button submitTables;
    @FXML
    Label errorCategorySelect;
    @FXML
    Label errorTableSize;
    @FXML
    TableColumn<Tables,String> TableTypeColumn;
    @FXML
    TableColumn<Tables, Integer> TableSizeColumn;
    @FXML
    TableColumn TableDeleteColumn;
    @FXML
    Label errorSubmit;
    @FXML
    Button logoutVendor;


    public void logoutVendor(ActionEvent event){

        try{
            Parent Logout = FXMLLoader.load(getClass().getResource("../VendorLogin.fxml"));
            Scene scene = new Scene(Logout,600,400);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            VendorLogin.vendorEntity = null;
            window.show();

        }catch (Exception e){

        }finally {

        }
    }

    private ObservableList<Tables> TablesModel = FXCollections.observableArrayList();
    public void AddTables(ActionEvent event){


        if(!categoryDropdown.getSelectionModel().isEmpty() && tableSize.getText().matches("^|[1-5]")){
            TablesModel.add(new Tables(Integer.parseInt(tableSize.getText()),categoryDropdown.getSelectionModel().getSelectedItem().toString()));
            TablesList.setItems(TablesModel);
        }else{

            System.out.println("doesnt");
        }

    }

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

    public void SubmitTables(ActionEvent event){

        if(!TablesList.getItems().isEmpty()){

            errorSubmit.setText("");


            List<Tables> table = TablesList.getItems();

            table.forEach((x)->{

                     try{
                         EntityManagerDefault em = new EntityManagerDefault();

                         em.entityManager.getTransaction().begin();
                         @SuppressWarnings("unchecked")
                         List<CategoryEntity> catId = em.entityManager.createQuery("SELECT e FROM CategoryEntity e WHERE e.categoryName= :catName")
                                 .setParameter("catName",x.getTableCat())
                                 .getResultList();
                         @SuppressWarnings("unchecked")
                         List<RestaurantEntity> resEnt = em.entityManager.createQuery("SELECT e FROM RestaurantEntity e WHERE e.ven_id=:venID")
                                 .setParameter("venID",VendorLogin.vendorEntity.getId())
                                 .getResultList();

                         TablesEntity tablesEntity = new TablesEntity();

                         tablesEntity.setRest_id(resEnt.get(0).getRest_id());
                         tablesEntity.setCat_id( catId.get(0).getId());
                         tablesEntity.setStatus("Available");
                         tablesEntity.setTableSize(x.getTablesize());

                         em.entityManager.merge(tablesEntity);
                         em.entityManager.getTransaction().commit();


                     }catch (Exception e){

                     }finally {

                     }



            });



        }
        else {
            errorSubmit.setText("Please add tables");
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("tableCat"));
        TableSizeColumn.setCellValueFactory(new PropertyValueFactory<>("tablesize"));
        TableDeleteColumn.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        TableDeleteColumn.setCellFactory(cellFactory);

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

        em.entityManager.close();

    }
    Callback<TableColumn<Tables, String>, TableCell<Tables, String>> cellFactory
            = //
            new Callback<TableColumn<Tables, String>, TableCell<Tables, String>>() {
                @Override
                public TableCell call(final TableColumn<Tables, String> param) {
                    final TableCell<Tables, String> cell = new TableCell<Tables, String>() {

                        final Button btn = new Button("Delete");
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    getTableView().getItems().remove(getIndex());
                                });
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };

}
