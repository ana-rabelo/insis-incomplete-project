package com.isep.acme.services.dto;

import com.isep.acme.model.enumerate.voteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {
    private long voteId;
    private voteType voteType;
    private long reviewId;
}
