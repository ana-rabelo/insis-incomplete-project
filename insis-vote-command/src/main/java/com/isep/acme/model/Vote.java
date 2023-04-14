package com.isep.acme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.isep.acme.services.dto.VoteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long voteID;

    @Column
    private String voteType;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public VoteDTO toDto() {
        return new VoteDTO(this.voteType,
                this.review.getIdReview());
    }

}
