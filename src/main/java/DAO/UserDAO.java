package DAO;

import ServicePackage.UserProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
    private Connection connection;
    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    public UserProfile get(String login) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("select * from users where login= '" + login + "'");
        ResultSet result = stmt.getResultSet();
        result.next();
        UserProfile userProfile = new UserProfile(result.getString(1),result.getString(2),result.getString(3));
        result.close();
        stmt.close();
        return userProfile;
    }


    public void insertUser(UserProfile userProfile) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("insert into users (login, password, email) values " +
                "('" + userProfile.getLogin() +
                "', '" + userProfile.getPass() +
                "', '" + userProfile.getEmail() +"')");
        stmt.close();
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (login varchar(256), password varchar(256), email varchar(256), PRIMARY KEY(login))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("drop table users");
        stmt.close();
    }
}
