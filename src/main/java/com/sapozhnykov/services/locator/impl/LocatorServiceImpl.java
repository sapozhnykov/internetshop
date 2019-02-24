package com.sapozhnykov.services.locator.impl;

import com.sapozhnykov.dao.auth.AuthClientDao;
import com.sapozhnykov.dao.auth.impl.AuthClientDaoImpl;
import com.sapozhnykov.dao.client.ClientDao;
import com.sapozhnykov.dao.client.impl.ClientDaoImpl;
import com.sapozhnykov.dao.order.OrderDao;
import com.sapozhnykov.dao.order.impl.OrderDaoImpl;
import com.sapozhnykov.dao.product.ProductDao;
import com.sapozhnykov.dao.product.impl.ProductDaoImpl;
import com.sapozhnykov.services.authorization.AuthClientService;
import com.sapozhnykov.services.authorization.impl.AuthClientServiceImpl;
import com.sapozhnykov.services.client.ClientService;
import com.sapozhnykov.services.client.impl.ClientServiceImpl;
import com.sapozhnykov.services.locator.LocatorService;
import com.sapozhnykov.services.order.OrderService;
import com.sapozhnykov.services.order.impl.OrderServiceImpl;
import com.sapozhnykov.services.product.ProductService;
import com.sapozhnykov.services.product.impl.ProductServiceImpl;
import com.sapozhnykov.validators.ValidationService;
import com.sapozhnykov.validators.impl.ValidationServiceImpl;
import com.sapozhnykov.view.menu.MenuView;
import com.sapozhnykov.view.menu.impl.*;

public class LocatorServiceImpl implements LocatorService {
    @Override
    public MenuView start() {
        ValidationService validationService = new ValidationServiceImpl();

        ClientDao clientDao = ClientDaoImpl.getInstance();
        ProductDao productDao = ProductDaoImpl.getInstance();
        OrderDao orderDao = OrderDaoImpl.getInstance();
        AuthClientDao authClientDao = AuthClientDaoImpl.getInstance();

        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
        ProductService productService = new ProductServiceImpl(productDao, validationService);
        OrderService orderService = new OrderServiceImpl(orderDao, validationService);
        AuthClientService authClientService = new AuthClientServiceImpl(authClientDao, validationService);

        MenuView catalogMenu = new CatalogMenuImpl(productService, orderService);
        MenuView clientListMenu = new ClientListMenuImpl(clientService, authClientService);
        MenuView productListMenu = new ProductListMenuImpl(productService);
        MenuView orderListMenu = new OrderListMenuImpl(orderService);
        MenuView clientMenu = new ClientMenuImpl(catalogMenu, clientService);
        MenuView authClientMenu = new AuthClientMenuImpl(clientMenu, authClientService, clientService);
        MenuView adminMenu = new AdminMenuImpl(clientListMenu, productListMenu, orderListMenu);

        return new MainMenuImpl(authClientMenu, adminMenu);
    }
}
