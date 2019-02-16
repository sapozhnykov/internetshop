package com.sapozhnykov.services.order.impl;

import com.sapozhnykov.dao.order.OrderDao;
import com.sapozhnykov.dao.order.impl.OrderDaoImpl;
import com.sapozhnykov.domain.Order;
import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.order.OrderService;

import java.util.ArrayList;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public boolean add(ArrayList<Product> products) {
        /* We don't have a Session to get client ID.
        *  So for current program version we have only one client
        *  who can bye something.
        * */
        long clientId = 1;

        Order order = new Order(clientId, products);
        return orderDao.add(order);
    }

    @Override
    public ArrayList<Order> getAll() { return orderDao.getAll(); }
}
