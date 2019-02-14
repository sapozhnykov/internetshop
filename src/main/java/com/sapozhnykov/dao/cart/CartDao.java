package com.sapozhnykov.dao.cart;

import com.sapozhnykov.domain.Product;

import java.util.ArrayList;

public interface CartDao {
    /**
     * Add Product to the Cart storage
     * @param product - Product
     * @return true if add is successful
     */
    boolean add(Product product);

    /**
     * Delete Product from the Cart storage by ID
     * @param id - Product ID
     * @return true if delete is successful
     */
    boolean deleteById(String id);

    /**
     * Get all Products from the Cart storage
     * @return all Products
     */
    ArrayList<Product> getAll();

    /**
     * Get Product from the Cart storage by ID
     * @param id - Product ID
     * @return Product
     */
    Product getById(String id);
}
