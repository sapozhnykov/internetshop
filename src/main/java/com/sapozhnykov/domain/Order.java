package com.sapozhnykov.domain;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static long id = 1;
    private long idClient;
    private BigDecimal finalCost;
    private ArrayList<Product> products;

    public Order(BigDecimal finalCost, ArrayList<Product> products) {
        id = id++;
        this.finalCost = finalCost;
        this.products = products;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public BigDecimal getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(BigDecimal finalCost) {
        this.finalCost = finalCost;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idCliet=" + idClient +
                ", finalCost=" + finalCost +
                ", products=" + products +
                '}';
    }
}
