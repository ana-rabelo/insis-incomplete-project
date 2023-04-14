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
    public Queue voteCreatedQueue() {
        return new Queue("votes.vote-created." + applicationName + "." + instanceID);
    }

    @Bean
    public Queue voteDeletedQueue() {
        return new Queue("votes.vote-deleted." + applicationName + "." + instanceID);
    }

    @Bean
    public Queue reviewCreatedQueue() {
        return new Queue("reviews.review-created." + applicationName + "." + instanceID);
    }

    @Bean
    public Queue reviewUpdatedQueue() {
        return new Queue("reviews.review-updated." + applicationName + "." + instanceID);
    }

    @Bean
    public Queue reviewDeletedQueue() {
        return new Queue("reviews.review-deleted." + applicationName + "." + instanceID);
    }

    @Bean
    public FanoutExchange voteCreatedExchange() {
        return new FanoutExchange("ms.votes.vote-created");
    }

    @Bean
    public FanoutExchange voteDeletedExchange() {
        return new FanoutExchange("ms.votes.vote-deleted");
    }

    @Bean
    public FanoutExchange reviewCreatedExchange() {
        return new FanoutExchange("ms.reviews.review-created");
    }

    @Bean
    public FanoutExchange reviewUpdatedExchange() {
        return new FanoutExchange("ms.reviews.review-updated");
    }

    @Bean
    public FanoutExchange reviewDeletedExchange() {
        return new FanoutExchange("ms.reviews.review-deleted");
    }

    @Bean
    public Binding voteCreatedBinding(Queue voteCreatedQueue, FanoutExchange voteCreatedExchange) {
        return BindingBuilder.bind(voteCreatedQueue).to(voteCreatedExchange);
    }

    @Bean
    public Binding voteDeletedBinding(Queue voteDeletedQueue, FanoutExchange voteDeletedExchange) {
        return BindingBuilder.bind(voteDeletedQueue).to(voteDeletedExchange);
    }

    @Bean
    public Binding reviewCreatedBinding(Queue reviewCreatedQueue, FanoutExchange reviewCreatedExchange) {
        return BindingBuilder.bind(reviewCreatedQueue).to(reviewCreatedExchange);
    }

    @Bean
    public Binding reviewUpdatedBinding(Queue reviewUpdatedQueue, FanoutExchange reviewUpdatedExchange) {
        return BindingBuilder.bind(reviewUpdatedQueue).to(reviewUpdatedExchange);
    }

    @Bean
    public Binding reviewDeletedBinding(Queue reviewDeletedQueue, FanoutExchange reviewDeletedExchange) {
        return BindingBuilder.bind(reviewDeletedQueue).to(reviewDeletedExchange);
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
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationListener(RabbitAdmin rabbitAdmin) {
        return event -> {
            rabbitAdmin.initialize();
        };
    }
}
