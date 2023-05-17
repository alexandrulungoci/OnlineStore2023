package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.Model.UserAddressModel;
import com.alex.onlinestore2023.dto.UserAddressDto;
import com.alex.onlinestore2023.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Override
    public void addUserAddress(UserAddressDto userAddressDto) {

        UserAddressModel userAddressModel = new UserAddressModel();
        userAddressModel.setId(userAddressDto.getId());
        userAddressModel.setCountry(userAddressDto.getCountry());
        userAddressModel.setCity(userAddressDto.getCity());
        userAddressModel.setStreet(userAddressDto.getStreet());
        userAddressModel.setStreetNumber(userAddressDto.getStreetNumber());
        userAddressModel.setZipcode(userAddressDto.getZipcode());

        userAddressRepository.save(userAddressModel);
    }

    @Override
    public UserAddressDto getUserAddress(Long id) {
        Optional<UserAddressModel> userAddressModelOptional = userAddressRepository.findById(id);
        if (userAddressModelOptional.isPresent()){
            UserAddressModel userAddressModel = userAddressModelOptional.get();
            UserAddressDto userAddressDto = new UserAddressDto();
            userAddressDto.setId(userAddressModel.getId());
            userAddressDto.setCity(userAddressModel.getCity());
            userAddressDto.setCountry(userAddressModel.getCountry());
            userAddressDto.setStreet(userAddressModel.getStreet());
            userAddressDto.setStreetNumber(userAddressModel.getStreetNumber());
            userAddressDto.setZipcode(userAddressModel.getZipcode());

            return userAddressDto;
        }
        return null;
    }

    @Override
    public List<UserAddressDto> getAllUserAddresses() {
        List<UserAddressDto> userAddressDtoList = new ArrayList<>();
        List<UserAddressModel> userAddressModelList = userAddressRepository.findAll();
        for (UserAddressModel userAddressModel: userAddressModelList){
            UserAddressDto userAddressDto = new UserAddressDto();
            userAddressDto.setId(userAddressModel.getId());
            userAddressDto.setCountry(userAddressModel.getCountry());
            userAddressDto.setCity(userAddressModel.getCity());
            userAddressDto.setStreet(userAddressModel.getStreet());
            userAddressDto.setStreetNumber(userAddressModel.getStreetNumber());
            userAddressDto.setZipcode(userAddressModel.getZipcode());
            userAddressDtoList.add(userAddressDto);
        }
        return userAddressDtoList;
    }

    @Override
    public void updateUserAddress(UserAddressDto userAddressDto) {
        Optional<UserAddressModel> userAddressModelOptional = userAddressRepository.findById(userAddressDto.getId());
        if (userAddressModelOptional.isPresent()){
            UserAddressModel userAddressModel = userAddressModelOptional.get();
            userAddressModel.setCountry(userAddressDto.getCountry());
            userAddressModel.setCity(userAddressDto.getCity());
            userAddressModel.setStreet(userAddressDto.getStreet());
            userAddressModel.setStreetNumber(userAddressDto.getStreetNumber());
            userAddressModel.setZipcode(userAddressDto.getZipcode());
            userAddressRepository.save(userAddressModel);
        }
    }

    @Override
    public void deleteUserAddress(Long id) {
        userAddressRepository.deleteById(id);


    }
}
