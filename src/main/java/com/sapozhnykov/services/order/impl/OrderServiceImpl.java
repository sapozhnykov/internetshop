package com.sapozhnykov.services.order.impl;

import com.sapozhnykov.dao.order.OrderDao;
import com.sapozhnykov.dao.order.impl.OrderDaoImpl;
import com.sapozhnykov.domain.Order;
import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.order.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public boolean add(long clientId, ArrayList<Product> products) {
//        Order order = new Order(clientId, products);
        return orderDao.add(clientId, products);
    }

    @Override
    public List<Order> getAll() { return orderDao.getAll(); }
}
