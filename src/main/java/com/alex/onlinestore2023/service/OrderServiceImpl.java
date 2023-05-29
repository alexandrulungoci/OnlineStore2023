package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.Model.CartModel;
import com.alex.onlinestore2023.Model.OrderLineModel;
import com.alex.onlinestore2023.Model.OrderModel;
import com.alex.onlinestore2023.Model.UserModel;
import com.alex.onlinestore2023.repository.CartRepository;
import com.alex.onlinestore2023.repository.OrderRepository;
import com.alex.onlinestore2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void addOrder(Long userId) {

        OrderModel orderModel = new OrderModel();
        CartModel cartModel = cartRepository.findByUserModel_Id(userId);
        List<OrderLineModel> orderLineModelList = cartModel.getOrderLineModelList();
        orderModel.setOrderLineModelList(orderLineModelList);

        Optional<UserModel> userModelOptional = userRepository.findById(userId);
        if (userModelOptional.isPresent()){
            UserModel userModel = userModelOptional.get();
            orderModel.setUserModel(userModel);
            orderModel.setUserName(userModel.getUserName());

        }
        orderModel.setTotalCost(cartModel.getTotalCartCost());
        orderModel.setOrderDate(LocalDateTime.now());
        cartModel.setOrderLineModelList(null);
        cartModel.setTotalCartCost(0.0);

        orderRepository.save(orderModel);


    }
}
