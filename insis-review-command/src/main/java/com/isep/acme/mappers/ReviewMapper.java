package com.isep.acme.mappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.dtos.usecases.CreateReviewDTO;
import com.isep.acme.model.Review;

@Component
public class ReviewMapper {

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
