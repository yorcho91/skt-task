package com.encora.skt.configuration;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static com.encora.skt.configuration.QueueConfiguration.QUEUE_LIST_PRODUCTS;
import static com.encora.skt.configuration.QueueConfiguration.QUEUE_INSERT_PRODUCT;

public class QueueConfigurationTest {

    QueueConfiguration test = new QueueConfiguration();

    @Test
    public void listProducts() {
        assertThat(test.listProducts())
            .isNotNull()
            .extracting("name")
            .contains(QUEUE_LIST_PRODUCTS);
    }

    @Test
    public void insertProduct() {
        assertThat(test.insertProduct())
            .isNotNull()
            .extracting("name")
            .contains(QUEUE_INSERT_PRODUCT);
    }

    @Test
    public void jackson2JsonMessageConverter() {
        assertThat(test.jackson2JsonMessageConverter())
            .isNotNull();
    }

}
