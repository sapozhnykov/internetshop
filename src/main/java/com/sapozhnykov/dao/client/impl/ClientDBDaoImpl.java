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

//        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
//              PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENT(NAME, SURNAME, EMAIL, PHONE) VALUES(?, ?, ?, ?)") )
//        {
////            statement.execute("INSERT INTO CLIENT(NAME, SURNAME, EMAIL, PHONE) VALUES('TEST', 'TEST', 'test@gmail.com', '0501111111');");
//            statement.setString(1, "VASYA");
//            statement.setString(2, "POPOV");
//            statement.setString(3, "vasya@gmail.com");
//            statement.setString(4, "0509999999");
//            statement.execute();
//
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("DB NOT WORK!!!");
////            e.printStackTrace();
//        }
    }

    @Override
    public Client add(String name, String surname, String phone, String email) {
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENT(NAME, SURNAME, EMAIL, PHONE) VALUES(?, ?, ?, ?)") )
        {
//            statement.execute("INSERT INTO CLIENT(NAME, SURNAME, EMAIL, PHONE) VALUES('TEST', 'TEST', 'test@gmail.com', '0501111111');");
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.execute();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SOMETHING WRONG!!!");
//            e.printStackTrace();
        }
        Client client = new Client(1000, name , surname, phone, email);
        return client;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        String clientID;
        String name;
        String surname;
        String email;
        String phone;
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              Statement statement = connection.createStatement() )
        {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENT;")){
                while(resultSet.next()) {
                    clientID = resultSet.getString(1);
                    name = resultSet.getString(2);
                    surname = resultSet.getString(3);
                    email = resultSet.getString("EMAIL");
                    phone = resultSet.getString("PHONE");
                    clients.add(new Client(Long.parseLong(clientID), name, surname, phone, email));
                }
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("SOMETHING WRONG!!!");
//            e.printStackTrace();
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

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
//            resultSet.previous();

            long clientID = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            String email = resultSet.getString("EMAIL");
            String phone = resultSet.getString("PHONE");

            newClient = new Client(clientID, name, surname, phone, email);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SOMETHING WRONG!!!");
//            e.printStackTrace();
        }

        return newClient;
    }

    @Override
    public boolean modify(long clientId, String name, String surname, String phone, String email) {
        return false;
    }

    public static ClientDao getInstance() {
        return CLIENT_DB_DAO;
    }
}
