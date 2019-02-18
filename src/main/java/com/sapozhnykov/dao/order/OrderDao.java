package com.sapozhnykov.dao.order;

import com.sapozhnykov.domain.Order;
import com.sapozhnykov.domain.Product;

import java.util.ArrayList;
import java.util.List;

public interface OrderDao {
    /**
     * Add Order to the storage
     * @param order - Order
     * @return true if add is successful
     */
    boolean add(long clientId, ArrayList<Product> products);

    /**
     * Get all Orders from the storage
     * @return all Orders
     */
    List<Order> getAll();
}
