package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.Model.UserAddressModel;
import com.alex.onlinestore2023.Model.UserModel;
import com.alex.onlinestore2023.dto.UserAddressDto;
import com.alex.onlinestore2023.dto.UserDto;
import com.alex.onlinestore2023.repository.UserAddressRepository;
import com.alex.onlinestore2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Override
    public void addUser(UserDto userDto) {
        UserModel userModel = new UserModel();
        userModel.setId(userDto.getId());
        userModel.setUserName(userDto.getUserName());

        Optional<UserAddressModel> userAddressModelOptional = userAddressRepository.findById(userDto.getUserAddress().getId());
        if (userAddressModelOptional.isPresent()){
            UserAddressModel userAddressModel = userAddressModelOptional.get();
            userModel.setUserAddress(userAddressModel);
        }

        userModel.setRoleModel(userDto.getRoleModel());

//        userModel.setCartModel

        userRepository.save(userModel);

    }

    @Override
    public UserDto getUser(Long id) {
        UserDto userDto = new UserDto();
        Optional<UserModel> userModelOptional = userRepository.findById(id);
        if (userModelOptional.isPresent()){
            UserModel userModel = userModelOptional.get();
            userDto.setId(userModel.getId());
            userDto.setUserName(userModel.getUserName());

            UserAddressModel userAddressModel = userModel.getUserAddress();
            if (userAddressModel != null){
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

            return userDto;
        }

        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<UserModel> userModelList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(UserModel userModel: userModelList){
            UserDto userDto = new UserDto();
            userDto.setId(userModel.getId());
            userDto.setUserName(userModel.getUserName());

            UserAddressDto userAddressDto = new UserAddressDto();
            userAddressDto.setId(userModel.getUserAddress().getId());
            userAddressDto.setCountry(userModel.getUserAddress().getCountry());
            userAddressDto.setCity(userModel.getUserAddress().getCity());
            userAddressDto.setStreet(userModel.getUserAddress().getStreet());
            userAddressDto.setStreetNumber(userModel.getUserAddress().getStreetNumber());
            userAddressDto.setZipcode(userModel.getUserAddress().getZipcode());
            userDto.setUserAddress(userAddressDto);

            userDto.setRoleModel(userModel.getRoleModel());

            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public void updateUser(UserDto userDto) {

        Optional<UserModel> userModelOptional = userRepository.findById(userDto.getId());
        if (userModelOptional.isPresent()){
            UserModel userModel = userModelOptional.get();
            userModel.setUserName(userDto.getUserName());
            userModel.setRoleModel(userDto.getRoleModel());
            userRepository.save(userModel);
        }
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
