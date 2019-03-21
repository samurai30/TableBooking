import RestaurantEntity.RestaurantEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {

    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.restaurant");

        RestaurantEntity restaurantEntity = new RestaurantEntity();

        restaurantEntity.setName("Domnic Thana");



        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(restaurantEntity);

        entityManager.getTransaction().commit();


        entityManagerFactory.close();
    }
}
