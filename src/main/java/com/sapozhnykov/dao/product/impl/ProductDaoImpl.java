package com.sapozhnykov.dao.product.impl;

import com.sapozhnykov.dao.product.ProductDao;
import com.sapozhnykov.domain.Product;

import java.util.ArrayList;

public class ProductDaoImpl implements ProductDao {
    private ArrayList<Product> products = new ArrayList<>();

    public ProductDaoImpl() {
        products.add(new Product("IPhone 5" , 320));
        products.add(new Product("IPhone 6" , 340));
        products.add(new Product("IPhone 8 plus" , 430));
        products.add(new Product("IPhone 10" , 840));
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
    public ArrayList<Product> getAll() {
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
}
