package com.alex.onlinestore2023.dto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrderDto {

    private long id;

    private String userName;

    private double totalCost;

    @OneToOne
    private UserDto user;

    private LocalDateTime orderDate;

    private List<OrderLineDto> orderLineList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderLineDto> getOrderLineList() {
        return orderLineList;
    }

    public void setOrderLineList(List<OrderLineDto> orderLineList) {
        this.orderLineList = orderLineList;
    }
}
