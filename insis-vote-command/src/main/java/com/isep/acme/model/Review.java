package com.isep.acme.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    private Long idReview;

    @Column(nullable = false)
    private String approvalStatus;

    // @OneToMany(mappedBy = "review")
    // private Set<Vote> votes = new HashSet<>();

    public Boolean setApprovalStatus(String approvalStatus) {

        if (approvalStatus.equalsIgnoreCase("pending") ||
                approvalStatus.equalsIgnoreCase("approved") ||
                approvalStatus.equalsIgnoreCase("rejected")) {

            this.approvalStatus = approvalStatus;
            return true;
        }
        return false;
    }

    // public void addVote(Vote vote) {
    //     if (this.approvalStatus.equals("approved"))
    //         this.votes.add(vote);
    //     else
    //         throw new IllegalArgumentException("Review is not approved");
    // }
}
