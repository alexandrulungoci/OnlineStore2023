package com.alex.onlinestore2023.Model;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    @OneToOne(cascade = CascadeType.ALL)
    private UserAddressModel userAddress;

    @Enumerated(EnumType.STRING)
    private RoleModel roleModel;

    @OneToOne(cascade = CascadeType.ALL)
    private CartModel cartModel;

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

    public UserAddressModel getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddressModel userAddress) {
        this.userAddress = userAddress;
    }

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    public CartModel getCartModel() {
        return cartModel;
    }

    public void setCartModel(CartModel cartModel) {
        this.cartModel = cartModel;
    }
}
