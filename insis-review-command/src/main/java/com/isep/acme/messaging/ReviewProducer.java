package com.isep.acme.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.model.Review;

@Component
public class ReviewProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ReviewProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void sendCreatedReviewMessage(Review review) {
        rabbitTemplate.convertAndSend("ms.reviews.review-created", "", review);
    }

    public void sendUpdatedReviewMessage(Review review) {
        rabbitTemplate.convertAndSend("ms.reviews.review-updated", "", review);
    }

    public void sendDeletedReviewMessage(Long ReviewID) {
        rabbitTemplate.convertAndSend("ms.reviews.review-deleted", "", ReviewID);
    }
}
