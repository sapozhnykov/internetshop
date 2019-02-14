package com.sapozhnykov.dao.order.impl;

import com.sapozhnykov.dao.order.OrderDao;
import com.sapozhnykov.domain.Order;

import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    private ArrayList<Order> orders = new ArrayList<>();

    @Override
    public boolean add(Order order) {
        return orders.add(order);
    }

    @Override
    public ArrayList<Order> getAll() {
        return this.orders;
    }
}
