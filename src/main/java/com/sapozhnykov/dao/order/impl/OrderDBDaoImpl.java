package com.sapozhnykov.dao.order.impl;

import com.sapozhnykov.dao.order.OrderDao;
import com.sapozhnykov.domain.Order;
import com.sapozhnykov.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDBDaoImpl implements OrderDao {
    private static final OrderDao ORDER_DAO = new OrderDBDaoImpl();

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "";

    private OrderDBDaoImpl() {
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement(
                      "CREATE TABLE IF NOT EXISTS ORDERS (ID BIGINT PRIMARY KEY AUTO_INCREMENT, CLIENTID BIGINT);" +
                              "CREATE TABLE IF NOT EXISTS ORDER_PRODUCT (ID BIGINT PRIMARY KEY AUTO_INCREMENT, ORDERID BIGINT, PRODUCTID BIGINT)"))

        {
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(long clientId, List<Product> products) {
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("INSERT INTO ORDERS(CLIENTID) VALUES(?)", Statement.RETURN_GENERATED_KEYS))
        {
            statement.setLong(1, clientId);
            int rows = statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if(resultSet.next()) {
                    long tempOrderId = resultSet.getLong("ID");
                    try(PreparedStatement statementTwo = connection.prepareStatement("INSERT INTO ORDER_PRODUCT(ORDERID, PRODUCTID) VALUES(?, ?)")) {
                        for(Product product: products) {
                            statementTwo.setLong(1, tempOrderId);
                            statementTwo.setLong(2, product.getId());
                            statementTwo.executeUpdate();
                        }
                        statementTwo.close();
                    }
                }
                resultSet.close();
            }
            if(rows > 0) {
                return true;
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        boolean result = false;
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("DELETE FROM ORDERS WHERE ID=?;") )
        {
            statement.setLong(1, id);
            statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    long tempOrderId = resultSet.getLong("ID");
                    try (PreparedStatement statementTwo = connection.prepareStatement("DELETE FROM ORDER_PRODUCT WHERE ORDERID=?")) {
                        statement.setLong(1, tempOrderId);
                        int rows = statement.executeUpdate();
                        if (rows > 0) {
                            result = true;
                        }
                    }
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();

        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDERS;") )
        {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    try(PreparedStatement statementTwo = connection.prepareStatement("SELECT ORDER_PRODUCT.PRODUCTID, PRODUCT.NAME, PRODUCT.PRICE " +
                                                                                            "FROM ORDER_PRODUCT LEFT JOIN PRODUCT ON ORDER_PRODUCT.PRODUCTID = PRODUCT.ID WHERE ORDER_PRODUCT.ORDERID=? ;")){
                        long orderID = resultSet.getLong(1);
                        long clientID = resultSet.getLong(2);
                        List<Product> products = new ArrayList<>();

                        statementTwo.setLong(1, orderID);
                        try(ResultSet resultSetTwo = statementTwo.executeQuery()) {
                            while (resultSetTwo.next()) {
                                long productID = resultSetTwo.getLong(1);
                                String productName = resultSetTwo.getString(2);
                                double productPrice = resultSetTwo.getDouble(3);
                                products.add(new Product(productID, productName, productPrice));
                            }
                        }
                        orders.add(new Order(orderID, clientID, products));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public static OrderDao getInstance() {
        return ORDER_DAO;
    }
}
