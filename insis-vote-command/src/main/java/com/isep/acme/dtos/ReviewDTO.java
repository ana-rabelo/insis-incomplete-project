package com.isep.acme.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long idReview;
    private String productSku;
    private String reviewText;
    private String userName;
    private String approvalStatus;
    private String funFact;
    private Double rating;
}
