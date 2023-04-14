package com.isep.acme.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ReviewDTO {
    private Long idReview;
    private String productSku;
    private String reviewText;
    private String userName;
    private String approvalStatus;
    private String funFact;
    private Double rating;
}
