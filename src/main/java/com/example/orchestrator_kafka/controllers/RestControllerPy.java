package com.example.orchestrator_kafka.controllers;

import com.example.orchestrator_kafka.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class RestControllerPy {
    @Autowired
    private KafkaProducer producer;

    @GetMapping("/order-data")
    public String getOrderData() {

        String data = "data";
        System.out.println("test data");
        sendGetRequest("http://127.0.0.1:8001/recommendation_module/");
        return data;
    }

    @GetMapping("/purchase-data")
    public String getPurchaseData() {

        String data = "[" +
                "{product_id: 1," +
                "bought: true}" +
                "{product_id: 2," +
                "bought: true}" +
                "{product_id: 3," +
                "bought: false}" +
                "]";
        return data;
    }


//имитация оркестратора и базы
    @GetMapping("/data_from_database")
    public String getDataFromDatabase() {
        //здесь должен быть метод обращения к базе
        String userRatingDataFromBase = "{" +
                "'alex': {'1': 5.0, '2': 3.0, '5': 4.0}, " +
                "'ivan': {'1': 4.0, '6': 1.0, '8': 2.0, '9': 3.0}, " +
                "'bob': {'2': 5.0, '3': 5.0}, " +
                "'david': {'3': 4.0, '4': 3.0, '6': 2.0, '7': 1.0}, " +
                "'john': {'1': 3.0, '3': 3.0, '4': 3.0, '6': 3.0, '7': 3.0, '9': 3.0}" +
                "}";
        return userRatingDataFromBase;
    }

//    @CrossOrigin
//    @GetMapping("/request_from_react/{userId}")
//    public String requestFromReact(@PathVariable("userId") String userId) {
//        String response = sendGetRequest("http://127.0.0.1:8001/api/recommend_data/" + userId + "/");
//        System.out.println(response);
//        return response;
//    }

    @CrossOrigin
    @GetMapping("/request_from_react/{userId}")
    public void imitationOfKafkaRequestFromReact(@PathVariable("userId") String userId) {
        producer.sendMessage("requestForUser", userId);
        System.out.println("Message to Py with userId " + userId + " sent.");
    }

    public String sendGetRequest(String source) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(source, HttpMethod.GET, null, String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }



}
