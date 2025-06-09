package com.exercise.restaurant.model;

import com.exercise.util.TableSize;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;


public class Booking {
    private UUID id;
    private String customerPhone;
    private TableSize tableSize;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
   private LocalDateTime bookedDateTime;
   private String restaurantName;

    public Booking(UUID id, String customerPhone, TableSize tableSize, LocalDateTime bookedDateTime, String restaurantName) {
        this.id = id;
        this.customerPhone = customerPhone;
        this.tableSize = tableSize;
        this.bookedDateTime = bookedDateTime;
        this.restaurantName = restaurantName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public TableSize getTableSize() {
        return tableSize;
    }

    public void setTableSize(TableSize tableSize) {
        this.tableSize = tableSize;
    }

    public LocalDateTime getBookedDateTime() {
        return bookedDateTime;
    }

    public void setBookedDateTime(LocalDateTime bookedDateTime) {
        this.bookedDateTime = bookedDateTime;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
