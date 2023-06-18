package com.isep.acme.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.isep.acme.dtos.VoteDTO;
import com.isep.acme.model.Vote;

@Service
public interface VoteService {
	public Vote create(final VoteDTO vote);

	public VoteDTO getVotesById(final long id);

	public List<VoteDTO> getVotes();
}
