package com.encora.skt.service;

import com.encora.skt.bean.Result;
import com.encora.skt.entity.Product;
import java.util.List;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.encora.skt.configuration.QueueConfiguration.QUEUE_LIST_PRODUCTS;
import static com.encora.skt.configuration.QueueConfiguration.QUEUE_INSERT_PRODUCT;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final RabbitTemplate rabbitTemplate;
    @Value("${service.down}")
    private final String serviceDown;

    public List<Product> list() {
        return convertSendAndReceiveOrThrow(
            () -> rabbitTemplate.convertSendAndReceive(
                QUEUE_LIST_PRODUCTS,
                QUEUE_LIST_PRODUCTS));
    }

    public Result insert(Product product) {
        Result result = convertSendAndReceiveOrThrow(
            () -> rabbitTemplate.convertSendAndReceive(
                QUEUE_INSERT_PRODUCT,
                product));
        return result;
    }

    private <T> T convertSendAndReceiveOrThrow(Supplier<Object> supplier) {
        Object result = supplier.get();
        if (result == null) {
            throw new RuntimeException(serviceDown);
        }
        return (T)result;
    }

}
