package com.isep.acme.services.mapper;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.model.Review;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReviewMapper {

    public Review createReviewFromMessage(String body){
        try {
            JsonNode json = new ObjectMapper().readTree(body);
            String approvalStatus = json.get("approvalStatus").asText();
            Long idReview = json.get("idReview").asLong();

            Review review = new Review();
            review.setIdReview(idReview);
            review.setApprovalStatus(approvalStatus);
            log.info("Review created from message: {}", review.toString());
            
            return review;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}

