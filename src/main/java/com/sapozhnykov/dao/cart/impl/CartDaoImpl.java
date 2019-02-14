package com.sapozhnykov.dao.cart.impl;

import com.sapozhnykov.dao.cart.CartDao;
import com.sapozhnykov.domain.Product;

import java.util.ArrayList;

public class CartDaoImpl implements CartDao {
    private ArrayList<Product> cartProducts = new ArrayList<>();

    @Override
    public boolean add(Product product) {
        return cartProducts.add(product);
    }

    @Override
    public boolean deleteById(String id) {
        Product tempProduct;

        tempProduct = getById(id);
        return cartProducts.remove(tempProduct);
    }

    @Override
    public ArrayList<Product> getAll() {
        return this.cartProducts;
    }

    @Override
    public Product getById(String id) {
        long tempId;

        try {
            tempId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }

        for(Product product: cartProducts) {
            if(product.getId() == tempId) {
                return product;
            }
        }
        return null;
    }
}
