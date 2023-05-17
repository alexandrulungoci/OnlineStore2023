package com.alex.onlinestore2023.controller;

import com.alex.onlinestore2023.dto.CategoryDto;
import com.alex.onlinestore2023.dto.UserAddressDto;
import com.alex.onlinestore2023.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping("addUserAddress")
    public ResponseEntity addUserAddress(@RequestBody UserAddressDto userAddressDto){
        userAddressService.addUserAddress(userAddressDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getUserAddress/{id}")
    public ResponseEntity<UserAddressDto> getUserAddress(@PathVariable Long id) {
        UserAddressDto userAddressDto = userAddressService.getUserAddress(id);
        return new ResponseEntity(userAddressDto, HttpStatus.OK);

    }

    @GetMapping("getAllUserAddresses")
    public ResponseEntity<List<UserAddressDto>> getAllUserAddresses() {
        List<UserAddressDto> userAddressDtoList = userAddressService.getAllUserAddresses();
        return new ResponseEntity(userAddressDtoList, HttpStatus.OK);
    }

    @PutMapping("updateUserAddress")
    public ResponseEntity updateUserAddress(@RequestBody UserAddressDto userAddressDto) {
        userAddressService.updateUserAddress(userAddressDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("deleteUserAddress/{id}")
    public ResponseEntity deleteUserAddress(@PathVariable Long id){
        userAddressService.deleteUserAddress(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
