package parent.repo;

import parent.datamodel.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import parent.service.DAO.RoleJPADAO;

import javax.transaction.Transactional;
import java.util.List;

public class RoleRepo {
    private final SessionFactory sessionFactory;
    private final RoleJPADAO roleJPADAO;


    public RoleRepo(SessionFactory sf,
                          RoleJPADAO roleDAO
    ) {
        this.sessionFactory = sf;
        this.roleJPADAO = roleDAO;
    }

    @Transactional
    public Role createRole(Role role){
        this.roleJPADAO.create(role);
        return getRoles().get(0);
    }

    public List<Role> getRoles() {
        Session session = getSession();
        List<Role> searchResult = this.roleJPADAO.search();
        return searchResult;
    }

    public Role getById(Long id){
        Session session = getSession();
        List<Role> searchResult = this.roleJPADAO.search(id);
        return searchResult.get(0);
    }

    @Transactional
    public void deleteByName(String name) {

        this.roleJPADAO.delete(name);

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

