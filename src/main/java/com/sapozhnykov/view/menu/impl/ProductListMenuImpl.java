package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.services.product.ProductService;
import com.sapozhnykov.services.product.impl.ProductServiceImpl;

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
    protected boolean makeChoice(){
        boolean isWorkContinue = true;
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

    private void addProduct(){
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

    private void deleteProduct(){
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
