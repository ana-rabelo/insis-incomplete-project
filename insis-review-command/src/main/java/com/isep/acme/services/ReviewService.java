package com.isep.acme.services;

import org.springframework.stereotype.Service;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;

@Service
public interface ReviewService {

	Review create(Review review, Product product);
    Review create(Review review);

	Boolean deleteReview(Long reviewId);

	Review moderateReview(Long reviewID, String status);
}
