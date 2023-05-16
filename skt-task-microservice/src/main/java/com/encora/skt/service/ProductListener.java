package com.encora.skt.service;

import com.encora.skt.bean.Result;
import com.encora.skt.entity.Product;
import com.encora.skt.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.encora.skt.configuration.QueueConfiguration.QUEUE_LIST_PRODUCTS;
import static com.encora.skt.configuration.QueueConfiguration.QUEUE_INSERT_PRODUCT;

@RequiredArgsConstructor
@Service
public class ProductListener {

    @Value("${product.saved}")
    private final String messageSaved;

    @Value("${product.error}")
    private final String messageError;

    private final ProductRepository repository;

    @RabbitListener(queues = QUEUE_LIST_PRODUCTS)
    public List<Product> listProducts() {
        return repository.listProducts();
    }

    @RabbitListener(queues = QUEUE_INSERT_PRODUCT)
    public Result insertProduct(Product product) {
        try {
            repository.insertProduct(
                product.getSku(),
                product.getName(),
                product.getDescription()
            );
        } catch (Exception ex) {
            return new Result()
                .setMessage(messageError)
                .setDetailedMessage(ex.getMessage());
        }
        return new Result()
            .setOk(true)
            .setMessage(messageSaved);
    }

}
