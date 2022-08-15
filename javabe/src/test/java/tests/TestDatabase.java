package tests;


import configuration.BaseTestConfig;
import parent.datamodel.MovieUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BaseTestConfig.class)
public class TestDatabase {

    @Inject
    SessionFactory sf;

//    @Inject
//    static
//    JPADAO<User> userJPADAO;

    @Test
    public  void test(){
        MovieUser movieUser = new MovieUser();
        movieUser.setUsername("EPITA");
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(movieUser);
        transaction.commit();
    }
}
