package com.sapozhnykov.dao.client.impl;

import com.sapozhnykov.dao.client.ClientDao;
import com.sapozhnykov.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDBDaoImpl implements ClientDao {

    private static final ClientDBDaoImpl CLIENT_DB_DAO = new ClientDBDaoImpl();

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "";

    private ClientDBDaoImpl() {
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement(
                      "CREATE TABLE IF NOT EXISTS CLIENT (ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255), SURNAME VARCHAR(255), EMAIL VARCHAR(255), PHONE VARCHAR(255))"))
        {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client add(String name, String surname, String phone, String email) {
        Client client = null;

        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENT(NAME, SURNAME, EMAIL, PHONE) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS) )
        {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                long newID = resultSet.getLong(1);
                client = new Client(newID, name , surname, phone, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }

    @Override
    public boolean deleteById(long id) {
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("DELETE FROM CLIENT WHERE ID=?;") )
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
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        long clientID;
        String name;
        String surname;
        String email;
        String phone;
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENT;") )
        {
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    clientID = resultSet.getLong("ID");
                    name = resultSet.getString("NAME");
                    surname = resultSet.getString("SURNAME");
                    email = resultSet.getString("EMAIL");
                    phone = resultSet.getString("PHONE");
                    clients.add(new Client(clientID, name, surname, phone, email));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client getById(long id) {
        Client newClient = null;

        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENT WHERE ID=?;") )
        {
            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();

                long clientID = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                String surname = resultSet.getString("SURNAME");
                String email = resultSet.getString("EMAIL");
                String phone = resultSet.getString("PHONE");

                newClient = new Client(clientID, name, surname, phone, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newClient;
    }

    @Override
    public boolean modify(long clientId, String name, String surname, String phone, String email) {

        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("UPDATE CLIENT SET NAME=? , SURNAME=?, EMAIL=?, PHONE=? WHERE ID=?;") )
        {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setLong(5, clientId);
            int rows = statement.executeUpdate();
            if(rows > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ClientDao getInstance() {
        return CLIENT_DB_DAO;
    }
}
