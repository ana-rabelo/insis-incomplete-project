package com.isep.acme.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;

@Component
public class ProductProducer {
    
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProductProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCreatedProductMessage(Product product) {
        rabbitTemplate.convertAndSend("ms.products.product-created", "", product);
    }

    public void sendUpdatedProductMessage(Product product) {
        rabbitTemplate.convertAndSend("ms.products.product-updated", "", product);
    }

    public void sendDeletedProductMessage(String productSku) {
        rabbitTemplate.convertAndSend("ms.products.product-deleted", "", productSku);
    }
}
