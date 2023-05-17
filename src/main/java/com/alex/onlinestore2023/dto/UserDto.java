package com.alex.onlinestore2023.dto;

import com.alex.onlinestore2023.Model.RoleModel;

public class UserDto {

    private long id;
    private String userName;
    private UserAddressDto userAddress;
    private RoleModel roleModel;

//    private CartDto cartDto;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserAddressDto getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddressDto userAddress) {
        this.userAddress = userAddress;
    }

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }
}
