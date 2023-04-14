package com.isep.acme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isep.acme.model.Vote;
import com.isep.acme.services.ReviewService;
import com.isep.acme.services.VoteService;
import com.isep.acme.services.dto.VoteDTO;
import com.isep.acme.services.mapper.VoteMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Vote", description = "Endpoints for managing votes")
@RestController
@RequestMapping("/votes")
@AllArgsConstructor
@Slf4j
public class VoteController {

    @Autowired
    private final VoteService voteService;
    @Autowired
    private final ReviewService reviewService;

    @Operation(summary = "creates a vote")
    @PostMapping
    public ResponseEntity<VoteDTO> createVote(@RequestBody VoteDTO voteDTO) {

        Vote createdVote = voteService.create(voteDTO);
        reviewService.addVoteToReview(createdVote.getReview().getIdReview(), createdVote);
        log.info("Vote {} for review {} created.", createdVote.getVoteType(), createdVote.getVoteID());

        return ResponseEntity.status(HttpStatus.CREATED).body(voteDTO);
    }
}
