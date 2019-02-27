package com.sapozhnykov.services.order.impl;

import com.sapozhnykov.dao.order.OrderDao;
import com.sapozhnykov.dao.order.impl.OrderDaoImpl;
import com.sapozhnykov.domain.Order;
import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.locator.ServiceLocator;
import com.sapozhnykov.services.order.OrderService;
import com.sapozhnykov.validators.ValidationService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = OrderDaoImpl.getInstance();
    private ValidationService validationService = ServiceLocator.getServiceByName("ValidationService");

    @Override
    public boolean add(long clientId, List<Product> products) {
        return orderDao.add(clientId, products);
    }

    @Override
    public boolean deleteById(String id) {
        long tempId;

        try {
            tempId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return false;
        }
        return orderDao.deleteById(tempId);
    }

    @Override
    public List<Order> getAll() { return orderDao.getAll(); }

    @Override
    public String getName() {
        return "OrderService";
    }
}
