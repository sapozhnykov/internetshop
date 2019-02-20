package com.sapozhnykov.dao.order;

import com.sapozhnykov.domain.Order;
import com.sapozhnykov.domain.Product;

import java.util.ArrayList;
import java.util.List;

public interface OrderDao {
    /**
     * Add order to the storage
     * @param clientId - ID of client
     * @param products - products
     * @return
     */
    boolean add(long clientId, ArrayList<Product> products);

    /**
     * Delete Order from the storage by ID
     * @param id - Order ID
     * @return true if delete is successful
     */
    boolean deleteById(long id);

    /**
     * Get Order from the storage by ID
     * @param id - Order ID
     * @return Order
     */
    Order getById(long id);

    /**
     * Get all Orders from the storage
     * @return all Orders
     */
    List<Order> getAll();
}
