package parent.service.DAO;

import parent.datamodel.MovieUser;
import parent.datamodel.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RoleJPADAO extends JPADAO<Role>{
    public RoleJPADAO(SessionFactory sf) {
        super(sf);
    }

    @Override
    public Query<Role> getAll(Session session) {
        Query<Role> query = session.createQuery(
                "from Role role "
        );

        query.getQueryString();
        return query;
    }

    @Override
    public Query<Role> getCategory(String category, Session session) {
        return null;
    }

    @Override
    public Query<Role> get(Long id, Session session) {

        Query<Role> query = session.createQuery(
                "from Role role " +
                        "where (:id is null or :id = role.id) "
        );

        query.setParameter("id", id);
        query.getQueryString();
        return query;

    }


}
