package com.isep.acme.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vote", description = "Endpoints for managing votes")
@RestController
@RequestMapping("/votes")
public class VoteController {

    /* @GetMapping("/{user}")
    public ResponseEntity<VoteDTO> getVoteByUser(@PathVariable String nome) {
        
    }
 */
}
