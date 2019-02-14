package com.sapozhnykov.services.order;

import com.sapozhnykov.domain.Order;
import com.sapozhnykov.domain.Product;

import java.util.ArrayList;

public interface OrderService {
    /**
     * Add new Order
     * @param products - list of Products
     * @return true if add is successful
     */
    boolean add(ArrayList<Product> products);
}
