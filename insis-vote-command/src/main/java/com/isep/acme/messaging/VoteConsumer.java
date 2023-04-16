package com.isep.acme.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isep.acme.dtos.VoteDTO;
import com.isep.acme.model.Vote;
import com.isep.acme.services.VoteService;
import com.isep.acme.services.mapper.VoteMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class VoteConsumer {
    
    @Autowired
    private VoteMapper voteMapper;
    private VoteService voteService;

    // @RabbitListener(queues = "#{voteCreatedQueue}")
    // public void receiveCreatedVoteMessage(Message message) {
    //     try {
    //         String bodyMessage = new String(message.getBody(), "UTF-8");
    //         log.info("Received message for created vote: {}", bodyMessage);
 
    //         VoteDTO voteDTO = voteMapper.messageToDto(bodyMessage);
    //         voteService.create(voteDTO);
    //         log.info("Review created successfully.");

    //     } catch (Exception e) {
    //         log.error("Error receving message.", e.getMessage());
    //     }
    // }

    // @RabbitListener(queues = "#{voteDeletedQueue}")
    // public void receiveDeletedVoteMessage(Message message) {
    //     try {
    //         String voteId = new String(message.getBody(), "UTF-8");
    //         log.info("Received message for deleted vote ID: {}", voteId);

    //         voteService.deleteById(Long.parseLong(voteId));

    //     } catch (Exception e) {
    //         log.error("Error receiving deleted vote message: {}", e.getMessage());
    //     }
    // }
}
