package com.sapozhnykov.dao.product.impl;

import com.sapozhnykov.dao.product.ProductDao;
import com.sapozhnykov.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDBDaoImpl implements ProductDao {
    private static final ProductDBDaoImpl PRODUCT_DB_DAO = new ProductDBDaoImpl();

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "";

    private ProductDBDaoImpl() {
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement(
                      "CREATE TABLE IF NOT EXISTS PRODUCT (ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255), PRICE DOUBLE);"))
        {
            statement.executeUpdate();

//            statement.close();
//            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(String name, double price) {
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUCT(NAME, PRICE) VALUES(?, ?)"))
        {
            statement.setString(1, name);
            statement.setDouble(2, price);
            int rows = statement.executeUpdate();

//            statement.close();
//            connection.close();

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
              PreparedStatement statement = connection.prepareStatement("DELETE FROM PRODUCT WHERE ID=?;") )
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
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        long productID;
        String name;
        double price;

        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT;") )
        {
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    productID = resultSet.getLong("ID");
                    name = resultSet.getString("NAME");
                    price = resultSet.getDouble("PRICE");
                    products.add(new Product(productID, name, price));
                }
//                statement.close();
//                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getById(long id) {
        Product newProduct = null;

        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE ID=?;") )
        {
            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();

                long productID = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                double price = resultSet.getDouble("PRICE");

                newProduct = new Product(productID, name, price);

//                resultSet.close();
            }
//            statement.close();
//            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newProduct;
    }

    @Override
    public boolean modify(long productId, String name, double price) {
        try ( Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
              PreparedStatement statement = connection.prepareStatement("UPDATE PRODUCT SET NAME=? , PRICE=? WHERE ID=?;") )
        {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setLong(3, productId);
            int rows = statement.executeUpdate();
            if(rows > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ProductDao getInstance() {
        return PRODUCT_DB_DAO;
    }
}
