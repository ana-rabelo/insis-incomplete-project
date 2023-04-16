package com.isep.acme.services;

import org.springframework.stereotype.Service;

import com.isep.acme.model.Review;

@Service
public interface ReviewService {

    Iterable<Review> getAll();

    Review save(Review review);

    // boolean addVoteToReview(Long reviewID, Vote vote);

    Review moderateReview(Long reviewID, String approved);

    Boolean deleteReview(Long reviewId);
}
