package com.sapozhnykov.dao.order.impl;

import com.sapozhnykov.dao.order.OrderDao;
import com.sapozhnykov.domain.Order;
import com.sapozhnykov.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private List<Order> orders = new ArrayList<>();
    private static long tempID = 1;

    @Override
    public boolean add(long clientId, ArrayList<Product> products) {
        Order order = new Order(tempID++, clientId, products);
        return orders.add(order);
    }

    @Override
    public List<Order> getAll() {
        return this.orders;
    }
}
