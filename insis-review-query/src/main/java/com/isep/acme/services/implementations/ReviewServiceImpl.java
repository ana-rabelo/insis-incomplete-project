package com.isep.acme.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.mappers.ReviewMapper;
import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.UserRepository;
import com.isep.acme.services.RatingService;
import com.isep.acme.services.RestService;
import com.isep.acme.services.ReviewService;
import com.isep.acme.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;
    ProductRepository productRepository;
    UserRepository userRepository;
    UserService userService;
    RatingService ratingService;
    RestService restService;
    ReviewMapper reviewMapper;

    @Override
    public Iterable<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review create(Review review) {
        log.info("Aqui o ID da review: {} e o product {}", 
                        review.getIdReview(),
                        review.getProduct().getSku());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsOfProduct(String sku, String status) {

        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        List<Review> reviews = reviewRepository.findByProductIdStatus(product, status)
                .orElseThrow(() -> new IllegalArgumentException("There is no reviews for this product"));

        return reviews;
    }

    @Override
    public Double getWeightedAverage(Product product) {

        Optional<List<Review>> r = reviewRepository.findByProductId(product);

        if (r.isEmpty())
            return 0.0;

        double sum = 0;

        for (Review rev : r.get()) {
            Double rate = rev.getRating();
            sum += rate;
        }

        return sum / r.get().size();
    }

    @Override
    public Boolean deleteReview(Long reviewId) {

        Optional<Review> rev = reviewRepository.findById(reviewId);

        if (rev.isEmpty()) {
            return null;
        }

        Review r = rev.get();
        reviewRepository.delete(r);

        return true;

    }

    @Override
    public List<ReviewDTO> findPendingReview() {

        Optional<List<Review>> r = reviewRepository.findPendingReviews();

        if (r.isEmpty()) {
            return null;
        }

        return reviewMapper.toDtoList(r.get());
    }

    @Override
    public Review moderateReview(Long reviewID, String status)
            throws ResourceNotFoundException, IllegalArgumentException {

        Review reviewToUpdate = reviewRepository.findById(reviewID)
                    .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        reviewToUpdate.setApprovalStatus(status);

        return reviewRepository.save(reviewToUpdate);
    }

    @Override
    public List<Review> findReviewsByUser(String user) {

        Optional<List<Review>> reviews = reviewRepository.findByUser(user);

        return reviews.get();
    }
}