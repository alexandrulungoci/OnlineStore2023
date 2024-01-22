package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void addOrder(Long userId);

    List<OrderDto> getOrdersByUserId(Long userId);

}
