package com.isep.acme.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class VoteReviewDTO {

	private String user;
	private String vote;
}