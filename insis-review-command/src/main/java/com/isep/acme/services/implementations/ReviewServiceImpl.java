package com.isep.acme.services.implementations;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.mappers.ReviewMapper;
import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.services.ProductService;
import com.isep.acme.services.RatingService;
import com.isep.acme.services.RestService;
import com.isep.acme.services.ReviewService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    
    @Autowired
    ProductRepository productRepository;

    @Autowired
    RatingService ratingService;
    
    @Autowired
    RestService restService;
    
    @Autowired
    ProductService productService;

    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public Review create(final Review review, final Product product) {

        String funfact = restService.getFunFact(LocalDate.now());   

        review.setFunFact(funfact);
        review.setProduct(product);
        review.setApprovalStatus("pending");

        reviewRepository.save(review);

        return review;
    }

    @Override
    public Review create(Review review) {

        reviewRepository.findById(review.getIdReview())
                .ifPresent(existingReview -> {
                    throw new IllegalArgumentException("Review with ID " + review.getIdReview() + " already exists.");
                });

        return reviewRepository.save(review);
    }

    @Override
    public Review moderateReview(Long reviewID, String status)
            throws ResourceNotFoundException, IllegalArgumentException {

        Review reviewToUpdate = reviewRepository.findById(reviewID)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        if (reviewToUpdate.setApprovalStatus(status))
            return reviewRepository.save(reviewToUpdate);

        return null;
    }

    @Override
    public Boolean deleteReview(Long reviewId) {

        Review reviewToDelete = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        reviewRepository.delete(reviewToDelete);
        return true;
    }
}