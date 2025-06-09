package com.exercise.restaurant.model;

import java.util.UUID;

public class Customer {
    UUID id;
    private String firstName;
    private String lastName;
    private String phone;

    public Customer(UUID id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}
