package com.isep.acme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.isep.acme.dtos.VoteDTO;
import com.isep.acme.model.enumerate.voteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

	@Id
	private long voteID;

	@Column
	@Enumerated(EnumType.STRING)
	private voteType voteType;

	@Column
	private String user;

	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;

	@Override
	public String toString() {
		return "Vote [voteID=" + voteID + ", voteType=" + voteType + ", user=" + user + "]";
	}

	public VoteDTO toDto() {
		return new VoteDTO(this.voteID,
				this.voteType,
				this.user,
				this.review.getIdReview());
	}

	public void addReview(Review review) {
		this.review = review;
	}
}
