package com.alex.onlinestore2023.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineModel> orderLineModelList = new ArrayList<>();

//    @OneToOne
//    private UserModel userModel;

    private double totalCartCost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


//    public UserModel getUserModel() {
//        return userModel;
//    }
//
//    public void setUserModel(UserModel userModel) {
//        this.userModel = userModel;
//    }

    public double getTotalCartCost() {
        return totalCartCost;
    }

    public void setTotalCartCost(double totalCartCost) {
        this.totalCartCost = totalCartCost;
    }

    public List<OrderLineModel> getOrderLineModelList() {
        return orderLineModelList;
    }

    public void setOrderLineModelList(List<OrderLineModel> orderLineModelList) {
        this.orderLineModelList = orderLineModelList;
    }
}
