package com.isep.acme.services.implementations;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.dtos.VoteDTO;
import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.model.enumerate.voteType;
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
		Review review = reviewRepository.findById(voteDTO.getIdReview())
				.orElseThrow(() -> new EntityNotFoundException("Review with id " + voteDTO.getIdReview() + " not found"));
		if (review.getApprovalStatus().equalsIgnoreCase("pending")) {
			throw new EntityNotFoundException("Review with id " + voteDTO.getIdReview() + " is pending");
		}
		Vote vote = new Vote();
		vote.setVoteType(voteDTO.getVoteType());
		vote.setUser(voteDTO.getUser());
		vote.setReview(review);

		return voteRepository.save(vote);
	}

	@Override
	public VoteDTO getVoteById(long id) {
		Vote vote = voteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Vote with id " + id + " not found"));

		return vote.toDto();
	}

	@Override
	public Vote updateVote(Vote vote, String type) {
		vote.setVoteType(type == "UPVOTE" ? voteType.UPVOTE : voteType.DOWNVOTE);
		return voteRepository.save(vote);
	}

	@Override
	public void deleteById(Long voteId) {
		voteRepository.deleteById(voteId);
	}

}
