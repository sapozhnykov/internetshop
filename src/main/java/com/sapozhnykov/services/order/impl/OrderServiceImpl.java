package com.sapozhnykov.services.order.impl;

import com.sapozhnykov.dao.order.OrderDao;
import com.sapozhnykov.dao.order.impl.OrderDaoImpl;
import com.sapozhnykov.domain.Order;
import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.order.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public boolean add(ArrayList<Product> products) {
        BigDecimal finalCost = new BigDecimal(0);
        for(Product product: products) {
            finalCost = finalCost.add(product.getPrice());
        }
        Order order = new Order(finalCost, products);
        return orderDao.add(order);
    }
}
