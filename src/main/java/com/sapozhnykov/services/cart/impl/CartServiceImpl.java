package com.sapozhnykov.services.cart.impl;

import com.sapozhnykov.dao.cart.CartDao;
import com.sapozhnykov.dao.cart.impl.CartDaoImpl;
import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.cart.CartService;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();

    @Override
    public boolean addToCart(Product product) {
        return cartDao.add(product);
    }

    @Override
    public ArrayList<Product> getAll() { return cartDao.getAll(); }
}
