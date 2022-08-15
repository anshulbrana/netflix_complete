package parent.service.DAO;

import parent.datamodel.Role;
import parent.datamodel.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class UserJPADAO extends JPADAO<User>{
    public UserJPADAO(SessionFactory sf) {
        super(sf);
    }

    @Override
    public Query<User> getAll(Session session) {
        Query<User> query = session.createQuery(
                "from User user "
        );

        query.getQueryString();
        return query;
    }

    @Override
    public Query<User> getCategory(String category, Session session) {
        return null;
    }

    @Override
    public Query<User> get(Long id, Session session) {
        return null;
    }

}

