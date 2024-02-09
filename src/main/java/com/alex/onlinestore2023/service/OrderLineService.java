package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.dto.OrderLineDto;

public interface OrderLineService {

void updateOrderLine(OrderLineDto orderLineDto, Long UserId);

OrderLineDto getOrderLineById(Long id);
}
