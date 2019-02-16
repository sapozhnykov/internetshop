package com.sapozhnykov.services.product.impl;

import com.sapozhnykov.dao.product.ProductDao;
import com.sapozhnykov.dao.product.impl.ProductDaoImpl;
import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.product.ProductService;

import java.util.ArrayList;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean addProduct(String name, String price) {
        double tempPrice;
        try {
            tempPrice = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return false;
        }
        Product client = new Product(name, tempPrice);
        boolean result = productDao.add(client);
        return result;
    }

    @Override
    public boolean deleteById(String id) {
        long tempId;

        try {
            tempId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return false;
        }
        return productDao.deleteById(tempId);
    }

    @Override
    public ArrayList<Product> getAllProduct() {
        return productDao.getAll();
    }

    @Override
    public Product getById(String id) {
        long tempId;

        try {
            tempId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }

        return productDao.getById(tempId);
    }
}
