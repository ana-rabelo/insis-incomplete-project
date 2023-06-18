package com.isep.acme.services.mapper;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.dtos.VoteDTO;
import com.isep.acme.model.Vote;
import com.isep.acme.model.enumerate.voteType;

@Component
public class VoteMapper {

	public VoteDTO toDTO(Vote vote) {
		if (vote == null) {
			return null;
		}

		VoteDTO voteDTO = new VoteDTO();

		voteDTO.setVoteType(vote.getVoteType());

		return voteDTO;
	}

	public Vote toEntity(VoteDTO voteDTO) {
		Vote vote = new Vote();
		vote.setVoteID(voteDTO.getVoteId());
		vote.setVoteType(voteDTO.getVoteType());
		vote.setUser(voteDTO.getUser());
		return vote;
	}

	public VoteDTO toDTOFromMessage(String vote) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			VoteDTO voteDTO = mapper.readValue(vote, VoteDTO.class);
			return voteDTO;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * @Mapping(source = "review_id", target = "reviewId")
	 * VoteDTO toDTOWithReviewId(Vote vote);
	 */
}