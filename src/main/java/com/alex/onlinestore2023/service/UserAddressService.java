package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.dto.UserAddressDto;

import java.util.List;

public interface UserAddressService {

    void addUserAddress(UserAddressDto userAddressDto);

    UserAddressDto getUserAddress(Long id);

    List<UserAddressDto> getAllUserAddresses();

    void updateUserAddress (UserAddressDto userAddressDto);

    void deleteUserAddress(Long id);
}
