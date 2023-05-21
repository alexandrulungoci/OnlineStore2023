package com.alex.onlinestore2023.dto;

import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

public class CartDto {

    private long id;

    private List<OrderLineDto> orderLineDtoList;

//    private UserDto user;

    private double totalCartCost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderLineDto> getOrderLineDtoList() {
        return orderLineDtoList;
    }

    public void setOrderLineDtoList(List<OrderLineDto> orderLineDtoList) {
        this.orderLineDtoList = orderLineDtoList;
    }

    public double getTotalCartCost() {
        return totalCartCost;
    }

    public void setTotalCartCost(double totalCartCost) {
        this.totalCartCost = totalCartCost;
    }
}
