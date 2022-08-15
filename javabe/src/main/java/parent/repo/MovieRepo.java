package parent.repo;

import parent.datamodel.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import parent.service.DAO.MovieJPADAO;

import javax.transaction.Transactional;
import java.util.List;

public class MovieRepo {
    private final SessionFactory sessionFactory;
    private final MovieJPADAO movieJPADAO;

    public MovieRepo(SessionFactory sf,
                           MovieJPADAO movieDAO
    ) {
        this.sessionFactory = sf;
        this.movieJPADAO = movieDAO;
    }

    public List<Movie> getMovies() {
        Session session = getSession();
        List<Movie> searchResult = this.movieJPADAO.search();
        return searchResult;
    }

    public List<Movie> getbyCategory(String category){
        Session session = getSession();
        List<Movie> searchResult = this.movieJPADAO.searchCategory(category);
        try {
            return searchResult;
        } catch (Exception e){
            return null;
        }
    }

    @Transactional
    public Movie createMovie(Movie movie){
        this.movieJPADAO.create(movie);
        return getMovies().get(0);
    }

    public Movie getById(Long id){
        Session session = getSession();
        List<Movie> searchResult = this.movieJPADAO.search(id);
        return searchResult.get(0);
    }



    @Transactional
    public void deleteByName(String name) {
        this.movieJPADAO.delete(name);
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
