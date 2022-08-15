package parent.service.DAO;

import parent.datamodel.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class MovieJPADAO extends JPADAO<Movie> {

    public MovieJPADAO(SessionFactory sf) {

        super(sf);
    }

    @Override
    public Query<Movie> getAll(Session session) {
        Query<Movie> query = session.createQuery(
                "from Movie movie "
        );

        query.getQueryString();
        return query;
    }

    @Override
    public Query<Movie> get(Long id, Session session) {

        Query<Movie> query = session.createQuery(
                "from Movie movie " +
                        "where (:id is null or :id = movie.id) "
        );

        query.setParameter("id", id);
        query.getQueryString();
        return query;

    }

    @Override
    public Query<Movie> getCategory(String category, Session session) {

        Query<Movie> query = session.createQuery(
                "from Movie movie " +
                        "where (:category is null or :category = movie.category) "
        );

        query.setParameter("category", category);
        query.getQueryString();
        return query;

    }

}
