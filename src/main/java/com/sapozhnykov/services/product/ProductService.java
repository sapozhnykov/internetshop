package com.sapozhnykov.services.product;

import com.sapozhnykov.domain.Product;
import java.util.List;

public interface ProductService {
    /**
     * Add new Product
     * @param name - name of Product
     * @param price - price of Product
     * @return true if add is successful
     */
    boolean addProduct(String name, String price);

    /**
     * Delete Product by ID
     * @param id - ID of Product
     * @return true if delete is successful
     */
    boolean deleteById(String id);

    /**
     * Return all Products
     * @return all Products
     */
    List<Product> getAllProduct();

    /**
     * Return Product by ID
     * @param id - ID of Product
     * @return Product
     */
    Product getById(String id);

    boolean modifyProduct(long productId, String name, String price);

    public String getName();
}
