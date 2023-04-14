package com.isep.acme.services;

import org.springframework.stereotype.Service;

import com.isep.acme.model.Vote;
import com.isep.acme.model.enumerate.voteType;
import com.isep.acme.services.dto.VoteDTO;

@Service
public interface VoteService {
    public Vote create(final VoteDTO vote);
    public VoteDTO getVoteById(final long id);    
    public Vote updateVote(final Vote vote, final voteType voteType);
    public void deleteById(final Long voteId);
}
