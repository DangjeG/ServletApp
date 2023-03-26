package ServicePackage;

import DAO.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public  class UserService {

    private static final Map<String, UserProfile> sessions = new HashMap<>();
    private static final Connection connection = createConnection();

    public static void addNewUser(UserProfile userProfile) {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = new UserDAO(connection);
            dao.createTable();
            dao.insertUser(userProfile);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public static UserProfile getUserByLogin(String login) {
        try {
            return (new UserDAO(connection).get(login));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
    private static Connection createConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "123456");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
