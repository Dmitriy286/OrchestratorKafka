package com.example.orchestrator_kafka.controllers;

import com.example.orchestrator_kafka.models.Address;
import com.example.orchestrator_kafka.models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("msg")
public class KafkaRestController {
//    @Autowired
//    private KafkaTemplate<Long, UserDto> kafkaTemplate;
//    @Autowired
//    public KafkaRestController(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

//    @PostMapping("msg")
//    public void sendMessage(Long msgId, String msg) {
//        ListenableFuture<SendResult<Long, String>> future = kafkaTemplate.send("msg", msgId, msg);
//        future.addCallback(System.out::println, System.err::println);
//        kafkaTemplate.flush();
//    }



//    @PostMapping(("msgdto"))
//    public void sendMessageAboutDTO(Long msgId, UserDto msg) {
//        msg.setAddress(new Address("RUS", "Msk", "Arbat", 1L, 1L));
//        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("msg", msgId, msg);
//        future.addCallback(System.out::println, System.err::println);
//        kafkaTemplate.flush();
//    }

}
