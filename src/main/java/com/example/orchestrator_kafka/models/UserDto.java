package com.example.orchestrator_kafka.models;

import lombok.Data;

@Data
public class UserDto {
    private Long age;
    private String name;
    private Address address;
}
