package com.alex.onlinestore2023.dto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class OrderDto {

    private long id;

    private String userName;

    private double totalCost;

    @OneToOne
    private UserDto user;

    private Date orderDate;

    private List<OrderLineDto> orderLineList;

}
