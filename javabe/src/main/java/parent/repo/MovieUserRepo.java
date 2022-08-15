package parent.repo;

import parent.datamodel.MovieUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import parent.service.DAO.MovieUserJPADAO;

import javax.transaction.Transactional;
import java.util.List;

public class MovieUserRepo {
    private final SessionFactory sessionFactory;
    private final MovieUserJPADAO movieUserJPADAO;

    public MovieUserRepo(SessionFactory sf,
                               MovieUserJPADAO movieUserDAO
    ) {
        this.sessionFactory = sf;
        this.movieUserJPADAO = movieUserDAO;
    }

    public List<MovieUser> getMovies() {
        Session session = getSession();
        List<MovieUser> searchResult = this.movieUserJPADAO.search();
        return searchResult;
    }

    public List<MovieUser> getAllWithRelationships() {
        Session session = getSession();
        List<MovieUser> searchResult = this.movieUserJPADAO.searchWithRelationship(session);
        return searchResult;
    }

    public List<MovieUser> getOneWithRelationships(Long id) {
        Session session = getSession();
        List<MovieUser> searchResult = this.movieUserJPADAO.searchOneWithRelationship(id,session);
        return searchResult;
    }
    @Transactional
    public MovieUser createMovieUser(MovieUser movieUser){
        this.movieUserJPADAO.create(movieUser);
        return getMovies().get(0);
    }



    private Session getSession() {
        Session currentSession = null;
        try {
            currentSession = this.sessionFactory.getCurrentSession();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (currentSession != null && currentSession.isOpen()) {
            return currentSession;
        } else {
            return this.sessionFactory.openSession();
        }
    }
}

