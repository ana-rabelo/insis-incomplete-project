package com.isep.acme.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.exception.ResourceNotFoundException;
import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.VoteRepository;
import com.isep.acme.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    VoteRepository voteRepository;

    @Override
    public Iterable<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public boolean addVoteToReview(Long reviewID, Vote vote) {

        Optional<Review> review = this.reviewRepository.findById(reviewID);

        if (review.isEmpty())
            return false;
        else {
            review.get().addVote(vote);
            voteRepository.save(vote);
        }
        return true;
    }

    @Override
    public Boolean deleteReview(Long reviewId) {
        Review reviewToDelete = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        reviewRepository.delete(reviewToDelete);
        return true;
    }

    @Override
    public Review moderateReview(Long reviewID, String approved)
            throws ResourceNotFoundException, IllegalArgumentException {

        Optional<Review> r = reviewRepository.findById(reviewID);

        if (r.isEmpty()) {
            throw new ResourceNotFoundException("Review not found");
        }

        Boolean ap = r.get().setApprovalStatus(approved);

        if (!ap) {
            throw new IllegalArgumentException("Invalid status value");
        }

        return reviewRepository.save(r.get());
    }
}