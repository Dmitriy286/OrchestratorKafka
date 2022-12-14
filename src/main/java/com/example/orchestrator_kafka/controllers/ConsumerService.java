package com.example.orchestrator_kafka.controllers;

import com.example.orchestrator_kafka.models.UserDto;
import com.example.orchestrator_kafka.service.KafkaProducer;
import com.example.orchestrator_kafka.repository.RepositoryImitation;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @Autowired
    private KafkaProducer producer;

    @KafkaListener(topics = "stringmsg", groupId = "third")
    public void stringMsgListener(ConsumerRecord<Long, String> record) {
        System.out.println("This is data from third listener, got from python:");
        System.out.println(record.value());
    }

    @KafkaListener(topics = "requestDataFromDB", groupId = "third")
    public void triggerForDataFromDB(ConsumerRecord<Long, String> record) {
        System.out.println(record.value());
    }

    @KafkaListener(topics = "requestOrdersDataFromOrchestrator", groupId = "ordersDataGroup")
    public void requestOrdersDataFromDB(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
        String data = RepositoryImitation.ordersData;
        producer.sendMessage("requestOrdersDataFromDB", data);
    }

    //temporary, data base listener imitation
    @KafkaListener(topics = "requestOrdersDataFromDB", groupId = "ordersDataGroup")
    public void getOrdersDataRequestFromOrchestrator(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
        producer.sendMessage("sendOrdersDataFromDB", record.value());
    }

    @KafkaListener(topics = "sendOrdersDataFromDB", groupId = "ordersDataGroup")
    public void getOrdersDataFromDB(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
        producer.sendMessage("sendOrdersDataToRecommendationModule", record.value());
        System.out.println("Message to python sent");
    }

    @KafkaListener(topics = "sendRecommendedProductsData", groupId = "ordersDataGroup")
    public void getRecommendedProductsData(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
        //todo ?????????? ?????????? ???????????????? ???????????? ?? React
//        producer.sendMessage("...", record.value());
        System.out.println("Message to React sent");
    }

}
