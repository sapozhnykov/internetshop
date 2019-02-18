package com.sapozhnykov.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private long clientId;
    private List<Product> products;

    public Order(long id, long clientId, ArrayList<Product> products) {
        this.id = id;
        this.clientId = clientId;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public long getClientId() {
        return clientId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", products=" + products +
                '}';
    }
}
