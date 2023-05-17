package com.alex.onlinestore2023.controller;

import com.alex.onlinestore2023.dto.ProductDto;
import com.alex.onlinestore2023.dto.UserDto;
import com.alex.onlinestore2023.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("addUser")
    public ResponseEntity addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        UserDto userDto = userService.getUser(id);
        return new ResponseEntity(userDto, HttpStatus.OK);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = userService.getAllUsers();
        return new ResponseEntity(userDtoList, HttpStatus.OK);
    }

    @PutMapping("updateUser")
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
