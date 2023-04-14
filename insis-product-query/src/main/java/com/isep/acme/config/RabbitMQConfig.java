package com.isep.acme.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.application.instance_id}")
    private String instanceID;

    @Bean
    public Queue productCreatedQueue() {
        return new Queue("products.create-product." + applicationName + "." + instanceID);
    }

    @Bean
    public Queue productUpdatedQueue() {
        return new Queue("products.update-product." + applicationName + "." + instanceID);
    }

    @Bean
    public Queue productDeletedQueue() {
        return new Queue("products.delete-product." + applicationName + "." + instanceID);
    }

    @Bean
    public FanoutExchange productCreatedExchange() {
        return new FanoutExchange("ms.products.product-created");
    }

    @Bean
    public FanoutExchange productUpdatedExchange() {
        return new FanoutExchange("ms.products.product-updated");
    }

    @Bean
    public FanoutExchange productDeletedExchange() {
        return new FanoutExchange("ms.products.product-deleted");
    }

    @Bean
    public Binding productCreatedBinding(Queue productCreatedQueue, FanoutExchange productCreatedExchange) {
        return BindingBuilder.bind(productCreatedQueue).to(productCreatedExchange);
    }

    @Bean
    public Binding productUpdatedBinding(Queue productUpdatedQueue, FanoutExchange productUpdatedExchange) {
        return BindingBuilder.bind(productUpdatedQueue).to(productUpdatedExchange);
    }

    @Bean
    public Binding productDeletedBinding(Queue productDeletedQueue, FanoutExchange productDeletedExchange) {
        return BindingBuilder.bind(productDeletedQueue).to(productDeletedExchange);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationListener(RabbitAdmin rabbitAdmin) {
        return event -> {
            rabbitAdmin.initialize();
        };
    }
}
