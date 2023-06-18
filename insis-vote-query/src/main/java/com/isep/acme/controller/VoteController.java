package com.isep.acme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isep.acme.dtos.VoteDTO;
import com.isep.acme.model.Vote;
import com.isep.acme.services.VoteService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Vote", description = "Endpoints for managing votes")
@RestController
@RequestMapping("/votes")
@AllArgsConstructor
public class VoteController {

	@Autowired
	private final VoteService voteService;

	@GetMapping("/{voteId}")
	public ResponseEntity<VoteDTO> getVoteById(@PathVariable Long voteId) {
		VoteDTO vote = voteService.getVotesById(voteId);
		return ResponseEntity.ok().body(vote);
	}

	@GetMapping("")
	public ResponseEntity<List<VoteDTO>> getVotes() {
		List<VoteDTO> vote = voteService.getVotes();
		return ResponseEntity.ok().body(vote);
	}

}
