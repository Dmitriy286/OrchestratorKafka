package com.example.orchestrator_kafka;

import com.example.orchestrator_kafka.models.UserDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class OrchestratorKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrchestratorKafkaApplication.class, args);
    }
    
    @KafkaListener(topics="msg", groupId = "first")
    public void msgDTOListener(ConsumerRecord<Long, UserDto> record){
        System.out.println(record);
        System.out.println(record.partition());
        System.out.println(record.key());
        System.out.println(record.value());
    }

}
