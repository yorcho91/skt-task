package com.encora.skt.service;

import com.encora.skt.bean.Result;
import com.encora.skt.entity.Product;
import com.encora.skt.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.encora.skt.configuration.QueueConfiguration.QUEUE_LIST_PRODUCTS;
import static com.encora.skt.configuration.QueueConfiguration.QUEUE_INSERT_PRODUCT;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductListenerTest {

    @Value("${product.saved}")
    String messageSaved;

    @Value("${product.error}")
    String messageError;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ProductRepository repository;

    Product product = new Product(
        0,
        "ProductListener",
        "ProductListenerName",
        "ProductListenerDescription");

    @Before
    public void setup() {
        assertThat(rabbitTemplate.convertSendAndReceive(QUEUE_INSERT_PRODUCT, product))
            .isInstanceOf(Result.class)
            .extracting("ok", "message")
            .contains(true, messageSaved);
    }

    @After
    public void after() {
        assertThat(repository.deleteBySku(product.getSku()))
            .isEqualTo(1);
    }

    @Test
    public void listProducts() {
        assertThat(rabbitTemplate.convertSendAndReceive(QUEUE_LIST_PRODUCTS, QUEUE_LIST_PRODUCTS))
            .asList()
            .extracting("sku")
            .contains(product.getSku());
    }

    @Test
    public void insertProduct() {
        assertThat(rabbitTemplate.convertSendAndReceive(QUEUE_INSERT_PRODUCT, product))
            .isInstanceOf(Result.class)
            .extracting("ok", "message")
            .contains(false, messageError);
    }

}
