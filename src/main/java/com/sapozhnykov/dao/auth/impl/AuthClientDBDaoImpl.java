package com.sapozhnykov.dao.auth.impl;

import com.sapozhnykov.dao.auth.AuthClientDao;
import com.sapozhnykov.domain.AuthClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthClientDBDaoImpl implements AuthClientDao {
    private static final AuthClientDao AUTH_CLIENT_DAO = new AuthClientDBDaoImpl();

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "";

    private AuthClientDBDaoImpl() {
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement(
                      "CREATE TABLE IF NOT EXISTS AUTHCLIENT (ID BIGINT PRIMARY KEY AUTO_INCREMENT, USERID BIGINT, PHONE VARCHAR(255), PASSWORD VARCHAR(255))"))
        {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AuthClient checkSingIn(String phone, String password) {
        AuthClient authClient = null;

        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("SELECT * FROM AUTHCLIENT WHERE PHONE=? AND PASSWORD=?;") )
        {
            statement.setString(1, phone);
            statement.setString(2, password);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    long authID = resultSet.getLong("ID");
                    long tempUserID = resultSet.getLong("USERID");
                    String tempPhone = resultSet.getString("PHONE");
                    String tempPassword = resultSet.getString("PASSWORD");

                    authClient = new AuthClient(tempUserID, authID, tempPhone, tempPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authClient;
    }

    @Override
    public boolean add(long userId, String phone, String password) {
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("INSERT INTO AUTHCLIENT(USERID, PHONE, PASSWORD) VALUES(?, ?, ?)"))
        {
            statement.setLong(1, userId);
            statement.setString(2, phone);
            statement.setString(3, password);
            int rows = statement.executeUpdate();

            if(rows > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("DELETE FROM AUTHCLIENT WHERE ID=?;") )
        {
            statement.setLong(1, id);
            int rows = statement.executeUpdate();
            if(rows > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<AuthClient> getAll() {
        List<AuthClient> authClients = new ArrayList<>();

        long authID;
        long userID;
        String phone;
        String password;

        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("SELECT * FROM AUTHCLIENT;") )
        {
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    authID = resultSet.getLong("ID");
                    userID = resultSet.getLong("USERID");
                    phone = resultSet.getString("PHONE");
                    password = resultSet.getString("PASSWORD");
                    authClients.add(new AuthClient(authID, userID, phone, password));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authClients;
    }

    @Override
    public boolean isLoginExists(String phone) {
        boolean result = false;

        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("SELECT * FROM AUTHCLIENT WHERE PHONE=?;") )
        {
            statement.setString(1, phone);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static AuthClientDao getInstance() {
        return AUTH_CLIENT_DAO;
    }
}
