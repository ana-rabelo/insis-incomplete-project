package com.isep.acme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idReview;

	@Column(nullable = false)
	private String approvalStatus;

	@Column(nullable = false)
	private String reviewText;

	@Column(nullable = false)
	private String funFact;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_sku", nullable = false)
	private Product product;

	@Column(nullable = false)
	private String userName;

	private Double rating;

	public Boolean setApprovalStatus(String approvalStatus) {

		if (approvalStatus.equalsIgnoreCase("pending") ||
				approvalStatus.equalsIgnoreCase("approved") ||
				approvalStatus.equalsIgnoreCase("rejected")) {

			this.approvalStatus = approvalStatus;
			return true;
		}
		return false;
	}

	public void setReviewText(String reviewText) {
		if (reviewText == null || reviewText.isBlank()) {
			throw new IllegalArgumentException("Review Text is a mandatory attribute of Review.");
		}
		if (reviewText.length() > 2048) {
			throw new IllegalArgumentException("Review Text must not be greater than 2048 characters.");
		}

		this.reviewText = reviewText;
	}
}
