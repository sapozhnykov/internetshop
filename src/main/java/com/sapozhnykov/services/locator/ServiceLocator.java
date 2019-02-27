package com.sapozhnykov.services.locator;

import com.sapozhnykov.services.authorization.AuthClientService;
import com.sapozhnykov.services.authorization.impl.AuthClientServiceImpl;
import com.sapozhnykov.services.client.ClientService;
import com.sapozhnykov.services.client.impl.ClientServiceImpl;
import com.sapozhnykov.services.order.OrderService;
import com.sapozhnykov.services.order.impl.OrderServiceImpl;
import com.sapozhnykov.services.product.ProductService;
import com.sapozhnykov.services.product.impl.ProductServiceImpl;
import com.sapozhnykov.validators.ValidationService;
import com.sapozhnykov.validators.impl.ValidationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static Map locator = new HashMap();
    static{
        ValidationService validationService = new ValidationServiceImpl();
        locator.put("ValidationService", validationService);

        ClientService clientService = new ClientServiceImpl();
        locator.put("ClientService", clientService);

        ProductService productService = new ProductServiceImpl();
        locator.put("ProductService", productService);

        OrderService orderService = new OrderServiceImpl();
        locator.put("OrderService", orderService);

        AuthClientService authClientService = new AuthClientServiceImpl();
        locator.put("AuthClientService", authClientService);
    }

    public static <T> T getServiceByName(String serviceName) {
        return (T)locator.get(serviceName);
    }
}
