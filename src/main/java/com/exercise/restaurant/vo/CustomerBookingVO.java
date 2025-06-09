package com.exercise.restaurant.vo;

import com.exercise.util.TableSize;

import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerBookingVO {
    private String customerPhone;
    private String cusomerFirstName;
    private String cusomerLastName;
    private String tableSize;
    private String bookedDateTime;
    private String restaurantName;
    public CustomerBookingVO() {
    }

    public CustomerBookingVO(String customerPhone, String cusomerFirstName, String cusomerLastName, String tableSize, String bookedDateTime, String restaurantName) {
        this.customerPhone = customerPhone;
        this.cusomerFirstName = cusomerFirstName;
        this.cusomerLastName = cusomerLastName;
        this.tableSize = tableSize;
        this.bookedDateTime = bookedDateTime;
        this.restaurantName = restaurantName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCusomerFirstName() {
        return cusomerFirstName;
    }

    public void setCusomerFirstName(String cusomerFirstName) {
        this.cusomerFirstName = cusomerFirstName;
    }

    public String getCusomerLastName() {
        return cusomerLastName;
    }

    public void setCusomerLastName(String cusomerLastName) {
        this.cusomerLastName = cusomerLastName;
    }

    public String getTableSize() {
        return tableSize;
    }

    public void setTableSize(String tableSize) {
        this.tableSize = tableSize;
    }

    public String getBookedDateTime() {
        return bookedDateTime;
    }

    public void setBookedDateTime(String bookedDateTime) {
        this.bookedDateTime = bookedDateTime;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
