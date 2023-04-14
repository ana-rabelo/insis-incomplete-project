package com.isep.acme.mappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.dtos.usecases.CreateReviewDTO;
import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.services.ProductService;

@Component
public class ReviewMapper {

    @Autowired
    private ProductService productservice;

    public static ReviewDTO toDto(Review review) {
        return new ReviewDTO(
                review.getIdReview(),
                review.getProduct().getSku(),
                review.getReviewText(),
                review.getUserName(),
                review.getApprovalStatus(),
                review.getFunFact(),
                review.getRating());
    }

    public static CreateReviewDTO toCreateReviewDTO(Review review) {
        return new CreateReviewDTO(
                review.getReviewText(),
                review.getUserName(),
                review.getRating());
    }

    public static Review toReview(CreateReviewDTO createReviewDTO) {
        Review review = new Review();

        review.setReviewText(createReviewDTO.getReviewText());
        review.setUserName(createReviewDTO.getUserName());
        review.setRating(createReviewDTO.getRating());

        return review;
    }

    public static Review toReview(ReviewDTO ReviewDTO, Product product) {
        Review review = new Review();

        review.setProduct(product);
        review.setReviewText(ReviewDTO.getReviewText());
        review.setUserName(ReviewDTO.getUserName());
        review.setRating(ReviewDTO.getRating());

        return review;
    }

    public static List<ReviewDTO> toDtoList(List<Review> review) {

        List<ReviewDTO> dtoList = new ArrayList<>();
        for (Review rev : review) {
            dtoList.add(toDto(rev));
        }

        return dtoList;
    }

    public Review createReviewFromMessage(String body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Review review = objectMapper.readValue(body, Review.class);
            return review;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Review createReviewDTO(String body){
        try {
            JsonNode json = new ObjectMapper().readTree(body);
            Long idReview = json.get("idReview").asLong();
            String productSku = json.get("productSku").asText();
            String reviewText = json.get("reviewText").asText();
            String userName = json.get("userName").asText();
            String approvalStatus = json.get("approvalStatus").asText();
            String funFact = json.get("funFact").asText();
            Double rating = json.get("rating").asDouble();
            
            Product product = productservice
                    .findBySku(productSku)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

            Review reviewCreated = new Review();
            reviewCreated.setIdReview(idReview);
            reviewCreated.setProduct(product);
            reviewCreated.setReviewText(reviewText);
            reviewCreated.setUserName(userName);
            reviewCreated.setApprovalStatus(approvalStatus);
            reviewCreated.setFunFact(funFact);
            reviewCreated.setRating(rating);

            return reviewCreated;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createMessageFromReview(Review review) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(review);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
