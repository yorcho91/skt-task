package com.encora.skt.controller;

import com.encora.skt.service.ProductService;
import org.junit.Before;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.ui.Model;

public class ProductControllerTest {

    static final String SERVER_DOWN = "Server is down";
    @Mock
    RabbitTemplate rabbitTemplate;
    @Mock
    Model model;
    ProductService productService;

    @Before
    public void setup() {
        productService = new ProductService(
            rabbitTemplate,
            SERVER_DOWN);
    }

}
