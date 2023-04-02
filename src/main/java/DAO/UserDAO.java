package DAO;

import ServicePackage.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDAO {
    private Session session;
    public UserDAO(Session session) {
        this.session = session;
    }
    public UserProfile get(long id){
        return (UserProfile) session.load(UserProfile.class, id);
    }

    public UserProfile getBy(String variable, String value){
        Criteria criteria = session.createCriteria(UserProfile.class);
        return (UserProfile) criteria.add(Restrictions.eq(variable, value)).uniqueResult();
    }
    public void insertUser(UserProfile userProfile){
        session.save(userProfile);
    }

}
