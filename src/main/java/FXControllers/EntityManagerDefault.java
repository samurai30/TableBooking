package FXControllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class EntityManagerDefault {

    public EntityManagerFactory entityManagerFactory;
    public EntityManager entityManager;
    ValidatorFactory factory;
    Validator validator ;

    
    EntityManagerDefault(){
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.restaurant");
        entityManager = entityManagerFactory.createEntityManager();
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }
}
