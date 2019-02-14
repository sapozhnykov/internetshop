package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.cart.CartService;
import com.sapozhnykov.services.cart.impl.CartServiceImpl;
import com.sapozhnykov.services.order.OrderService;
import com.sapozhnykov.services.order.impl.OrderServiceImpl;
import com.sapozhnykov.services.product.ProductService;
import com.sapozhnykov.services.product.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.ArrayList;

public class CatalogMenuImpl extends MenuImpl{
    private final CartService cartService = new CartServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @Override
    protected void showMenu() {
        System.out.println("====== CATALOG MENU ======");
        showAllProducts();
        System.out.println("______    Options   _______");
        System.out.println("1. Add product to the cart");
        System.out.println("2. Show the cart");
        System.out.println("3. Checkout!");
        System.out.println("r. Return");
        System.out.println("e. Exit");
    }

    @Override
    protected void makeChoice() throws IOException {
        while (super.isRunningMenu) {
            showMenu();
            switch (super.inputParameter("number of menu")) {
                case "1":
                    addToCart();
                    break;
                case "2":
                    showTheCart();
                    break;
                case "3":
//                    System.out.println("CHECKOUT");
                    doCkeckout();
                    break;
                case "r":
                    super.returnBack();
                    break;
                case "e":
                    super.exit();
                    break;
                default:
                    super.showErrorMessage();
            }
        }
    }

    private void showAllProducts() {
        productService.getAllProduct().forEach(System.out::println);
    }

    private void doCkeckout() {
        boolean result;

        ArrayList<Product> cartProducts = cartService.getAll();
        result = orderService.add(cartProducts);

        if(result) {
            System.out.println("Thank you for your checkout!");
        } else {
            System.out.println("Error. checkout can't be done!");
        }
        System.out.println();
    }

    private void showTheCart() {
        System.out.println("===== Your card =====");
        cartService.getAll().forEach(System.out::println);
        System.out.println();
    }

    private void addToCart() throws IOException {
        String tempId = "";
        boolean result = false;
        Product product;

        tempId = super.inputParameter("product ID");
        product = productService.getById(tempId);
        if(product != null){
            result = cartService.addToCart(product);
        }

        if(result) {
            System.out.println("product added to cart!");
        } else {
            System.out.println("Error. product don't added to cart!");
        }
        System.out.println();
    }
}
