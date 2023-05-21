package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.dto.CartDto;

public interface CartService {

    void addToCart(Long userId, Long productId);

    CartDto findByUserModel_Id(Long id);
}
