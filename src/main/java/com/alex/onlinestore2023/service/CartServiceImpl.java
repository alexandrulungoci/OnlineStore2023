package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.Model.CartModel;
import com.alex.onlinestore2023.Model.OrderLineModel;
import com.alex.onlinestore2023.Model.ProductModel;
import com.alex.onlinestore2023.Model.UserModel;
import com.alex.onlinestore2023.dto.CartDto;
import com.alex.onlinestore2023.dto.OrderLineDto;
import com.alex.onlinestore2023.dto.ProductDto;
import com.alex.onlinestore2023.repository.CartRepository;
import com.alex.onlinestore2023.repository.ProductRepository;
import com.alex.onlinestore2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class CartServiceImpl implements CartService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    private double totalCartCost = 0.0;



    @Override
    public void addToCart(Long userId, Long productId) {
        Optional<UserModel> userModelOptional = userRepository.findById(userId);
        if (userModelOptional.isPresent()){
            UserModel userModel = userModelOptional.get();

            CartModel cartModel= userModel.getCartModel();
            if (cartModel == null || cartModel.getOrderLineModelList() == null){
                cartModel = new CartModel();
                cartModel.setTotalCartCost(totalCartCost);
                userModel.setCartModel(cartModel);
            }
            OrderLineModel orderLineModel = new OrderLineModel();
            Optional<ProductModel> productModelOptional = productRepository.findById(productId);
            if (productModelOptional.isPresent()){
                ProductModel productModel = productModelOptional.get();
                orderLineModel.setProductModel(productModel);
                orderLineModel.setQuantity(1);
                orderLineModel.setProductPrice(productModel.getPrice());
                orderLineModel.setLinePrice(orderLineModel.getQuantity() * orderLineModel.getProductPrice());
            }

            totalCartCost = cartModel.getTotalCartCost() + orderLineModel.getLinePrice();
            cartModel.setTotalCartCost(totalCartCost);
            cartModel.getOrderLineModelList().add(orderLineModel);

            cartRepository.save(cartModel);
            userRepository.save(userModel);


        }



    }

    @Override
    public CartDto findByUserModel_Id(Long id) {
        CartDto cartDto = new CartDto();
        CartModel cartModel = cartRepository.findByUserModel_Id(id);
        List<OrderLineDto> orderLineDtoList = new ArrayList<>();
        List<OrderLineModel> orderLineModelList = cartModel.getOrderLineModelList();
        if (orderLineModelList == null){
            return null;
        }

        for ( OrderLineModel orderLineModel: orderLineModelList){
            OrderLineDto orderLineDto = new OrderLineDto();
            orderLineDto.setId(orderLineModel.getId());
            ProductDto productDto = new ProductDto();
            ProductModel productModel = orderLineModel.getProductModel();
            productDto.setId(productModel.getId());
            productDto.setProductName(productModel.getProductName());
            productDto.setPrice(productModel.getPrice());

            orderLineDto.setId(orderLineModel.getId());
            orderLineDto.setProduct(productDto);
            orderLineDto.setQuantity(orderLineModel.getQuantity());
            orderLineDto.setProductPrice(orderLineModel.getProductPrice());
            orderLineDto.setLinePrice(orderLineModel.getLinePrice());
            orderLineDtoList.add(orderLineDto);

        }

        cartDto.setId(cartModel.getId());
        cartDto.setOrderLineDtoList(orderLineDtoList);
        cartDto.setTotalCartCost(cartModel.getTotalCartCost());


        return cartDto;
    }
}
