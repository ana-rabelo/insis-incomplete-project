package com.isep.acme.services.mapper;

import org.mapstruct.Mapper;

import com.isep.acme.dtos.VoteDTO;
import com.isep.acme.model.Vote;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    VoteDTO toDTO(Vote vote);

    Vote toEntity(VoteDTO voteDTO);

    VoteDTO messageToDto(String bodyMessage);

    /* @Mapping(source = "review_id", target = "reviewId")
    VoteDTO toDTOWithReviewId(Vote vote); */
}