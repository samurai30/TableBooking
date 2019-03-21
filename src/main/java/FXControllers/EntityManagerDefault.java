package FXControllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerDefault {

    public EntityManagerFactory entityManagerFactory;
    public EntityManager entityManager;

    EntityManagerDefault(){
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.restaurant");
        entityManager = entityManagerFactory.createEntityManager();
    }
}
