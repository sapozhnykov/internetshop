package com.sapozhnykov.services.product.impl;

import com.sapozhnykov.dao.product.ProductDao;
import com.sapozhnykov.dao.product.impl.ProductDBDaoImpl;
import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.locator.ServiceLocator;
import com.sapozhnykov.services.product.ProductService;
import com.sapozhnykov.validators.ValidationService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = ProductDBDaoImpl.getInstance();
    private ValidationService validationService = ServiceLocator.getServiceByName("ValidationService");

    @Override
    public boolean addProduct(String name, String price) {
        double tempPrice;
        try {
            tempPrice = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return false;
        }
//        Product client = new Product(name, tempPrice);
//        boolean result = productDao.add(client);
        boolean result = productDao.add(name, tempPrice);
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
    public List<Product> getAllProduct() {
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

    public boolean modifyProduct(long productId, String name, String price){
        double tempPrice;
        try {
            tempPrice = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return false;
        }
        return productDao.modify(productId, name, tempPrice);
    }

    @Override
    public String getName() {
        return "ProductService";
    }
}
