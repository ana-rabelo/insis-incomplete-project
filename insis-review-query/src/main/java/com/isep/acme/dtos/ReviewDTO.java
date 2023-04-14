package com.isep.acme.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDTO {

	private Long idReview;
	private String reviewText;
	private String approvalStatus;
	private String funFact;
	private Double rating;

}
