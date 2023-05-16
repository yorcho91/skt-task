package com.encora.skt.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

    public static final String QUEUE_INSERT_PRODUCT = "insert_product";

    public static final String QUEUE_LIST_PRODUCTS = "list_products";

    @Bean
    public Queue listProducts() {
        return new Queue(QUEUE_LIST_PRODUCTS);
    }

    @Bean
    public Queue insertProduct() {
        return new Queue(QUEUE_INSERT_PRODUCT);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
