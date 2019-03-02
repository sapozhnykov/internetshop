package com.sapozhnykov.dao.product.impl;

import com.sapozhnykov.dao.product.ProductDao;
import com.sapozhnykov.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static final ProductDao PRODUCT_DAO = new ProductDaoImpl();

    private List<Product> products = new ArrayList<>();

    private ProductDaoImpl() {

    }

    @Override
    public boolean add(Product product) {
        return products.add(product);
    }

    @Override
    public boolean deleteById(long id) {
        Product tempProduct;

        tempProduct = getById(id);
        return products.remove(tempProduct);
    }

    @Override
    public List<Product> getAll() {
        return this.products;
    }

    @Override
    public Product getById(long id) {
        for(Product product: products) {
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean modify(long productId, String name, double price) {
        boolean result = false;
        for(Product product: products) {
            if(product.getId() == productId) {
                product.setName(name);
                product.setPrice(price);
                result = true;
            }
        }
        return result;
    }

    public static ProductDao getInstance() {
        return PRODUCT_DAO;
    }
}
