package com.sapozhnykov.services.product.impl;

import com.sapozhnykov.dao.product.ProductDao;
import com.sapozhnykov.dao.product.impl.ProductDaoImpl;
import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.product.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean addProduct(String name, String price) {
        BigDecimal tempPrice;
        try {
            tempPrice = new BigDecimal(price);
        } catch (NumberFormatException e) {
            return false;
        }
        Product client = new Product(name, tempPrice);
        boolean result = productDao.add(client);
        return result;
    }

    @Override
    public boolean deleteById(String id) {
        return productDao.deleteById(id);
    }

    @Override
    public ArrayList<Product> getAllProduct() { return productDao.getAll(); }

    @Override
    public Product getById(String id) {
        return productDao.getById(id);
    }
}
