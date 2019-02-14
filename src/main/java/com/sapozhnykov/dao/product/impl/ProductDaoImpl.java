package com.sapozhnykov.dao.product.impl;

import com.sapozhnykov.dao.product.ProductDao;
import com.sapozhnykov.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductDaoImpl implements ProductDao {
    private ArrayList<Product> products = new ArrayList<>();

    public ProductDaoImpl() {
        products.add(new Product("IPhone 5" , new BigDecimal(320)));
        products.add(new Product("IPhone 6" , new BigDecimal(340)));
        products.add(new Product("IPhone 8 plus" , new BigDecimal(430)));
        products.add(new Product("IPhone 10" , new BigDecimal(840)));
    }

    @Override
    public boolean add(Product product) {
        boolean result = products.add(product);
        System.out.println("Saving... Please, wait...");
        return result;
    }

    @Override
    public boolean deleteById(String id) {
        Product tempProduct;

        tempProduct = getById(id);
        return products.remove(tempProduct);
    }

    @Override
    public ArrayList<Product> getAll() {
        return this.products;
    }

    @Override
    public Product getById(String id) {
        long tempId;

        try {
            tempId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }

        for(Product product: products) {
            if(product.getId() == tempId) {
                return product;
            }
        }
        return null;
    }
}
