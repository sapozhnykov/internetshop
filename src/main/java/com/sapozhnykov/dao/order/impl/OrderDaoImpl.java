package com.sapozhnykov.dao.order.impl;

import com.sapozhnykov.dao.order.OrderDao;
import com.sapozhnykov.domain.Order;
import com.sapozhnykov.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static OrderDao orderDao = new OrderDaoImpl();

    private List<Order> orders = new ArrayList<>();
    private static long tempID = 1;

    private OrderDaoImpl() { }

    @Override
    public boolean add(long clientId, ArrayList<Product> products) {
        Order order = new Order(tempID++, clientId, products);
        return orders.add(order);
    }

    @Override
    public boolean deleteById(long id) {
        Order tempOrder;

        tempOrder = getById(id);
        return orders.remove(tempOrder);
    }

    @Override
    public Order getById(long id) {
        for(Order order: orders) {
            if(order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        return this.orders;
    }

    public static OrderDao getInstance() {
        return orderDao;
    }
}
