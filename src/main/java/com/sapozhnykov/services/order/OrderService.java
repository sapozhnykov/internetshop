package com.sapozhnykov.services.order;

import com.sapozhnykov.domain.Order;
import com.sapozhnykov.domain.Product;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    /**
     * Add new Order
     * @param products - List of selected products
     * @return true if add is successful
     */
    boolean add(long clientId, ArrayList<Product> products);

    /**
     * Return all Orders
     * @return all Orders
     */
    List<Order> getAll();
}
