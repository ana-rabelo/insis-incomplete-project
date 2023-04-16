package com.isep.acme.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isep.acme.dtos.VoteDTO;
@Component
public class VoteProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public VoteProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void sendCreatedVoteMessage(VoteDTO voteDTO) {
        rabbitTemplate.convertAndSend("ms.votes.vote-created", "", voteDTO);
    }

    public void sendDeletedVoteMessage(Long voteID) {
        rabbitTemplate.convertAndSend("ms.votes.vote-deleted", "", voteID);
    }
    
}
