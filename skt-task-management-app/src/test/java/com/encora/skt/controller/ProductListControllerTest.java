package com.encora.skt.controller;

import com.encora.skt.entity.Product;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.encora.skt.configuration.QueueConfiguration.QUEUE_LIST_PRODUCTS;
import static com.encora.skt.controller.ProductListController.ATTRIBUTE_NAME_PRODUCTS;
import static com.encora.skt.controller.ProductListController.VIEW_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductListControllerTest
extends ProductControllerTest
{
    ProductListController test;

    @Before
    public void setup() {
        super.setup();
        test = new ProductListController(productService);
    }

    @Test
    public void get() {
        List<Product> products = Arrays.asList(new Product(0, "sku", "name", "description"));
        when(rabbitTemplate.convertSendAndReceive(
            QUEUE_LIST_PRODUCTS,
            QUEUE_LIST_PRODUCTS))
            .thenReturn(products);

        assertThat(test.get(model))
            .isEqualTo(VIEW_NAME);

        verify(rabbitTemplate, times(1))
            .convertSendAndReceive(QUEUE_LIST_PRODUCTS, QUEUE_LIST_PRODUCTS);

        verify(model, times(1))
            .addAttribute(ATTRIBUTE_NAME_PRODUCTS, products);
    }

    @Test
    public void getWithError() {
        when(rabbitTemplate.convertSendAndReceive(
            QUEUE_LIST_PRODUCTS,
            QUEUE_LIST_PRODUCTS))
            .thenReturn(null);

        assertThatThrownBy(() -> test.get(model))
            .hasMessage(SERVER_DOWN);

        verify(rabbitTemplate, times(1))
            .convertSendAndReceive(QUEUE_LIST_PRODUCTS, QUEUE_LIST_PRODUCTS);
    }

}
