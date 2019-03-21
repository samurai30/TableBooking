import RestaurantEntityType.RestaurantEntity;
import RestaurantEntityType.TablesEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;


public class main {

    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.restaurant");

        RestaurantEntity restaurantEntity = new RestaurantEntity();
        RestaurantEntity restaurantEntity2 = new RestaurantEntity();


        TablesEntity tablesEntity = new TablesEntity();
        tablesEntity.setType("Fine Dining");
        TablesEntity tablesEntity1 =  new TablesEntity();
        tablesEntity1.setType("Out Door Dining");
        TablesEntity tablesEntity2 =  new TablesEntity();
        tablesEntity2.setType("In Door Dining");

        Set<TablesEntity> tablesEntities =  new HashSet<TablesEntity>();
        Set<TablesEntity> tablesEntities1 = new HashSet<TablesEntity>();

        tablesEntities.add(tablesEntity1);

        tablesEntities1.add(tablesEntity);
        tablesEntities1.add(tablesEntity2);

        restaurantEntity.setName("Domnic Thana");
        restaurantEntity2.setName("Suyog Food Center");
        restaurantEntity.setTablesEntities(tablesEntities);
        restaurantEntity2.setTablesEntities(tablesEntities1);


        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(restaurantEntity);
        entityManager.persist(restaurantEntity2);


        entityManager.getTransaction().commit();


        entityManagerFactory.close();
    }
}
