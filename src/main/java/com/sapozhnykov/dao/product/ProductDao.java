package com.sapozhnykov.dao.product;

import com.sapozhnykov.domain.Product;

import java.util.List;

public interface ProductDao {
    /**
     * Add Product to the storage
     * @param name - name of product
     * @param price - price of product
     * @return true if add is successful
     */
    boolean add(String name, double price);

    /**
     * Delete Product from the storage by ID
     * @param id - Product ID
     * @return true if delete is successful
     */
    boolean deleteById(long id);

    /**
     * Get all Products from the storage
     * @return all Products from the storage
     */
    List<Product> getAll();

    /**
     * Get Product from the storage by ID
     * @param id - Product ID
     * @return Product
     */
    Product getById(long id);

    boolean modify(long productId, String name, double price);
}
