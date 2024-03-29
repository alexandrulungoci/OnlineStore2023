package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.Model.*;
import com.alex.onlinestore2023.dto.*;
import com.alex.onlinestore2023.repository.CartRepository;
import com.alex.onlinestore2023.repository.OrderRepository;
import com.alex.onlinestore2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
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

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {

        List<OrderModel> orderModelList = orderRepository.findByUserModel_Id(userId);
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (OrderModel orderModel : orderModelList) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orderModel.getId());
            orderDto.setTotalCost(orderModel.getTotalCost());
            orderDto.setUserName(orderModel.getUserName());

            UserDto userDto = new UserDto();
            Optional<UserModel> userModelOptional = userRepository.findById(userId);
            if (userModelOptional.isPresent()) {
                UserModel userModel = userModelOptional.get();
                userDto.setId(userModel.getId());
                userDto.setUserName(userModel.getUserName());

                UserAddressModel userAddressModel = userModel.getUserAddress();
                if (userAddressModel != null) {
                    UserAddressDto userAddressDto = new UserAddressDto();
                    userAddressDto.setId(userAddressModel.getId());
                    userAddressDto.setCountry(userAddressModel.getCountry());
                    userAddressDto.setCity(userAddressModel.getCity());
                    userAddressDto.setStreet(userAddressModel.getStreet());
                    userAddressDto.setStreetNumber(userAddressModel.getStreetNumber());
                    userAddressDto.setZipcode(userAddressModel.getZipcode());
                    userDto.setUserAddress(userAddressDto);
                }
                userDto.setRoleModel(userModel.getRoleModel());
                orderDto.setUser(userDto);

                orderDto.setOrderDate(orderModel.getOrderDate());


                List<OrderLineModel> orderLineModelList = orderModel.getOrderLineModelList();
                List< OrderLineDto> orderLineDtoList = new ArrayList<>();
                for(OrderLineModel orderLineModel: orderLineModelList) {
                    OrderLineDto orderLineDto = new OrderLineDto();
                    orderLineDto.setId(orderLineModel.getId());
                    orderLineDto.setLinePrice(orderLineModel.getLinePrice());
                    orderLineDto.setQuantity(orderLineModel.getQuantity());
                    orderLineDto.setProductPrice(orderLineModel.getProductPrice());
                    ProductDto productDto = new ProductDto();
                    productDto.setId(orderLineModel.getProductModel().getId());
                    productDto.setProductName(orderLineModel.getProductModel().getProductName());
                    productDto.setPrice(orderLineModel.getProductModel().getPrice());
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setId(orderLineModel.getProductModel().getCategory().getId());
                    categoryDto.setCategoryName(orderLineModel.getProductModel().getCategory().getCategoryName());
                    productDto.setCategory(categoryDto);
                    orderLineDto.setProduct(productDto);

                    orderLineDtoList.add(orderLineDto);
                    orderDto.setOrderLineList(orderLineDtoList);

                }

                orderDtoList.add(orderDto);
                return orderDtoList;
            }

        }
        return null;
    }


    @Override
    public List<OrderDto> getOrders() {

        List<OrderModel> orderModelList = orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (OrderModel orderModel : orderModelList) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orderModel.getId());
            orderDto.setTotalCost(orderModel.getTotalCost());
            orderDto.setUserName(orderModel.getUserName());

            UserDto userDto = new UserDto();
            long userId = orderModel.getUserModel().getId();
            Optional<UserModel> userModelOptional = userRepository.findById(userId);
            if (userModelOptional.isPresent()) {
                UserModel userModel = userModelOptional.get();
                userDto.setId(userModel.getId());
                userDto.setUserName(userModel.getUserName());

                UserAddressModel userAddressModel = userModel.getUserAddress();
                if (userAddressModel != null) {
                    UserAddressDto userAddressDto = new UserAddressDto();
                    userAddressDto.setId(userAddressModel.getId());
                    userAddressDto.setCountry(userAddressModel.getCountry());
                    userAddressDto.setCity(userAddressModel.getCity());
                    userAddressDto.setStreet(userAddressModel.getStreet());
                    userAddressDto.setStreetNumber(userAddressModel.getStreetNumber());
                    userAddressDto.setZipcode(userAddressModel.getZipcode());
                    userDto.setUserAddress(userAddressDto);
                }
                userDto.setRoleModel(userModel.getRoleModel());
                orderDto.setUser(userDto);

                orderDto.setOrderDate(orderModel.getOrderDate());


                List<OrderLineModel> orderLineModelList = orderModel.getOrderLineModelList();
                List<OrderLineDto> orderLineDtoList = new ArrayList<>();
                for (OrderLineModel orderLineModel : orderLineModelList) {
                    OrderLineDto orderLineDto = new OrderLineDto();
                    orderLineDto.setId(orderLineModel.getId());
                    orderLineDto.setLinePrice(orderLineModel.getLinePrice());
                    orderLineDto.setQuantity(orderLineModel.getQuantity());
                    orderLineDto.setProductPrice(orderLineModel.getProductPrice());
                    ProductDto productDto = new ProductDto();
                    productDto.setId(orderLineModel.getProductModel().getId());
                    productDto.setProductName(orderLineModel.getProductModel().getProductName());
                    productDto.setPrice(orderLineModel.getProductModel().getPrice());
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setId(orderLineModel.getProductModel().getCategory().getId());
                    categoryDto.setCategoryName(orderLineModel.getProductModel().getCategory().getCategoryName());
                    productDto.setCategory(categoryDto);
                    orderLineDto.setProduct(productDto);

                    orderLineDtoList.add(orderLineDto);
                    orderDto.setOrderLineList(orderLineDtoList);

                }

            }
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}
