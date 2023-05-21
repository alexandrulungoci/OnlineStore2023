package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.Model.CartModel;
import com.alex.onlinestore2023.Model.OrderModel;
import com.alex.onlinestore2023.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    private CartRepository cartRepository;

    @Override
    public void addOrder(Long id) {

        OrderModel orderModel = new OrderModel();
        CartModel cartModel = cartRepository.findByUserModel_Id(id);


    }
}
