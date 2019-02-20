package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.domain.Product;
import com.sapozhnykov.services.product.ProductService;

public class ProductListMenuImpl extends MenuImpl {
    private final ProductService productService;

    public ProductListMenuImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void showMenu() {
        System.out.println("====== PRODUCT LIST ======");
        showAllProducts();
        System.out.println("______   Options   ______");
        System.out.println("1. Add new product");
        System.out.println("2. Delete product");
        System.out.println("3. Modify product");
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
                case "3":
                    modifyProduct();
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

    private void modifyProduct() {
        String tempId = "";
        long productId;
        String name;
        String price;

        boolean result;

        tempId = super.inputParameter("product ID");
        Product product = productService.getById(tempId);
        if(product == null) {
            System.out.println("Product not found");
            return;
        }

        System.out.println("====== Product information: ======");
        System.out.println(product);

        productId = product.getId();
        name = super.inputParameter("name");
        price = super.inputParameter("price");

        result = productService.modifyProduct(productId, name, price);

        if(result) {
            System.out.println("Product modified!");
        } else {
            System.out.println("Error. product not modified!");
        }
        System.out.println();
    }
}
