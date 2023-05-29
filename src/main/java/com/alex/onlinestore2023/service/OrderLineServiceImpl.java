package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.Model.CartModel;
import com.alex.onlinestore2023.Model.OrderLineModel;
import com.alex.onlinestore2023.dto.OrderLineDto;
import com.alex.onlinestore2023.repository.CartRepository;
import com.alex.onlinestore2023.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;



@Service
public class OrderLineServiceImpl implements OrderLineService{

    @Autowired
    OrderLineRepository orderLineRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public void updateOrderLine(OrderLineDto orderLineDto, Long userId) {
        Optional<OrderLineModel> orderLineModelOptional = orderLineRepository.findById(orderLineDto.getId());
        if (orderLineModelOptional.isPresent()){
            OrderLineModel orderLineModel = orderLineModelOptional.get();
            OrderLineModel orderLineModel1 = new OrderLineModel();
            orderLineModel1.setProductModel(orderLineModel.getProductModel());
            orderLineModel1.setProductPrice(orderLineModel.getProductPrice());
            orderLineModel1.setQuantity(orderLineDto.getQuantity());
            orderLineModel1.setLinePrice(orderLineModel1.getProductPrice() * orderLineModel1.getQuantity());

            CartModel cartModel = cartRepository.findByUserModel_Id(userId);
            List<OrderLineModel> orderLineModelList = cartModel.getOrderLineModelList();
            orderLineModelList.remove(orderLineModel);
            orderLineModelList.add(orderLineModel1);
            cartModel.setOrderLineModelList(orderLineModelList);
            double totalCost = 0.0;
            for(OrderLineModel orderLineModel2: orderLineModelList){
                totalCost += orderLineModel2.getLinePrice();
            }
            cartModel.setTotalCartCost(totalCost);
            orderLineRepository.save(orderLineModel1);
            orderLineRepository.delete(orderLineModel);
        }

    }
}
