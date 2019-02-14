package com.sapozhnykov.dao.order;

import com.sapozhnykov.domain.Order;

import java.util.ArrayList;

public interface OrderDao {
    /**
     * Add Order to the storage
     * @param order - Order
     * @return true if add is successful
     */
    boolean add(Order order);

    /**
     * Get all Orders from the storage
     * @return all Orders
     */
    ArrayList<Order> getAll();
}
