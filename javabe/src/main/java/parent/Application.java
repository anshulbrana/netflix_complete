package parent;

import org.hibernate.SessionFactory;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import parent.service.DAO.MovieJPADAO;
import parent.service.DAO.MovieUserJPADAO;
import parent.service.DAO.RoleJPADAO;
import parent.repo.MovieRepo;
import parent.repo.MovieUserRepo;
import parent.repo.RoleRepo;
import parent.repo.UserRepo;
import parent.service.DAO.UserJPADAO;
import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:postgresql://localhost:5432/epita_netflix", "postgres", "admin@123!@#");
    }
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(@Autowired DataSource dataSource){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("parent.datamodel");
        Properties properties = new Properties();
        properties.setProperty("hibernate.properties", PostgreSQL10Dialect.class.getName());
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql","true");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }



    @Bean
    public UserJPADAO getUserDAO(SessionFactory sf){
        return new UserJPADAO(sf);
    }
    @Bean
    public RoleJPADAO getRoleDAO(SessionFactory sf){
        return new RoleJPADAO(sf);
    }
    @Bean
    public MovieJPADAO getMovieDAO(SessionFactory sf){
        return new MovieJPADAO(sf);
    }
    @Bean
    public MovieUserJPADAO getMovieUserDAO(SessionFactory sf){
        return new MovieUserJPADAO(sf);
    }



    @Bean
    public UserRepo getUserRepository(SessionFactory sf,
                                            UserJPADAO userDao
    ){
        return new UserRepo(sf, userDao);
    }

    @Bean
    public RoleRepo getRoleRepository(SessionFactory sf,
                                      RoleJPADAO roleDAO
    ){
        return new RoleRepo(sf, roleDAO);
    }

    @Bean
    public MovieRepo getMovieRepository(SessionFactory sf,
                                        MovieJPADAO movieDAO
    ){
        return new MovieRepo(sf, movieDAO);
    }

    @Bean
    public MovieUserRepo getMovieUserRepository(SessionFactory sf,
                                                MovieUserJPADAO movieUserDAO
    ){
        return new MovieUserRepo(sf, movieUserDAO);
    }

}
