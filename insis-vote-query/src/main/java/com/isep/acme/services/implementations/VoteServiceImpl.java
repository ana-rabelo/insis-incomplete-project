package com.isep.acme.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.dtos.VoteDTO;
import com.isep.acme.exception.ResourceNotFoundException;
import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.VoteRepository;
import com.isep.acme.services.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public Vote create(VoteDTO voteDTO) {
        try {
            Vote vote = new Vote();
            System.out.println("Review: " + voteDTO.getIdReview());
            Review review = reviewRepository.findById(voteDTO.getIdReview()).orElseThrow(() -> new ResourceNotFoundException("Review not found"));        
            System.out.println("Review: " + review.getVotes());
            vote.addReview(review);
            vote.setVoteType(voteDTO.getVoteType());
            vote.setVoteID(voteDTO.getVoteId());
            return voteRepository.save(vote);
          
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error adding vote to review");
        }
       
    }

    @Override
    public VoteDTO getVotesById(long idVote) {
        Vote vote = voteRepository.findById(idVote).orElseThrow(() -> new EntityNotFoundException("Vote not found"));
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setVoteId(vote.getVoteID());
        voteDTO.setVoteType(vote.getVoteType());
        voteDTO.setIdReview(vote.getReview().getIdReview());
        return voteDTO;
    }

  
}
