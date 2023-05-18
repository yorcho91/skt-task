package com.encora.skt.controller;

import com.encora.skt.bean.Result;
import com.encora.skt.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.encora.skt.configuration.QueueConfiguration.QUEUE_INSERT_PRODUCT;
import static com.encora.skt.controller.ProductInsertController.ATTRIBUTE_NAME_PRODUCT;
import static com.encora.skt.controller.ProductInsertController.ATTRIBUTE_NAME_RESULT;
import static com.encora.skt.controller.ProductInsertController.VIEW_NAME;
import static com.encora.skt.constant.RequestMapping.PRODUCT_INSERT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductInsertControllerTest
extends ProductControllerTest
{
    @Mock
    RedirectAttributes redirectAttributes;
    ProductInsertController test;

    @Before
    public void setup() {
        super.setup();
        test = new ProductInsertController(productService);
    }

    @Test
    public void get() {
        when(model.containsAttribute(ATTRIBUTE_NAME_PRODUCT))
            .thenReturn(false);

        assertThat(test.get(model))
            .isEqualTo(VIEW_NAME);

        verify(model, times(1))
            .containsAttribute(ATTRIBUTE_NAME_PRODUCT);

        verify(model, times(1))
            .addAttribute(anyString(), any(Product.class));
    }

    @Test
    public void post() {
        Product product = new Product(0, "sku", "name", "description");
        Result result = new Result().setOk(true).setMessage("Product saved");

        when(rabbitTemplate.convertSendAndReceive(QUEUE_INSERT_PRODUCT, product))
            .thenReturn(result);

        assertThat(test.post(redirectAttributes, product))
            .isNotNull()
            .extracting("url")
            .containsExactly(PRODUCT_INSERT);

        verify(rabbitTemplate, times(1))
            .convertSendAndReceive(QUEUE_INSERT_PRODUCT, product);

        verify(redirectAttributes, times(1))
            .addFlashAttribute(ATTRIBUTE_NAME_PRODUCT, product);

        verify(redirectAttributes, times(1))
            .addFlashAttribute(ATTRIBUTE_NAME_RESULT, result);
    }

}
