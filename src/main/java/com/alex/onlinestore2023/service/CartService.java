package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.dto.CartDto;

public interface CartService {

    void addToCart(Long userId, Long productId);

//    CartDto getCart (Long userId);

    CartDto findByUserModel_Id(Long id);


    void deleteOrderLineFromCart(Long userId, Long orderLineId);
}
