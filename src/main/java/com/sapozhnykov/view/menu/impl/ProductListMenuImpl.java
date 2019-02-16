package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.services.product.ProductService;
import com.sapozhnykov.services.product.impl.ProductServiceImpl;

import java.io.IOException;

public class ProductListMenuImpl extends MenuImpl {
    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void showMenu() {
        System.out.println("====== PRODUCT LIST ======");
        showAllProducts();
        System.out.println("______   Options   ______");
        System.out.println("1. Add new product");
        System.out.println("2. Delete product");
        System.out.println("r. Return");
        System.out.println("e. Exit");
    }

    @Override
    protected void makeChoice() throws IOException {
        while (super.isRunningMenu) {
            showMenu();
            switch (super.inputParameter("number of menu")) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    deleteProduct();
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

    private void addProduct() throws IOException {
        String name = "";
        String price = "";

        boolean result;

        name = super.inputParameter("name");
        price = super.inputParameter("price");

        result = productService.addProduct(name, price);
        if(result) {
            System.out.println("product saved!");
        } else {
            System.out.println("Error. product don't saved!");
        }
        System.out.println();
    }

    private void deleteProduct() throws IOException {
        String tempId = "";
        boolean result;

        tempId = super.inputParameter("client ID");
        result = productService.deleteById(tempId);
        if(result) {
            System.out.println("product deleted!");
        } else {
            System.out.println("Error. product don't deleted!");
        }
        System.out.println();
    }
}