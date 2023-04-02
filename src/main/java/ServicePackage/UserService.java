package ServicePackage;

import DAO.UserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;


public  class UserService {

    private static final Map<String, UserProfile> sessions = new HashMap<>();
    private static final SessionFactory sessionFactory = createSessionFactory(createConfiguration());


    public static void addNewUser(UserProfile userProfile) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDAO dao = new UserDAO(session);
        dao.insertUser(userProfile);
        transaction.commit();
        session.close();
    }

    public static UserProfile getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        UserDAO dao = new UserDAO(session);
        UserProfile profile = dao.getBy("login", login);
        session.close();
        return profile;
    }

    public static UserProfile getUserByEmail(String login) {
        Session session = sessionFactory.openSession();
        UserDAO dao = new UserDAO(session);
        UserProfile profile = dao.getBy("email", login);
        session.close();
        return profile;
    }
    public static UserProfile get(long id) {
        Session session = sessionFactory.openSession();
        UserDAO dao = new UserDAO(session);
        UserProfile profile = dao.get(id);
        session.close();
        return profile;
    }

    public static UserProfile getUserBySessionId(String sessionId) {
        return sessions.get(sessionId);
    }

    public static void addSession(String sessionId, UserProfile userProfile) {
        sessions.put(sessionId, userProfile);
    }

    public static void deleteSession(String sessionId) {
        sessions.remove(sessionId);
    }
    private static Configuration createConfiguration(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserProfile.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "123456");
        configuration.setProperty("hibernate.show_sql", "false");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }
    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
