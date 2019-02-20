package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.authorization.impl.AuthClientServiceImpl;
import com.sapozhnykov.services.order.OrderService;
import com.sapozhnykov.services.product.ProductService;

import java.util.ArrayList;

public class CatalogMenuImpl extends MenuImpl{
    private final ProductService productService;
    private final OrderService orderService;

    public CatalogMenuImpl(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    protected void showMenu() {
        System.out.println("====== CATALOG MENU ======");
        showAllProducts();
        System.out.println("______    Options   _______");
        System.out.println("1. Add product to the order");
        System.out.println("2. List orders");
        System.out.println("r. Return");
        System.out.println("e. Exit");
    }

    @Override
    protected boolean makeChoice() {
        boolean isWorkContinue = true;
        while (super.isRunningMenu) {
            showMenu();
            switch (super.inputParameter("number of menu")) {
                case "1":
                    addToOrder();
                    break;
                case "2":
                    listOrders();
                    break;
                case "r":
                    super.returnBack();
                    break;
                case "e":
                    isWorkContinue = false;
                    super.returnBack();
                    break;
                default:
                    super.showErrorMessage();
            }
            if(!isWorkContinue) {
                super.returnBack();
            }
        }
        return isWorkContinue;
    }

    private void showAllProducts() {
        productService.getAllProduct().forEach(System.out::println);
    }

    private void addToOrder() {
        String tempId = "";
        boolean result = false;
        Product product;
        String choice;
        ArrayList<Product> selectedProducts = new ArrayList<>();

        do{
            tempId = super.inputParameter("product ID");
            product = productService.getById(tempId);
            if(product != null) {
                selectedProducts.add(product);
            } else {
                System.out.println("Product not found");
            }
            System.out.println("Would you like to add one more? (y/n)");
            choice = super.inputParameter("your choice");
        } while (choice.equals("y"));

        if(!selectedProducts.isEmpty()){
            result = orderService.add(AuthClientServiceImpl.getCurrentUserId(), selectedProducts);
        }

        if(result) {
            System.out.println("Products added to the order!");
        } else {
            System.out.println("Error. Products didn't add to the order!");
        }
        System.out.println();
    }

    private void listOrders() {
        System.out.println("==== Your Orders ====");
        orderService.getAll().forEach(System.out::println);
    }
}
