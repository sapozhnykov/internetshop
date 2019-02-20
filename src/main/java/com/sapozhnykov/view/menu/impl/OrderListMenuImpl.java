package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.services.order.OrderService;

public class OrderListMenuImpl extends MenuImpl {
    private final OrderService orderService;

    public OrderListMenuImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected void showMenu() {
        System.out.println("====== ORDERS LIST ======");
        listOrders();
        System.out.println("______   Options   ______");
        System.out.println("1. Remove order");
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
                    removeOrder();
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

    private void listOrders() {
        System.out.println("==== All Orders ====");
        orderService.getAll().forEach(System.out::println);
    }

    private void removeOrder() {
        String tempId = "";
        boolean result;

        tempId = super.inputParameter("order ID");
        result = orderService.deleteById(tempId);
        if(result) {
            System.out.println("Order was deleted!");
        } else {
            System.out.println("Error. Order wasn't deleted!");
        }
        System.out.println();
    }


}
