package com.sapozhnykov.services.cart;

import com.sapozhnykov.domain.Product;

import java.util.ArrayList;

public interface CartService {
    /**
     * Add Product to the Cart
     * @param product - Product
     * @return true if add is successful
     */
    boolean addToCart(Product product);
    ArrayList<Product> getAll();
}
