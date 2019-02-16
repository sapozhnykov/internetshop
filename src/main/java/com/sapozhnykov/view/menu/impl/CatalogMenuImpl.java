package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.order.OrderService;
import com.sapozhnykov.services.order.impl.OrderServiceImpl;
import com.sapozhnykov.services.product.ProductService;
import com.sapozhnykov.services.product.impl.ProductServiceImpl;

import java.util.ArrayList;

public class CatalogMenuImpl extends MenuImpl{
    private final ProductService productService = new ProductServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private ArrayList<Product> selectedProducts = new ArrayList<>();

    @Override
    protected void showMenu() {
        System.out.println("====== CATALOG MENU ======");
        showAllProducts();
        System.out.println("______    Options   _______");
        System.out.println("1. Add product to the order");
        System.out.println("2. List orders");
        System.out.println("r. Return");
    }

    @Override
    protected void makeChoice() {
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
                default:
                    super.showErrorMessage();
            }
        }
    }

    private void showAllProducts() {
        productService.getAllProduct().forEach(System.out::println);
    }

    private void addToOrder() {
        String tempId = "";
        boolean result = false;
        Product product;
        String choice;

        do{
            tempId = super.inputParameter("product ID");
            product = productService.getById(tempId);
            if(product != null) {
                selectedProducts.add(product);
            } else {
                System.out.println("Product not found");
            }
            System.out.println("Would you like to add one more? (y/n)");
            choice = super.inputParameter("Make a choice");
        } while (choice.equals("y"));

        if(!selectedProducts.isEmpty()){
            result = orderService.add(selectedProducts);
        }

        if(result) {
            System.out.println("Products added to the order!");
        } else {
            System.out.println("Error. Products didn't add to the order!");
        }
        System.out.println();
    }

    private void listOrders() {
        orderService.getAll().forEach(System.out::println);
    }
}
