package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.Model.*;
import com.alex.onlinestore2023.dto.*;
import com.alex.onlinestore2023.repository.*;
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

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;


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

            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(productModel.getCategory().getId());
            categoryDto.setCategoryName(productModel.getCategory().getCategoryName());
            productDto.setCategory(categoryDto);

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

        UserDto userDto = new UserDto();
        Optional<UserModel> userModelOptional = userRepository.findById(id);
        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();

            userDto.setUserName(userModel.getUserName());
            userDto.setId(userModel.getId());
            userDto.setRoleModel(userModel.getRoleModel());

            UserAddressDto userAddressDto = new UserAddressDto();
            long addressId = userModel.getUserAddress().getId();
            Optional<UserAddressModel> userAddressModelOptional = userAddressRepository.findById(addressId);
            if (userAddressModelOptional.isPresent()){
                UserAddressModel userAddressModel = userAddressModelOptional.get();
                userAddressDto.setId(userAddressModel.getId());
                userAddressDto.setCountry(userAddressModel.getCountry());
                userAddressDto.setCity(userAddressModel.getCity());
                userAddressDto.setStreet(userAddressModel.getStreet());
                userAddressDto.setStreetNumber(userAddressModel.getStreetNumber());
                userAddressDto.setZipcode(userAddressModel.getZipcode());

            }
            userDto.setUserAddress(userAddressDto);
            cartDto.setUser(userDto);
        }

        return cartDto;
    }



    @Override
    public void deleteOrderLineFromCart(Long userId, Long orderLineId) {
        CartModel cartModel = cartRepository.findByUserModel_Id(userId);
        List<OrderLineModel> orderLineModelList = cartModel.getOrderLineModelList();
        Optional<OrderLineModel> orderLineModelOptional = orderLineRepository.findById(orderLineId);
        if (orderLineModelOptional.isPresent()){
            OrderLineModel orderLineModel = orderLineModelOptional.get();
            orderLineModelList.remove(orderLineModel);
        }
        cartModel.setOrderLineModelList(orderLineModelList);
        double totalCost = 0.0;
        for(OrderLineModel orderLineModel: orderLineModelList){
            totalCost += orderLineModel.getLinePrice();
        }
        cartModel.setTotalCartCost(totalCost);
        cartRepository.save(cartModel);

    }
}
